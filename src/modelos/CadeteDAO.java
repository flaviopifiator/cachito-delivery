package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CadeteDAO {
    
    public void agregar (Cadete cadete) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cadetes "
                    + "             (dni_cadete, nombre_cadete, apellido_cadete,"
                    + "estado_cadete, foto_cadete, activo VALUES (?,?,?,?,?,?)");
            ps.setInt(1,cadete.getDni());
            ps.setString(2, cadete.getNombre());
            ps.setString(3, cadete.getApellido());
            ps.setInt(4, cadete.getEstado());
            ps.setString(5, cadete.getFoto());
            ps.setBoolean(6, cadete.getActivo());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en CadeteDAO.agregar() "+ex);}
        
    }
    
    public void actualizar (Cadete cadete) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE cadetes SET "
                    + "             nombre_cadete = ?, apellido_cadete = ?, estado_cadete = ?,"
                    + "              foto_cadete = ?, activo = ? WHERE dni_cadete=?");

            ps.setString(1, cadete.getNombre());
            ps.setString(2, cadete.getApellido());
            ps.setInt(3, cadete.getEstado());
            ps.setString(4, cadete.getFoto());
            ps.setBoolean(5, cadete.getActivo());
            ps.setInt(6, cadete.getDni());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en CadeteDAO.actualizar() "+ex);}
        
    }
    
    public void eliminar (int dni) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM cadetes WHERE codigo_cliente='"+dni+"'");
            st.close();
        }catch (Exception ex){throw new DataAccessException("Error en CadeteDAO.eliminar() "+ex);}
        
    }
    
    
    public ArrayList buscarTodo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cadetes order by dni_cadete");
            Cadete cadete = null;
            ArrayList lista = new ArrayList();
            while(rs.next())
            {
                cadete = new Cadete();
                cadete.setDni(rs.getInt("dni_cadete"));
                cadete.setNombre(rs.getString("nombre_cadete").trim());
                cadete.setApellido(rs.getString("apellido_cadete").trim());
                cadete.setEstado(rs.getInt("estado_cadete"));
                cadete.setFoto(rs.getString("foto_cadete").trim());
                cadete.setActivo(rs.getBoolean("activo"));
                lista.add(cadete);
            }
            rs.close();
            st.close();
            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en CadeteDAO.buscarTodo() "+ex);}
    
    }
    
    
    public Cadete buscarCadete(int dni) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cadetes where dni_cadete='"+dni+"'");
            Cadete cadete = null;
             while(rs.next())
            {
                cadete = new Cadete();
                cadete.setDni(rs.getInt("dni_cadete"));
                cadete.setNombre(rs.getString("nombre_cadete").trim());
                cadete.setApellido(rs.getString("apellido_cadete").trim());
                cadete.setEstado(rs.getInt("estado_cadete"));
                cadete.setFoto(rs.getString("foto_cadete").trim());
                cadete.setActivo(rs.getBoolean("activo"));
            }   
            
            rs.close();
            st.close();
            return cadete;
        }catch (Exception ex){throw new DataAccessException("Error en CadeteDAO.buscarCadete() "+ex);}
   
    } 
}
    
