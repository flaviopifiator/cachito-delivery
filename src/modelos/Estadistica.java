/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author Cusipuma
 */
public class Estadistica {
    
    public Object[][] cadete(String min, int cant, String max) throws ClassNotFoundException, SQLException, DataAccessException, ParseException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM cadetes");
        int tam=0;
        while(rs.next())
            tam++;
        Object[][] lista = new Object[tam][4];

        for(int i=0; i<lista.length; i++){
            lista[i][1]=0;
            lista[i][2]=0;
        }
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM cadetes");

        while(rs.next()){
            lista[i][0]=rs.getInt("cod_cadete");
            lista[i][3]=rs.getString("apellido_cadete")+" "+rs.getString("nombre_cadete");
            i++;
        }
        int[] ped; 
        rs.close();
        for(int k=0; k<lista.length; k++){
            rs=st.executeQuery("SELECT pedidos.estado_pedido, facturas.fecha_factura FROM facturas,pedidos WHERE facturas.cod_pedido=pedidos.cod_pedido AND facturas.cod_cadete="+lista[k][0]);
            while(rs.next()){
                Fecha f = new Fecha();
                if(f.rango(min, rs.getString("fecha_factura"), max)==true){
                    PedidoDAO pedido = new PedidoDAO();
                    int estado = rs.getInt("estado_pedido");
                    if(estado==4)
                        lista[k][1]=Integer.parseInt(lista[k][1].toString())+1;
                    if(estado==5)
                        lista[k][2]=Integer.parseInt(lista[k][2].toString())+1;
                }
            }
            rs.close();
        }
        
        lista=burbuja(lista);
        Object[][] nueva = new Object[cant][4];
        
        if(lista.length>cant){
            for(int j=0; j<cant; j++){
                nueva[j][0]=lista[j][0];
                nueva[j][1]=lista[j][1];
                nueva[j][2]=lista[j][2];
                nueva[j][3]=lista[j][3];
            }
        }else
            nueva=lista;
        
        
        st.close();
        return nueva;
    }
    
    public Object[][] burbuja(Object[][] lista){
        int i, j, aux;
        for(i=0;i<lista.length-1;i++)
            for(j=0;j<lista.length-i-1;j++)
                if(Integer.parseInt(lista[j+1][1].toString())>Integer.parseInt(lista[j][1].toString())){
                    aux=Integer.parseInt(lista[j+1][1].toString());
                    lista[j+1][1]=lista[j][1];
                    lista[j][1]=aux;
                }
        
        return lista;
    }
    
    public float[] porcentaje(Object[][] lista){
        float[] aux = new float[lista.length];
        int suma=0;
        float resul=0;
        
        DecimalFormat df = new DecimalFormat("0.00"); 
        
        for(int i=0; i<lista.length; i++)
            suma=suma+Integer.parseInt(lista[i][1].toString());
        
        if(suma!=0){
            for(int j=0; j<lista.length-1; j++){
                double valor=(Double.parseDouble(lista[j][1].toString())/(double)suma)*100;
                
                float f = (float )valor;

                String nuevo = "";

                for(int i=0; i<df.format(f).length(); i++)
                    if(df.format(f).charAt(i)==',')
                        nuevo=nuevo+".";
                    else
                        nuevo=nuevo+df.format(f).charAt(i);

                float imp = Float.parseFloat(nuevo);

                aux[j]=imp;
                resul=resul+imp;
            }
            aux[lista.length-1]=100-resul;
        }else{
            for(int k=0; k<lista.length; k++){
                aux[k]=(float) 0.00;
            }
        }
        return aux;
    }
}
