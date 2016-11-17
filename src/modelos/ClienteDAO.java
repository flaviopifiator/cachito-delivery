package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {
    
    public void agregar (Cliente cliente) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes "
                    + "             (cod_cliente, nombre_cliente, apellido_cliente, fec_nac_cliente,"
                    + "              calle_cliente, num_calle_cliente,barrio_cliente, departamento_cliente,"
                    + "                 casa_cliente, localidad_cliente, observacion_cliente, activo)"
                    + "              VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1,cliente.getCodigo());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getFecha_nacimiento());
            ps.setString(5, cliente.getCalle());
            ps.setString(6, cliente.getNumero_calle());
            ps.setString(7, cliente.getBarrio());
            ps.setString(8, cliente.getDepartamento());
            ps.setString(9, cliente.getCasa());
            ps.setString(10, cliente.getLocalidad());
            ps.setString(11, cliente.getObservacion());
            ps.setBoolean(12, cliente.getActivo());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en ClienteDAO.agregar() "+ex);}
        
    }
    
    public void actualizar (Cliente cliente) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE clientes SET "
                    + "             nombre_cliente = ?, apellido_cliente = ?, fec_nac_cliente = ?,"
                    + "              calle_cliente = ?, num_calle_cliente = ?,barrio_cliente = ?, departamento_cliente = ?,"
                    + "                 casa_cliente = ?, localidad_cliente = ?, observacion_cliente = ?, activo = ? WHERE cod_cliente=?");

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getFecha_nacimiento());
            ps.setString(4, cliente.getCalle());
            ps.setString(5, cliente.getNumero_calle());
            ps.setString(6, cliente.getBarrio());
            ps.setString(7, cliente.getDepartamento());
            ps.setString(8, cliente.getCasa());
            ps.setString(9, cliente.getLocalidad());
            ps.setString(10, cliente.getObservacion());
            ps.setBoolean(11, cliente.getActivo());    
            ps.setInt(12,cliente.getCodigo());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en ClienteDAO.actualizar() "+ex);}
        
    }
    
    public void eliminar (int codigo) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM clientes WHERE codigo_cliente='"+codigo+"'");
            st.close();
        }catch (Exception ex){throw new DataAccessException("Error en ClienteDAO.eliminar() "+ex);}
        
    }
    
    
    public ArrayList buscarTodo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes order by cod_cliente");
            Cliente cliente = null;
            ArrayList lista = new ArrayList();
            while(rs.next())
            {
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cod_cliente"));
                cliente.setNombre(rs.getString("nombre_cliente").trim());
                cliente.setApellido(rs.getString("apellido_cliente").trim());
                cliente.setFecha_nacimiento(rs.getString("fec_nac_cliente").trim());
                cliente.setCalle(rs.getString("calle_cliente"));
                cliente.setNumero_calle(rs.getString("num_calle_cliente").trim());
                cliente.setBarrio(rs.getString("barrio_cliente").trim());
                cliente.setDepartamento(rs.getString("departamento_cliente").trim());
                cliente.setCasa(rs.getString("casa_cliente").trim());
                cliente.setLocalidad(rs.getString("localidad_cliente").trim());
                cliente.setObservacion(rs.getString("observacion_cliente").trim());
                cliente.setActivo(rs.getBoolean("activo"));
                lista.add(cliente);
            }
            rs.close();
            st.close();
            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en ClienteDAO.buscarTodo() "+ex);}
    
    }
    
    
    public Cliente buscarCliente(int codigo) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes where cod_cliente='"+codigo+"'");
            Cliente cliente = null;
             while(rs.next())
            {
            cliente = new Cliente();
                cliente.setCodigo(rs.getInt("cod_cliente"));
                cliente.setNombre(rs.getString("nombre_cliente").trim());
                cliente.setApellido(rs.getString("apellido_cliente").trim());
                cliente.setFecha_nacimiento(rs.getString("fec_nac_cliente").trim());
                cliente.setCalle(rs.getString("calle_cliente"));
                cliente.setNumero_calle(rs.getString("num_calle_cliente").trim());
                cliente.setBarrio(rs.getString("barrio_cliente").trim());
                cliente.setDepartamento(rs.getString("departamento_cliente").trim());
                cliente.setCasa(rs.getString("casa_cliente").trim());
                cliente.setLocalidad(rs.getString("localidad_cliente").trim());
                cliente.setObservacion(rs.getString("observacion_cliente").trim());
                cliente.setActivo(rs.getBoolean("activo"));
            }   
            
            rs.close();
            st.close();
            return cliente;
        }catch (Exception ex){throw new DataAccessException("Error en ClienteDAO.buscarCliente() "+ex);}
   
    }
    public Object[][] listadoClientes() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes order by cod_cliente asc");
            int tam=0;
            while(rs.next())
                tam++;
            Object[][] lista = new Object[tam][4];
            int i=0;
            rs.close();
            rs = st.executeQuery("SELECT * FROM clientes order by cod_cliente asc");
            while(rs.next())
            {
                lista[i][0] = rs.getInt("cod_cliente");
                lista[i][1] = rs.getString("apellido_cliente").trim();
                lista[i][2] = rs.getString("nombre_cliente").trim();
                lista[i][3] = rs.getString("calle_cliente").trim()+" "+
                              rs.getString("num_calle_cliente").trim()+" "+
                              rs.getString("barrio_cliente").trim()+" "+
                              rs.getString("departamento_cliente").trim()+" "+
                              rs.getString("casa_cliente").trim()+" "+
                              rs.getString("localidad_cliente").trim();
                i++;
            }
            rs.close();
            st.close();
            
            
            return lista;
            
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
    }
    public Object[][] telefonos(int cod) throws ClassNotFoundException, SQLException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM telefonos_clientes WHERE cod_cliente = "+cod);
        int tam=0;
        while(rs.next())
            tam++;
        Object[][] lista = new Object[tam][2];
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM telefonos_clientes WHERE cod_cliente = "+cod);
        while(rs.next())
        {
            lista[i][0] = rs.getString("descripcion").trim();
            lista[i][1] = rs.getString("telefono_cliente").trim();
            i++;
        }
        rs.close();
        st.close();
        return lista;
    }
    public Object[][] correo(int cod) throws ClassNotFoundException, SQLException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM correos WHERE cod_cliente = "+cod);
        int tam=0;
        while(rs.next())
            tam++;
        Object[][] lista = new Object[tam][2];
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM correos WHERE cod_cliente = "+cod);
        while(rs.next())
        {
            lista[i][0] = rs.getString("correo").trim();
            i++;
        }
        rs.close();
        st.close();
        return lista;
    }
    public Object[][] faltas(int cod) throws ClassNotFoundException, SQLException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM faltas WHERE cod_cliente = "+cod);
        int tam=0;
        while(rs.next())
            tam++;
        Object[][] lista = new Object[tam][2];
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM faltas WHERE cod_cliente = "+cod);
        while(rs.next())
        {
            lista[i][0] = rs.getString("descripcion_falta").trim();
            i++;
        }
        rs.close();
        st.close();
        return lista;
    }
    public Object[][] buscarText(String cod, String ape, String nom, String dom) throws DataAccessException{
        try{
                if(cod==null && ape==null && nom==null && dom==null)
                    return this.listadoClientes();
                else{
                    Object [][] real = null;
                    real=this.listadoClientes();
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
                    if (!ape.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam2; i++){
                            if (real[i][1].toString().toLowerCase().indexOf(ape.toLowerCase())==0){
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
                    if (!nom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam3; i++){
                            if (real[i][2].toString().toLowerCase().indexOf(nom.toLowerCase())==0){
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
                    if (!dom.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam4; i++){
                            if (real[i][3].toString().toLowerCase().indexOf(dom.toLowerCase())==0){
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
            
            
            
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarUsuarioText() "+ex);}
    }
    public Object[][] comidasMenu() throws ClassNotFoundException, SQLException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM comidas WHERE cantidad_comida>0 ORDER BY cod_comida ASC");
        int tam=0;
        while(rs.next())
            tam++;
        Object[][] lista = new Object[tam][4];
        int i=0;
        rs.close();
        rs = st.executeQuery("SELECT * FROM comidas WHERE cantidad_comida>0 ORDER BY cod_comida ASC");
        while(rs.next())
        {
            lista[i][0] = rs.getInt("cod_comida");
            lista[i][1] = rs.getString("descripcion_comida").trim();
            lista[i][2] = rs.getFloat("precio_comida");
            lista[i][3] = rs.getInt("cantidad_comida");
            i++;
        }
        rs.close();
        st.close();
        return lista;
    }
    
    public void insertarDetalle(Object[][] detalle) throws ClassNotFoundException, SQLException{
        for(int i=0; i<detalle.length;i++){
                Connection con = BaseDeDatos.getInstance();
                PreparedStatement ps = con.prepareStatement("INSERT INTO detalle_pedido "
                    + "            (cod_detalle_pedido, cod_comida, cantidad_comida)"
                    + "             VALUES (?,?,?) ");
                ps.setInt(1, Integer.parseInt(detalle[i][0].toString().trim()));
                ps.setInt(2, Integer.parseInt(detalle[i][1].toString().trim()));
                ps.setInt(3, Integer.parseInt(detalle[i][2].toString().trim()));
                ps.execute();
                ps.close();  
            }
    }
    
    public int buscarZonaCliente(int cod) throws SQLException, ClassNotFoundException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT cod_zona FROM clientes WHERE cod_cliente='"+cod+"'");
        while(rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    public void actualizarComidas(Object[][] comidas) throws ClassNotFoundException, SQLException{
        for(int i=0; i<comidas.length;i++){
            if(comidas[i][0]!=null){
                int cod = Integer.parseInt(comidas[i][0].toString().trim());
                int cant = Integer.parseInt(comidas[i][3].toString().trim());
                Connection con = BaseDeDatos.getInstance();
                PreparedStatement ps = con.prepareStatement("UPDATE comidas SET "
                    + "cantidad_comida = "+cant+" WHERE cod_comida="+cod);
                ps.execute();
                ps.close();
            }
              
        }
    }
    
    public void insertarPedido(Pedido ped) throws SQLException, ClassNotFoundException{
        Connection con = BaseDeDatos.getInstance();
                PreparedStatement ps = con.prepareStatement("INSERT INTO pedidos "
                    + "( cod_zona, cod_cliente, cod_detalle_pedido, estado_pedido, fecha_pedido, observacion_pedido, cod_usuario, hora_pedido) "
                    + "             VALUES (?,?,?,?,?,?,?,?) ");
                ps.setInt(1, ped.getCod_zona());
                ps.setInt(2, ped.getCod_cliente());
                ps.setInt(3, ped.getCod_detalle());
                ps.setInt(4, ped.getEstado());
                ps.setString(5, ped.getFecha());
                ps.setString(6, ped.getObservacion());
                ps.setInt(7, ped.getCod_usuario());
                ps.setString(8, ped.getHora());
                ps.execute();
                ps.close();  
    }
    
    public int obtenerCodComida(String desc) throws SQLException, ClassNotFoundException{
        desc=desc.trim();
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT cod_comida FROM comidas WHERE descripcion_comida='"+desc+"'");
        while(rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    public int obtenerDemora(int cod) throws SQLException, ClassNotFoundException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT demora_comida FROM comidas WHERE cod_comida="+cod);
        while(rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    public float obtenerPrecio(int cod) throws SQLException, ClassNotFoundException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT precio_comida FROM comidas WHERE cod_comida="+cod);
        while(rs.next())
            return rs.getInt(1);
        return -1;
    }
    
    public float obtenerTarifa(int cod) throws SQLException, ClassNotFoundException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT zonas.tarifa_zona FROM zonas, clientes WHERE zonas.cod_zona=clientes.cod_zona AND clientes.cod_cliente="+cod);
        while(rs.next())
            return rs.getFloat(1);
        return -1;
    }
    
    public int ultimoDetalle() throws ClassNotFoundException, SQLException{
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM detalle_pedido");
        int tam=-1;
        while(rs.next()){
            if(tam<rs.getInt(1))
                tam=rs.getInt(1);
        }
        return tam;
    }
    
    public Cliente buscarClienteCod(int codigo) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM clientes where cod_cliente="+codigo);
            Cliente cliente = null;
             while(rs.next())
            {
            cliente = new Cliente();
                cliente.setCodigo(rs.getInt(1));
                cliente.setNombre(rs.getString(2).trim());
                cliente.setApellido(rs.getString(3).trim());
                cliente.setFecha_nacimiento(rs.getString(4).trim());
                cliente.setCalle(rs.getString(5));
                cliente.setNumero_calle(rs.getString(6).trim());
                cliente.setBarrio(rs.getString(7).trim());
                cliente.setDepartamento(rs.getString(8).trim());
                cliente.setCasa(rs.getString(9).trim());
                cliente.setLocalidad(rs.getString(10).trim());
                cliente.setObservacion(rs.getString(11).trim());
                cliente.setActivo(rs.getBoolean(12));
                int cod_zona=rs.getInt(13);
            }   
            
            rs.close();
            st.close();
            return cliente;
        }catch (Exception ex){throw new DataAccessException("Error en ClienteDAO.buscarCliente() "+ex);}
   
    }
}

