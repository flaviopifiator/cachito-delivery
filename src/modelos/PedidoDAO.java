/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public Object [][] listadoPedidosCancelados() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos where estado_pedido=9 order by cod_pedido asc");
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
                    + "AND pedidos.estado_pedido=9 ORDER BY cod_pedido ASC");
            while(rs.next())
            {
                lista[i][0] = rs.getInt(1);
                lista[i][1] = rs.getString(4).trim()+" "+rs.getString(3).trim();
                lista[i][2] = rs.getString(5).trim()+" "+rs.getString(6).trim()+" "+rs.getString(7).trim()+" "
                        +rs.getString(8).trim()+" "+rs.getString(9).trim()+" "+rs.getString(10).trim();
                lista[i][3] ="Cancelado";
                i++;
            }


            rs.close();
            st.close();

            return lista;

        }
    catch (Exception ex){throw new DataAccessException("Error en PedidoDAO.ListadoPedidosActivos() "+ex);}
    }
        
    public Object [][] listadoPedidosHistoricos() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedidos where estado_pedido>3 AND estado_pedido<6 order by cod_pedido asc");
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
                    + "AND estado_pedido>3 AND estado_pedido<6 ORDER BY cod_pedido ASC");
            while(rs.next())
            {
                lista[i][0] = rs.getInt(1);
                lista[i][1] = rs.getString(4).trim()+" "+rs.getString(3).trim();
                lista[i][2] = rs.getString(5).trim()+" "+rs.getString(6).trim()+" "+rs.getString(7).trim()+" "
                        +rs.getString(8).trim()+" "+rs.getString(9).trim()+" "+rs.getString(10).trim();
                switch (rs.getInt(2)){
                    case 4:{
                        lista[i][3] = "Entregado";
                        break;
                    }
                    case 5:{
                        lista[i][3] = "No entregado";
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
                        + " usuarios.cod_usuario, usuarios.apellido_usuario, usuarios.nombre_usuario, "
                        + " pedidos.fecha_pedido, pedidos.hora_pedido, zonas.tarifa_zona"
                        + " FROM pedidos, clientes, usuarios, zonas WHERE "
                        + "pedidos.cod_cliente=clientes.cod_cliente AND "
                        + "pedidos.cod_usuario=usuarios.cod_usuario AND "
                        + "pedidos.cod_zona=zonas.cod_zona AND "
                        + "pedidos.cod_pedido='"+cod+"'");
                if(rs.next()){   
                    lista[0][0]=rs.getString(1);
                    lista[0][1]=rs.getInt(2); //Codigo del detalle del pedido
                    lista[0][2]=rs.getInt(3);
                    lista[0][3]=rs.getString(4);
                    lista[0][4]=rs.getString(5);
                    lista[0][5]="";
                    lista[0][6]="";
                    lista[0][7]="";
                    lista[0][8]=rs.getInt(6);
                    lista[0][9]=rs.getString(7);
                    lista[0][10]=rs.getString(8);
                    lista[0][11]=rs.getString(9);
                    lista[0][12]=rs.getString(10);
                    lista[0][13]=rs.getFloat(11);
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
                Object[][] comidas = new Object[tam][4];

                rs.close();
                st.close();

                st = con.createStatement();
                rs = st.executeQuery("SELECT comidas.descripcion_comida,detalle_pedido.cantidad_comida, comidas.precio_comida, comidas.demora_comida "
                        + "FROM comidas, detalle_pedido WHERE detalle_pedido.cod_comida=comidas.cod_comida "
                        + "AND detalle_pedido.cod_detalle_pedido='"+cod+"'");
                int i=0;
                while(rs.next()){
                    comidas[i][0]=rs.getString(1).trim();
                    comidas[i][1]=rs.getInt(2);
                    comidas[i][2]=rs.getFloat(3);
                    comidas[i][3]=rs.getString(4);
                    i++;
                }

                return comidas;
        }catch (Exception ex){throw new DataAccessException("Error en PedidoDAO.buscarComidasPedidoCod() "+ex);}
    }

    
    public Object[][] buscarPedidoText(String cod, String apeNom, String dom, String estado, int tipo) throws DataAccessException{
        try{
            switch (tipo){
                case 1:{        //ACTIVOS
                    if(cod==null && apeNom==null && dom==null && estado==null)
                        return listadoPedidosActivos();
                    Object [][] real = null;
                    real= listadoPedidosActivos();
                    int tam=real.length;
                    int tam2=real.length;
                    int tam3=real.length;
                    int tam4=real.length;
                    if(real==null)
                        return real;
                    if (!cod.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam; i++){
                            if (real[i][0].toString().toLowerCase().indexOf(cod.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;
                            }                            
                        }
                        for(int k=j; k<tam; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam2--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!apeNom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam2; i++){
                            if (real[i][1].toString().toLowerCase().indexOf(apeNom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam2; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!dom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam3; i++){
                            if (real[i][2].toString().toLowerCase().indexOf(dom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam3; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam4--;
                        }

                    }
                    if (!estado.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam4; i++){
                            if (real[i][3].toString().toLowerCase().indexOf(estado.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam4; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam3--;
                        }

                    }

                    return real;
                }
                case 2:{        //HISTORICOS
                    if(cod==null && apeNom==null && dom==null && estado==null)
                        return listadoPedidosHistoricos();
                    Object [][] real = null;
                    real= listadoPedidosHistoricos();
                    int tam=real.length;
                    int tam2=real.length;
                    int tam3=real.length;
                    int tam4=real.length;
                    if(real==null)
                        return real;
                    if (!cod.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam; i++){
                            if (real[i][0].toString().toLowerCase().indexOf(cod.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;
                            }                            
                        }
                        for(int k=j; k<tam; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam2--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!apeNom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam2; i++){
                            if (real[i][1].toString().toLowerCase().indexOf(apeNom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam2; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!dom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam3; i++){
                            if (real[i][2].toString().toLowerCase().indexOf(dom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam3; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam4--;
                        }

                    }
                    if (!estado.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam4; i++){
                            if (real[i][3].toString().toLowerCase().indexOf(estado.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam4; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam3--;
                        }

                    }

                    return real;
                }
                case 3:{        //CANCELADOS
                    if(cod==null && apeNom==null && dom==null && estado==null)
                        return listadoPedidosCancelados();
                    Object [][] real = null;
                    real= listadoPedidosCancelados();
                    int tam=real.length;
                    int tam2=real.length;
                    int tam3=real.length;
                    int tam4=real.length;
                    if(real==null)
                        return real;
                    if (!cod.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam; i++){
                            if (real[i][0].toString().toLowerCase().indexOf(cod.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;
                            }                            
                        }
                        for(int k=j; k<tam; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam2--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!apeNom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam2; i++){
                            if (real[i][1].toString().toLowerCase().indexOf(apeNom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam2; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam3--;
                            tam4--;
                        }

                    }
                    if (!dom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam3; i++){
                            if (real[i][2].toString().toLowerCase().indexOf(dom.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam3; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam4--;
                        }

                    }
                    if (!estado.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam4; i++){
                            if (real[i][3].toString().toLowerCase().indexOf(estado.toLowerCase())==0){
                                real[j][0]=real[i][0];
                                real[j][1]=real[i][1];
                                real[j][2]=real[i][2];
                                real[j][3]=real[i][3];
                                j++;

                            }
                        }
                        for(int k=j; k<tam4; k++){
                            real[k][0]=null;
                            real[k][1]=null;
                            real[k][2]=null;
                            real[k][3]=null;
                            tam--;
                            tam2--;
                            tam3--;
                        }

                    }

                    return real;
                }
                    
            }

                
         return null;   
            
        }catch (Exception ex){throw new DataAccessException("Error en PedidosDAO.buscarPedidoText() "+ex);}
   
    } 
    
    public void actualizarEstadoPedido(int cod_pedido, int estado) throws SQLException, ClassNotFoundException, DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE pedidos SET estado_pedido='"+estado+"' WHERE cod_pedido='"+cod_pedido+"'");
            ps.execute();
            ps.close();
        }catch (ClassNotFoundException | SQLException ex){throw new DataAccessException("Error en PedidoDAO.ActualizarEstadoPedido() "+ex);}
    }
    

    
}
