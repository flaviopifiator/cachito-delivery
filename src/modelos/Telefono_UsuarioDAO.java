package modelos;
import Excepciones.DataAccessException;
import Excepciones.TelefonoUsuarioInexistenteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Telefono_UsuarioDAO {
    
    public void agregar (Telefono_Usuario telefono) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO telefonos_usuarios"
                    + "            (dni_usuario, telefono_usuario, descripcion)"
                    + "             VALUES (?,?,?) ");
       
        ps.setInt(1, telefono.getDni_usuario());
        ps.setString(2, telefono.getTelefono());
        ps.setString(3, telefono.getDescripcion());
        ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.agregar() "+ex);}
        
    }
    
    public void actualizar (Telefono_Usuario telefono) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE telefonos_usuarios SET "
                    +                    "             telefono_usuario = ?, descripcion = ? "
                    +                           "WHERE dni_usuario=?");

            ps.setString(1, telefono.getTelefono());
            ps.setString(2, telefono.getDescripcion());
            ps.setInt(3, telefono.getDni_usuario());
            
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.actualizar() "+ex);}
        
    }
    
        public void eliminar (int cod_usuario) throws DataAccessException{
            try{
                Connection con = BaseDeDatos.getInstance();
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM telefonos_usuarios WHERE cod_usuario='"+cod_usuario+"'");
                st.close();
            }catch (Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.eliminar() "+ex);}

        }
                
        public void actualizarGabe(Object[][] telefonos) throws DataAccessException, TelefonoUsuarioInexistenteException{
            if(telefonos.length==0)
                throw new TelefonoUsuarioInexistenteException("No hay ningun telefono agregado.");
                
            this.eliminar(Integer.parseInt(telefonos[0][0].toString()));
                this.agregarGabe(telefonos);

        }
    
    public ArrayList buscarTodo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonos_usuarios order by dni_usuario");
            Telefono_Usuario telefono = null;
            ArrayList lista = new ArrayList();
            while(rs.next())
            {
                telefono = new Telefono_Usuario();
                telefono.setDni_usuario(rs.getInt("dni_usuario"));
                telefono.setTelefono(rs.getString("telefono_usuario").trim());
                telefono.setDescripcion(rs.getString("descripcion").trim());
                lista.add(telefono);
            }
            rs.close();
            st.close();
            return lista;
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.buscarTodo() "+ex);}
    
    }
    
    
    public Telefono_Usuario buscarTelefono_Usuario(int dni_usuario) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonos_usuarios where dni_usuario='"+dni_usuario+"'");
            Telefono_Usuario telefono = null;
             while(rs.next())
            {
                telefono = new Telefono_Usuario();
                telefono.setDni_usuario(rs.getInt("dni_usuario"));
                telefono.setTelefono(rs.getString("telefono_usuario").trim());
                telefono.setDescripcion(rs.getString("descripcion").trim());
            }   
            
            rs.close();
            st.close();
            return telefono;
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.buscarTelefono_Usuario() "+ex);}
   
    } 
    
    public boolean existeTelefono_Usuario(int cod_usuario, String numero) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM telefonos_usuarios where cod_usuario='"+cod_usuario+"' and telefono_usuario='"+numero+"'");
             while(rs.next())
            {
                return true;
            }   
            
            return false;
        }catch (Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.existeTelefono_Usuario() "+ex);}
   
    } 
    
        public void agregarGabe(Object[][] telefonos) throws DataAccessException, TelefonoUsuarioInexistenteException{
                if(telefonos.length==0)
                    throw new TelefonoUsuarioInexistenteException("No hay ningun telefono agregado.");
            try{
            for(int i=0; i<telefonos.length;i++){
                Connection con = BaseDeDatos.getInstance();
                PreparedStatement ps = con.prepareStatement("INSERT INTO telefonos_usuarios"
                    + "            (cod_usuario, telefono_usuario, descripcion)"
                    + "             VALUES (?,?,?) ");
                ps.setInt(1, Integer.parseInt(telefonos[i][0].toString()));
                ps.setString(2, telefonos[i][1].toString());
                ps.setString(3, telefonos[i][2].toString());
                ps.execute();
                ps.close();
                
            }
            
            }
            catch(Exception ex){throw new DataAccessException("Error en Telefono_UsuarioDAO.agregarGabe() "+ex);}
         
        
    }
        
    public Object[][] devolverTel(int cod) throws SQLException, ClassNotFoundException{
        
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM telefonos_usuarios "
        + "WHERE cod_usuario ='"+cod+"'");
        int tam=0;
        
        while(rs.next())
            tam++;
        rs.close();
        st.close();
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM telefonos_usuarios "
        + "WHERE cod_usuario ='"+cod+"'");
        
        Object[][] tel = new Object[tam][2];
        int i=0;
        while(rs.next()){
            tel[i][0]=rs.getString(3);
            tel[i][1]=rs.getString(2);
            i++;
        }
        rs.close();
        st.close();
        return tel;                
    }
    
}

