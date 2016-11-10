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
        
        Object[][] lista = new Object[tam][5];

        for(int i=0; i<lista.length; i++){
            lista[i][1]=0;
            lista[i][2]=0;
        }
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM cadetes");

        while(rs.next()){
            lista[i][0]=rs.getInt("cod_cadete");
            
            String nombre = rs.getString("nombre_cadete").trim();
            String apellido = rs.getString("apellido_cadete").trim();
            
            lista[i][4]=nombre;
            
            lista[i][3]=apellido;
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
        
        lista=burbuja(lista,5);
        Object[][] nueva;
        
        if(lista.length>cant){
            nueva = new Object[cant][5];
            for(int j=0; j<cant; j++){
                nueva[j][0]=lista[j][0];
                nueva[j][1]=lista[j][1];
                nueva[j][2]=lista[j][2];
                nueva[j][3]=lista[j][3];
                nueva[j][4]=lista[j][4];
            }
        }else{
            nueva = new Object[lista.length][5];
            nueva=lista;
        }
        if(Integer.parseInt(nueva[0][1].toString())==0 && Integer.parseInt(nueva[0][2].toString())==0){
            Object[][] nueva2 = new Object[1][3];
            nueva2[0][0]="CHINITO";
            nueva2[0][1]="";
            nueva2[0][2]="";
            
            return nueva2;
        }
        
        st.close();
        return nueva;
    }
    
    public Object[][] burbuja(Object[][] lista, int cant){
        int i, j;
        Object[][] aux = new Object[1][cant];
        for(i=0;i<lista.length-1;i++)
            for(j=0;j<lista.length-i-1;j++)
                if(Integer.parseInt(lista[j+1][1].toString())>Integer.parseInt(lista[j][1].toString())){
                    for (int k=0; k<cant; k++) {
                        aux[0][k]=lista[j+1][k];
                        lista[j+1][k]=lista[j][k];
                        lista[j][k]=aux[0][k];
                    }
                }
        return lista;
    }
    
    public Object[][] burbujaF(Object[][] lista){
        int i, j;
        Object[][] aux= new Object[1][3];
        for(i=0;i<lista.length-1;i++)
            for(j=0;j<lista.length-i-1;j++)
                if(Float.parseFloat(lista[j+1][2].toString())>Float.parseFloat(lista[j][2].toString())){
                    for (int k = 0; k < 3; k++) {
                        aux[0][k]=lista[j+1][k];
                        lista[j+1][k]=lista[j][k];
                        lista[j][k]=aux[0][k];
                    }
                    
                }
        
        return lista;
    }
    
    public float[] porcentaje(Object[][] lista, int pos){
        float[] aux = new float[lista.length];
        int suma=0;
        float resul=0;
        
        DecimalFormat df = new DecimalFormat("0.00"); 
        
        for(int i=0; i<lista.length; i++)
            suma=suma+Integer.parseInt(lista[i][pos].toString());
        
        if(suma!=0){
            for(int j=0; j<lista.length-1; j++){
                double valor=(Double.parseDouble(lista[j][pos].toString())/(double)suma)*100;
                
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
            float fin = 100-resul;
            String nuevo = "";

            for(int i=0; i<df.format(fin).length(); i++)
                if(df.format(fin).charAt(i)==',')
                    nuevo=nuevo+".";
                else
                    nuevo=nuevo+df.format(fin).charAt(i);

            aux[lista.length-1] = Float.parseFloat(nuevo);
            
        }else{
            for(int k=0; k<lista.length; k++){
                aux[k]=(float) 0.00;
            }
        }
        return aux;
    }
    public float[] porcentajeF(Object[][] lista, int pos){
        float[] aux = new float[lista.length];
        float suma=0;
        float resul=0;
        
        DecimalFormat df = new DecimalFormat("0.00"); 
        
        for(int i=0; i<lista.length; i++)
            suma=suma+Float.parseFloat(lista[i][pos].toString());
        
        if(suma!=0){
            for(int j=0; j<lista.length-1; j++){
                double valor=(Double.parseDouble(lista[j][pos].toString())/(double)suma)*100;
                
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
            float fin = 100-resul;
            String nuevo = "";

            for(int i=0; i<df.format(fin).length(); i++)
                if(df.format(fin).charAt(i)==',')
                    nuevo=nuevo+".";
                else
                    nuevo=nuevo+df.format(fin).charAt(i);

            aux[lista.length-1] = Float.parseFloat(nuevo);
            
        }else{
            for(int k=0; k<lista.length; k++){
                aux[k]=(float) 0.00;
            }
        }
        
        
        return aux;
    }
    
    public String porFecha(String tabla, String min, String max, String campo, String fecha) throws ClassNotFoundException, SQLException, ParseException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM "+tabla);
        
        String consulta ="";
        int i=0;
        Fecha f = new Fecha();
        
        while(rs.next()){
            if(f.rango(min, rs.getString(fecha), max)){
                if(i==0)
                    consulta=" AND ( "+tabla+"."+campo+" = "+rs.getString(campo)+" ";
                else
                    consulta=consulta+" OR "+tabla+"."+campo+"="+rs.getString(campo);
                i++;
            }
        }
        consulta=consulta+")";
        st.cancel();
        rs.close();
        if(i==0)
            return "";
        
        return consulta;        
    }
    
   
    public Object[][] comidas(String min, int cant, String max) throws ClassNotFoundException, SQLException, ParseException{
        String consulta = this.porFecha("facturas", min, max, "cod_factura", "fecha_factura");
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        
        if(consulta==""){
            Object[][] nueva = new Object[1][3];
            nueva[0][0]="CHINITO";
            nueva[0][1]="";
            nueva[0][2]="";
            
            return nueva;
        }   
        
        ResultSet rs = st.executeQuery
        ("SELECT detalle_pedido.cod_comida, comidas.descripcion_comida, sum(detalle_pedido.cantidad_comida) AS cant "+
         "FROM pedidos, comidas, detalle_pedido, facturas "+
         "WHERE pedidos.cod_detalle_pedido = detalle_pedido.cod_detalle_pedido AND detalle_pedido.cod_comida = comidas.cod_comida AND "+
         "facturas.cod_pedido=pedidos.cod_pedido AND pedidos.estado_pedido=4 "+consulta+ 
         " GROUP BY  detalle_pedido.cod_comida, comidas.descripcion_comida "+
         "ORDER BY cant DESC ");
        
        int tam=0;
        
        while(rs.next())
            tam++;
        
        rs.close();
        
        Object[][] lista = new Object[tam][3];
        
        rs = st.executeQuery
        ("SELECT detalle_pedido.cod_comida, comidas.descripcion_comida, sum(detalle_pedido.cantidad_comida) AS cant "+
         "FROM pedidos, comidas, detalle_pedido, facturas "+
         "WHERE pedidos.cod_detalle_pedido = detalle_pedido.cod_detalle_pedido AND detalle_pedido.cod_comida = comidas.cod_comida AND "+
         "facturas.cod_pedido=pedidos.cod_pedido AND pedidos.estado_pedido=4 "+consulta+ 
         " GROUP BY  detalle_pedido.cod_comida, comidas.descripcion_comida "+
         "ORDER BY cant DESC");
        
        int i=0;
        while(rs.next()){
            lista[i][0]=rs.getInt("cod_comida");
            lista[i][1]=rs.getString("descripcion_comida").trim();
            lista[i][2]=rs.getInt("cant");
            i++;
        }
        rs.close();
        st.close();
        
        Object[][] nueva;
        
        if(lista.length>cant){
            nueva = new Object[cant][3];
            for(int j=0; j<cant; j++){
                nueva[j][0]=lista[j][0];
                nueva[j][1]=lista[j][1];
                nueva[j][2]=lista[j][2];
            }
        }else{
            nueva = new Object[lista.length][3];
            nueva=lista;
        }
        return nueva;
    }
    
    public Object[][] meses(String min, int cant, String max) throws ClassNotFoundException, SQLException, ParseException{
        String consulta = this.porFecha("facturas", min, max, "cod_factura", "fecha_factura");
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        
        if(consulta==""){
            Object[][] nueva = new Object[1][3];
            nueva[0][0]="CHINITO";
            nueva[0][1]="";
            nueva[0][2]="";
            
            return nueva;
        }        
                
                
        ResultSet rs = st.executeQuery(      
         "SELECT facturas.fecha_factura, facturas.cod_factura, facturas.tarifa_zona , facturas.importe_total_facturado "+
         "FROM facturas, pedidos "+
         "WHERE facturas.cod_pedido = pedidos.cod_pedido AND pedidos.estado_pedido = 4 "+consulta  
        );
        
        int tam=0;
        
        while(rs.next())
            tam++;
        rs.close();
        
        rs = st.executeQuery(
         "SELECT facturas.fecha_factura, facturas.cod_factura, facturas.tarifa_zona , facturas.importe_total_facturado "+
         "FROM facturas, pedidos "+
         "WHERE facturas.cod_pedido = pedidos.cod_pedido AND pedidos.estado_pedido = 4 "+consulta  
        );
              
        
        int k=0;
        Object[][] meses = new Object[tam][3];
        
        for (int j = 0; j < meses.length; j++) {
            meses[j][1]=0;
            meses[j][2]=0;
        }
        
        while(rs.next()){
            if (k==0){
                meses[0][0]=this.mesAnio(rs.getString("fecha_factura"));
                meses[0][1]=1;
                meses[0][2]=rs.getFloat("tarifa_zona")+rs.getFloat("importe_total_facturado");;
                k=1;
            
            }else{
                boolean insertar = true;
                for (int i = 0; i < k; i++) {
                    if(meses[i][0].toString().equals(this.mesAnio(rs.getString("fecha_factura")))){
                        insertar=false;
                        meses[i][1]=Integer.parseInt(meses[i][1].toString())+1;
                        meses[i][2]=Float.parseFloat(meses[i][2].toString())+rs.getFloat("tarifa_zona")+rs.getFloat("importe_total_facturado");
                    }
                        
                }
                if(insertar){
                    meses[k][0]=this.mesAnio(rs.getString("fecha_factura"));
                    meses[k][1]=1;
                    meses[k][2]=rs.getFloat("tarifa_zona")+rs.getFloat("importe_total_facturado");
                    k++;
                }
            }
        }
        rs.close();
        st.close();
        
        meses=burbujaF(meses);
        
        Object[][] lista = new Object[k][3];
        for (int i = 0; i < k; i++) {
            lista[i][0]=meses[i][0];
            lista[i][1]=meses[i][1];
            lista[i][2]=meses[i][2];
        }
        
        Object[][] nueva;
        
        if(lista.length>cant){
            nueva = new Object[cant][5];
            for(int j=0; j<cant; j++){
                nueva[j][0]=lista[j][0];
                nueva[j][1]=lista[j][1];
                nueva[j][2]=lista[j][2];
                nueva[j][3]=lista[j][3];
                nueva[j][4]=lista[j][4];
            }
        }else{
            nueva = new Object[lista.length][5];
            nueva=lista;
        }
        
        return nueva;
    }
    
    public String mesAnio(String fecha){
        String meses = "";
        boolean holi = true;
        boolean copiar = false;
        
        for (int i = 0; i < fecha.length(); i++) {
            if(copiar)
                meses=meses+fecha.charAt(i);
            if(fecha.charAt(i)=='/' && holi)
                copiar = true;
        }
        return meses;
    }
    
    public Object[][] zonas(String min, int cant, String max) throws ClassNotFoundException, SQLException, ParseException{
        
        String consulta = this.porFecha("facturas", min, max, "cod_factura", "fecha_factura");
        
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        
        if(consulta==""){
            Object[][] nueva = new Object[1][3];
            nueva[0][0]="CHINITO";
            nueva[0][1]="";
            nueva[0][2]="";
            
            return nueva;
        }
        
        ResultSet rs = st.executeQuery(      
          "SELECT zonas.cod_zona, zonas.descripcion_zona, count(pedidos.cod_pedido) as pedidos "+
          "FROM pedidos, zonas, facturas "+
          "WHERE pedidos.cod_pedido = facturas.cod_pedido AND pedidos.cod_zona = zonas.cod_zona AND pedidos.estado_pedido = 4 "+consulta+
          "GROUP BY zonas.cod_zona, zonas.descripcion_zona "+
          "ORDER BY pedidos DESC"
          );
        int tam=0;
        
        while(rs.next())
            tam++;
        rs.close();
        rs = st.executeQuery(      
          "SELECT zonas.cod_zona, zonas.descripcion_zona, count(pedidos.cod_pedido) as pedidos "+
          "FROM pedidos, zonas, facturas "+
          "WHERE pedidos.cod_pedido = facturas.cod_pedido AND pedidos.cod_zona = zonas.cod_zona AND pedidos.estado_pedido = 4 "+consulta+
          "GROUP BY zonas.cod_zona, zonas.descripcion_zona "+
          "ORDER BY pedidos DESC"
          );
        
        Object[][] lista = new Object[tam][3];
        int i=0;
        
        while(rs.next()){
            lista[i][0]=rs.getInt("cod_zona");
            lista[i][1]=rs.getInt("pedidos");
            lista[i][2]=rs.getString("descripcion_zona");
            i++;
        }
        Object[][] nueva;
        
        if(lista.length>cant){
            nueva = new Object[cant][3];
            for(int j=0; j<cant; j++){
                nueva[j][0]=lista[j][0];
                nueva[j][1]=lista[j][1];
                nueva[j][2]=lista[j][2];
            }
        }else{
            nueva = new Object[lista.length][3];
            nueva=lista;
        }
        
        
        return nueva;
    }
}
