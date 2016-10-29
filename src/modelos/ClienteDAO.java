package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}

