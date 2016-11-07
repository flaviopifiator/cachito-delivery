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
import javax.swing.JOptionPane;

/**
 *
 * @author Cusipuma
 */
public class FacturaDAO {
    Factura facfac = new Factura();
    PedidoDAO pedido = new PedidoDAO();
    
//    public Object[][] cadetes(String min, int cant, String max) throws SQLException, DataAccessException{
//        try{
//            
//            
//            
//            Connection con = BaseDeDatos.getInstance();
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM facturas");
//            int tam=0;
//            while(rs.next())
//                tam++;
//            Object[][] lista = new Object[tam][3];
//            
//            for(int i=0; i<lista.length; i++){
//                lista[i][1]=0;
//                lista[i][2]=0;
//            }
//            int i=0, j=1;
//            rs.close();
//            rs = st.executeQuery("SELECT * FROM facturas");
//            
//            while(rs.next()){
//                int estado = pedido.estado(rs.getInt("cod_pedido"));
//                boolean pase = true;
//                //Para el primer elemento
//                if(i==0){
//                    lista[0][0]=rs.getInt("cod_cadete");
//                    if(estado==-1){
//                        System.out.println("Pedido "+rs.getInt("cod_pedido")+" no encontrado");
//                        return null;
//                    }else{
//                        if(estado==1)//ENTREGADO
//                            lista[0][1]=1;
//                        if(estado==0)//Cancelado
//                            lista[0][2]=1;
//                    }
//                    i++;
//                }else{
//                    for(int k=0; k<j; k++){
//                        if(lista[k][0].equals(rs.getInt("cod_cadete"))){
//                            if(estado==-1){
//                                System.out.println("Pedido "+rs.getInt("cod_pedido")+" no encontrado");
//                                return null;
//                            }else{
//                                if(estado==1)//ENTREGADO
//                                    lista[k][1]=Integer.parseInt(lista[k][1].toString())+1;
//                                if(estado==0)//Cancelado
//                                    lista[k][2]=Integer.parseInt(lista[k][2].toString())+1;
//                            }
//                            pase=false;
//                        }
//                        
//                    }
//                    if(pase){
//                        lista[i][0]=rs.getInt("cod_cadete");
//                        if(estado==-1){
//                            System.out.println("Pedido "+rs.getInt("cod_pedido")+" no encontrado");
//                            return null;
//                        }else{
//                            if(estado==1)//ENTREGADO
//                                lista[i][1]=Integer.parseInt(lista[k][1].toString())+1;
//                            if(estado==0)//Cancelado
//                                lista[i][2]=Integer.parseInt(lista[k][2].toString())+1;
//                        }
//                    }
//                }//NO OLVIDARME I++
//            }
//            rs.close();
//            st.close();
//            return lista;
//            
//        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
//    }
//        
//        
//        
//        
//    } 
}
