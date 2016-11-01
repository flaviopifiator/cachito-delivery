package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.FileInputStream;

public class UsuarioDAO {
    Telefono_UsuarioDAO telDAO = new Telefono_UsuarioDAO();
    public void agregar (Usuario usuario) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO usuarios (dni_usuario, "
                    + "nombre_usuario, apellido_usuario, contraseña_usuario, cargo_usuario, "
                    + "foto_usuario, activo, foto ) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getPass());
            ps.setInt(5, usuario.getCargo());
            ps.setString(6, usuario.getFoto());
            ps.setBoolean(7, usuario.getActivo()); 
            ps.setBinaryStream(8, usuario.fis,usuario.longitud);
            ps.execute();
            telDAO.agregar(usuario.getTel());
        }catch(Exception ex){throw new DataAccessException("Error en UsuarioDAO.agregar() "+ex);}
        
    }
    
    public void actualizar (Usuario usuario) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET nombre_usuario=?, apellido_usuario=?, contraseña_usuario=?, cargo_usuario=? ,foto_usuario=?,activo=? WHERE dni_usuario=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getPass());
            ps.setInt(4, usuario.getCargo());
            ps.setString(5, usuario.getFoto());
            ps.setBoolean(6, usuario.getActivo());
            ps.setInt(7, usuario.getDni());            
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en UsuarioDAO.actualizar() "+ex);}
        
    }
    
    public void eliminar (int dni) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM usuarios WHERE dni_usuario='"+dni+"'");
            st.close();
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.eliminar() "+ex);}
        
    }
    
    
    public ArrayList buscarTodo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios order by dni_usuario");
            Usuario user = null;
            ArrayList lista = new ArrayList();
            while(rs.next())
            {
                user = new Usuario();
                user.setDni(rs.getInt("dni_usuario"));
                user.setNombre(rs.getString("nombre_usuario").trim());
                user.setApellido(rs.getString("apellido_usuario").trim());
                user.setPass(rs.getString("contraseña_usuario").trim());
                user.setCargo(rs.getInt("cargo_usuario"));
                user.setFoto(rs.getString("foto_usuario").trim());
                user.setActivo(rs.getBoolean("activo"));
                lista.add(user);
            }
            rs.close();
            st.close();
            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarTodo() "+ex);}
    
    }
    
    
    public Usuario buscarUsuario(int dni) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios where dni_usuario='"+dni+"'");
            Usuario user = null;
             while(rs.next())
            {
            
                user = new Usuario();
                user.setDni(rs.getInt("dni_usuario"));
                user.setNombre(rs.getString("nombre_usuario").trim());
                user.setApellido(rs.getString("apellido_usuario").trim());
                user.setPass(rs.getString("contraseña_usuario").trim());
                user.setCargo(rs.getInt("cargo_usuario"));
                user.setFoto(rs.getString("foto_usuario").trim());
                user.setActivo(rs.getBoolean("activo"));
            }   
            
            rs.close();
            st.close();
            return user;
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
   
    } 
}

