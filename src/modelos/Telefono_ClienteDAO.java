package modelos;
import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Telefono_ClienteDAO {
    public void agregar (Telefono_Cliente telefono) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO telefonos_clientes"
                    + "            (cod_cliente, telefono_cliente, descripcion)"
                    + "             VALUES (?,?,?) ");
       
        ps.setInt(1, telefono.getCodigo_cliente());
        ps.setString(2, telefono.getTelefono());
        ps.setString(3, telefono.getDescripcion());
        ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en Telefono_ClienteDAO.agregar() "+ex);}
        
    }
    
    public void actualizar (Telefono_Cliente telefono) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE telefonos_clientes SET "
                    +                    "             telefono_cliente = ?, descripcion = ? "
                    +                           "WHERE cod_cliente=?");

            ps.setString(1, telefono.getTelefono());
            ps.setString(2, telefono.getDescripcion());
            ps.setInt(3, telefono.getCodigo_cliente());
            
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en Telefono_ClienteDAO.actualizar() "+ex);}
        
    }
    
    public void eliminar (int cod_cliente) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM telefonos_clientes WHERE cod_cliente='"+cod_cliente+"'");
            st.close();
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_ClienteDAO.eliminar() "+ex);}
        
    }
    
    public ArrayList buscarTodo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonos_clientes order by cod_cliente");
            Telefono_Cliente telefono = null;
            ArrayList lista = new ArrayList();
            while(rs.next())
            {
                telefono = new Telefono_Cliente();
                telefono.setCodigo_cliente(rs.getInt("cod_cliente"));
                telefono.setTelefono(rs.getString("telefono_cliente").trim());
                telefono.setDescripcion(rs.getString("descripcion").trim());
                lista.add(telefono);
            }
            rs.close();
            st.close();
            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_ClienteDAO.buscarTodo() "+ex);}
    
    }
    
    
    public Telefono_Cliente buscarTelefono_Cliente(int cod_cliente) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonos_clientes where cod_cliente='"+cod_cliente+"'");
            Telefono_Cliente telefono = null;
             while(rs.next())
            {
                telefono = new Telefono_Cliente();
                telefono.setCodigo_cliente(rs.getInt("cod_cliente"));
                telefono.setTelefono(rs.getString("telefono_cliente").trim());
                telefono.setDescripcion(rs.getString("descripcion").trim());
            }   
            
            rs.close();
            st.close();
            return telefono;
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_ClienteDAO.buscarCliente() "+ex);}
   
    } 
    
}