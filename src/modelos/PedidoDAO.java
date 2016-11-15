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

/**
 *
 * @author Cusipuma
 */
public class PedidoDAO {
    Pedido ped = new Pedido();
    
    public int estado(int cod) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos WHERE cod_pedido="+cod);
            int a = -1;
            if(!rs.equals(null))
                a=rs.getInt("estado_pedido");
            rs.close();
            st.close();
            return a;
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
    }
    
    public Object [][] listadoPedidosActivos() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos where estado_pedido<4 order by cod_pedido asc");
            int tam=0;
            while(rs.next())
                tam++;
            Object[][] lista = new Object[tam][4];
            int i=0;
            rs.close();
            rs = st.executeQuery("SELECT pedidos.cod_pedido, pedidos.estado_pedido,clientes.nombre_cliente, "
                    + "clientes.apellido_cliente, clientes.calle_cliente, clientes.num_calle_cliente, "
                    + "clientes.barrio_cliente, clientes.departamento_cliente, clientes.casa_cliente, "
                    + "clientes.localidad_cliente FROM pedidos, clientes WHERE pedidos.cod_cliente=clientes.cod_cliente "
                    + "AND pedidos.estado_pedido<4 ORDER BY cod_pedido ASC");
            while(rs.next())
            {
                lista[i][0] = rs.getInt(1);
                lista[i][1] = rs.getString(4).trim()+" "+rs.getString(3).trim();
                lista[i][2] = rs.getString(5).trim()+" "+rs.getString(6).trim()+" "+rs.getString(7).trim()+" "
                        +rs.getString(8).trim()+" "+rs.getString(9).trim()+" "+rs.getString(10).trim();
                switch (rs.getInt(2)){
                    case 0:{
                        lista[i][3] = "En espera";
                        break;
                    }
                    case 1:{
                        lista[i][3] = "En preparacion";
                        break;
                    }
                    case 2:{
                        lista[i][3] = "Listo para enviar";
                        break;
                    }
                    case 3:{
                        lista[i][3] = "Enviado";
                        break;
                    }
                }
                i++;
            }


            rs.close();
            st.close();

            return lista;

        }
    catch (Exception ex){throw new DataAccessException("Error en PedidoDAO.ListadoPedidosActivos() "+ex);}
    }
    
    public Object[][] buscarPedidoCod(int cod, String estado) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Object[][] lista = new Object[1][14];
            estado=estado.trim();
            if(estado.equals("Entregado") || estado.equals("No entregado") || estado.equals("Enviado")){
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT pedidos.observacion_pedido, pedidos.cod_detalle_pedido, "
                        + "clientes.cod_cliente, clientes.apellido_cliente, clientes.nombre_cliente, cadetes.cod_cadete, "
                        + "cadetes.apellido_cadete, cadetes.nombre_cadete, usuarios.cod_usuario, usuarios.apellido_usuario,"
                        + " usuarios.nombre_usuario, pedidos.fecha_pedido, pedidos.hora_pedido, zonas.tarifa_zona"
                        + " FROM pedidos, clientes, facturas, cadetes, usuarios,zonas WHERE "
                        + "pedidos.cod_cliente=clientes.cod_cliente AND pedidos.cod_pedido=facturas.cod_pedido AND "
                        + "pedidos.cod_zona=zonas.cod_zona AND "
                        + "facturas.cod_cadete=cadetes.cod_cadete AND pedidos.cod_usuario=usuarios.cod_usuario AND "
                        + "pedidos.estado_pedido>2 AND pedidos.estado_pedido<6 AND pedidos.cod_pedido='"+cod+"'");
                if(rs.next()){
                            lista[0][0]=rs.getString(1);
                            lista[0][1]=rs.getInt(2); //Codigo del detalle del pedido
                            lista[0][2]=rs.getInt(3);
                            lista[0][3]=rs.getString(4);
                            lista[0][4]=rs.getString(5);
                            lista[0][5]=rs.getInt(6);
                            lista[0][6]=rs.getString(7);
                            lista[0][7]=rs.getString(8);
                            lista[0][8]=rs.getInt(9);
                            lista[0][9]=rs.getString(10);
                            lista[0][10]=rs.getString(11);
                            lista[0][11]=rs.getString(12);
                            lista[0][12]=rs.getString(13);
                            lista[0][13]=rs.getFloat(14);
                }
                rs.close();
                st.close();
            }
            else{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT pedidos.observacion_pedido, pedidos.cod_detalle_pedido, "
                        + "clientes.cod_cliente, clientes.apellido_cliente, clientes.nombre_cliente,"
                        + "usuarios.cod_usuario, usuarios.apellido_usuario,"
                        + " pedidos.fecha_pedido, pedidos.hora_pedido, zonas.tarifa_zona"
                        + " usuarios.nombre_usuario FROM pedidos, clientes, usuarios, zonas WHERE "
                        + "pedidos.cod_cliente=clientes.cod_cliente AND "
                        + "pedidos.cod_usuario=usuarios.cod_usuario AND "
                        + "pedidos.cod_zona=zonas.cod_zona AND"
                        + "pedidos.estado_pedido<3 AND pedidos.cod_pedido='"+cod+"'");
                if(rs.next()){   
                    lista[0][0]=rs.getString(1);
                    lista[0][1]=rs.getInt(2); //Codigo del detalle del pedido
                    lista[0][2]=rs.getInt(3);
                    lista[0][3]=rs.getString(4);
                    lista[0][4]=rs.getString(5);
                    lista[0][5]="";
                    lista[0][6]="";
                    lista[0][7]="";
                    lista[0][8]=rs.getInt(9);
                    lista[0][9]=rs.getString(10);
                    lista[0][10]=rs.getString(11);
                    lista[0][11]=rs.getString(12);
                    lista[0][12]=rs.getString(13);
                    lista[0][13]=rs.getFloat(14);
                }
                rs.close();
                st.close(); 
            }

            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en PedidoDAO.buscarPedidoCod() "+ex);}
    }
    
    public Object[][] buscarComidasPedidoCod(int cod) throws ClassNotFoundException, SQLException, DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT comidas.descripcion_comida,detalle_pedido.cantidad_comida, comidas.precio_comida FROM comidas, "
                        + "detalle_pedido WHERE detalle_pedido.cod_comida=comidas.cod_comida "
                        + "AND detalle_pedido.cod_detalle_pedido='"+cod+"'");
                int tam=0;
                while(rs.next())
                    tam++;
                Object[][] comidas = new Object[tam][3];

                rs.close();
                st.close();

                st = con.createStatement();
                rs = st.executeQuery("SELECT comidas.descripcion_comida,detalle_pedido.cantidad_comida, comidas.precio_comida FROM comidas, "
                        + "detalle_pedido WHERE detalle_pedido.cod_comida=comidas.cod_comida "
                        + "AND detalle_pedido.cod_detalle_pedido='"+cod+"'");
                int i=0;
                while(rs.next()){
                    comidas[i][0]=rs.getString(1).trim();
                    comidas[i][1]=rs.getInt(2);
                    comidas[i][2]=rs.getFloat(3);
                    i++;
                }

                return comidas;
        }catch (Exception ex){throw new DataAccessException("Error en PedidoDAO.buscarComidasPedidoCod() "+ex);}
    }
    
}
