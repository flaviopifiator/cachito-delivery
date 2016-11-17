package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    Telefono_UsuarioDAO telDAO = new Telefono_UsuarioDAO();
    
    public void agregar (Usuario usuario) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO usuarios (dni_usuario, "
                    + "nombre_usuario, apellido_usuario, contraseña_usuario, cargo_usuario, "
                    + "foto_usuario, activo ) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getPass());
            ps.setInt(5, usuario.getCargo());
            ps.setBinaryStream(6, usuario.getFis(),usuario.getLongitud());
            ps.setBoolean(7, usuario.getActivo()); 
            ps.execute();
            ps.close();
        }catch(Exception ex){throw new DataAccessException("Error en UsuarioDAO.agregar() "+ex);}
        
    }
    public void actualizar (Usuario usuario) throws DataAccessException{
    
        try{
            if(usuario.getFis()==null){
                if(usuario.getLongitud()==-1){
                    Connection con = BaseDeDatos.getInstance();
                    PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET dni_usuario=?, nombre_usuario=?"
                            + ",apellido_usuario=?, contraseña_usuario=? ,cargo_usuario=?, foto_usuario=?, activo=? WHERE cod_usuario=?");
                    ps.setInt(1, usuario.getDni());
                    ps.setString(2, usuario.getNombre());
                    ps.setString(3, usuario.getApellido());
                    ps.setString(4, usuario.getPass());
                    ps.setInt(5, usuario.getCargo());
                    ps.setBinaryStream(6, null,0);
                    ps.setBoolean(7, usuario.getActivo());
                    ps.setInt(8, usuario.getCod());
                    ps.execute();
                    ps.close();
                }else{
                    Connection con = BaseDeDatos.getInstance();
                    PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET dni_usuario=?, nombre_usuario=?"
                            + ",apellido_usuario=?, contraseña_usuario=? ,cargo_usuario=?, activo=? WHERE cod_usuario=?");
                    ps.setInt(1, usuario.getDni());
                    ps.setString(2, usuario.getNombre());
                    ps.setString(3, usuario.getApellido());
                    ps.setString(4, usuario.getPass());
                    ps.setInt(5, usuario.getCargo());
                    ps.setBoolean(6, usuario.getActivo());
                    ps.setInt(7, usuario.getCod());
                    ps.execute();
                    ps.close();
                }
                
            }else{
                Connection con = BaseDeDatos.getInstance();
                PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET dni_usuario=?, nombre_usuario=?"
                        + ",apellido_usuario=?, contraseña_usuario=? ,cargo_usuario=?, foto_usuario=?, activo=? WHERE cod_usuario=?");
                ps.setInt(1, usuario.getDni());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getPass());
                ps.setInt(5, usuario.getCargo());
                ps.setBinaryStream(6, usuario.getFis(),usuario.getLongitud());
                ps.setBoolean(7, usuario.getActivo());
                ps.setInt(8, usuario.getCod());
                ps.execute();
                ps.close();
            }
            
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
                user.setCodFoto(rs.getBinaryStream("foto_usuario"));
                user.setActivo(rs.getBoolean("activo"));
                user.setCod(rs.getInt("cod_usuario"));
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
    
    public Object[][] listadoUsuariosCusi() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios order by cod_usuario asc");
            int tam=0;
            while(rs.next())
                tam++;
            Object[][] lista = new Object[tam][4];
            int i=0;
            rs.close();
            rs = st.executeQuery("SELECT * FROM usuarios order by cod_usuario asc");
            while(rs.next())
            {
                lista[i][0] = rs.getInt(8);
                lista[i][1] = rs.getString(3).trim();
                lista[i][2] = rs.getString(2).trim();
                if(rs.getBoolean(7)==true){
                    if (rs.getInt(5)==0)
                        lista[i][3]="Administrador";
                    else
                        lista[i][3]="Cajero";
                }else
                    lista[i][3]="Eliminado";
                i++;
            }
            rs.close();
            st.close();
            CadeteDAO cadete = new CadeteDAO();
            Object[][] cad = cadete.listadoCadetesCusi();
            
            Object[][] nueva = new Object[cad.length+lista.length][4];
            
            int k=0;
            
            for (int j = 0; j < cad.length; j++) {
                nueva[j][0]=cad[j][0].toString().trim();
                nueva[j][1]=cad[j][1].toString().trim();
                nueva[j][2]=cad[j][2].toString().trim();
                nueva[j][3]=cad[j][3].toString().trim();
                k++;
            }
            
            for (int j = 0; j < lista.length; j++) {
                nueva[k][0]=lista[j][0].toString().trim();
                nueva[k][1]=lista[j][1].toString().trim();
                nueva[k][2]=lista[j][2].toString().trim();
                nueva[k][3]=lista[j][3].toString().trim();
                k++;
            }
            
            nueva=burbuja(nueva, 4);
            
            return nueva;
            
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
    }
    
    public Object[][] listadoUsuariosActivo() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios where activo=true order by cod_usuario asc");
            int tam=0;
            while(rs.next())
                tam++;
            Object[][] lista = new Object[tam][4];
            int i=0;
            rs.close();
            rs = st.executeQuery("SELECT * FROM usuarios where activo=true order by cod_usuario asc");
            while(rs.next())
            {
                lista[i][0] = rs.getInt(8);
                lista[i][1] = rs.getString(3).trim();
                lista[i][2] = rs.getString(2).trim();
                if (rs.getInt(5)==0)
                    lista[i][3]="Administrador";
                else
                    lista[i][3]="Cajero";
                i++;
            }
            rs.close();
            st.close();
            
            CadeteDAO cadete = new CadeteDAO();
            Object[][] cad = cadete.listadoCadeteActivo();
            
            Object[][] nueva = new Object[cad.length+lista.length][4];
            
            int k=0;
            
            for (int j = 0; j < cad.length; j++) {
                nueva[j][0]=cad[j][0].toString().trim();
                nueva[j][1]=cad[j][1].toString().trim();
                nueva[j][2]=cad[j][2].toString().trim();
                nueva[j][3]=cad[j][3].toString().trim();
                k++;
            }
            
            for (int j = 0; j < lista.length; j++) {
                nueva[k][0]=lista[j][0].toString().trim();
                nueva[k][1]=lista[j][1].toString().trim();
                nueva[k][2]=lista[j][2].toString().trim();
                nueva[k][3]=lista[j][3].toString().trim();
                k++;
            }
            
            nueva=burbuja(nueva, 4);
            
            return nueva;
            
            
            
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
    }
    
    public Usuario buscarUsuarioCod(int dni) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios where cod_usuario='"+dni+"'");
            Usuario user = null;
            if(rs.next()){
                user = new Usuario();
                user.setDni(rs.getInt("dni_usuario"));
                user.setNombre(rs.getString("nombre_usuario").trim());
                user.setApellido(rs.getString("apellido_usuario").trim());
                user.setPass(rs.getString("contraseña_usuario").trim());
                user.setCargo(rs.getInt("cargo_usuario"));
                user.setCodFoto(rs.getBinaryStream("foto_usuario"));
                user.setActivo(rs.getBoolean("activo"));
                user.setCod(rs.getInt("cod_usuario"));
            }   
            
            rs.close();
            st.close();
            return user;
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarCliente() "+ex);}
   
    }
    public Object[][] buscarUsuarioText(String cod, String ape, String nom, String car, boolean b) throws DataAccessException{
        try{
            if(b==true){
                if(cod==null && ape==null && nom==null && car==null)
                    return this.listadoUsuariosCusi();
                else{
                    Object [][] real = null;
                    real=this.listadoUsuariosCusi();
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
                                real[j][0]=real[i][0].toString().trim();
                                real[j][1]=real[i][1].toString().trim();
                                real[j][2]=real[i][2].toString().trim();
                                real[j][3]=real[i][3].toString().trim();
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
                                real[j][0]=real[i][0].toString().trim();
                                real[j][1]=real[i][1].toString().trim();
                                real[j][2]=real[i][2].toString().trim();
                                real[j][3]=real[i][3].toString().trim();
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
                                real[j][0]=real[i][0].toString().trim();
                                real[j][1]=real[i][1].toString().trim();
                                real[j][2]=real[i][2].toString().trim();
                                real[j][3]=real[i][3].toString().trim();
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
                    if (!car.isEmpty()){
                        int j=0;
                        for (int i=0; i<tam4; i++){
                            if (real[i][3].toString().toLowerCase().indexOf(car.toLowerCase())==0){
                                real[j][0]=real[i][0].toString().trim();
                                real[j][1]=real[i][1].toString().trim();
                                real[j][2]=real[i][2].toString().trim();
                                real[j][3]=real[i][3].toString().trim();
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
            }else{
                    if(cod==null && ape==null && nom==null && car==null)
                        return this.listadoUsuariosActivo();
                    else{
                        Object [][] real = null;
                        real=this.listadoUsuariosActivo();
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
                                    real[j][0]=real[i][0].toString().trim();
                                    real[j][1]=real[i][1].toString().trim();
                                    real[j][2]=real[i][2].toString().trim();
                                    real[j][3]=real[i][3].toString().trim();
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
                                    real[j][0]=real[i][0].toString().trim();
                                    real[j][1]=real[i][1].toString().trim();
                                    real[j][2]=real[i][2].toString().trim();
                                    real[j][3]=real[i][3].toString().trim();
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
                                    real[j][0]=real[i][0].toString().trim();
                                    real[j][1]=real[i][1].toString().trim();
                                    real[j][2]=real[i][2].toString().trim();
                                    real[j][3]=real[i][3].toString().trim();
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
                        if (!car.isEmpty()){
                            int j=0;
                            for (int i=0; i<tam4; i++){
                                if (real[i][3].toString().toLowerCase().indexOf(car.toLowerCase())==0){
                                    real[j][0]=real[i][0].toString().trim();
                                    real[j][1]=real[i][1].toString().trim();
                                    real[j][2]=real[i][2].toString().trim();
                                    real[j][3]=real[i][3].toString().trim();
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
            
            
        }catch (Exception ex){throw new DataAccessException("Error en UsuarioDAO.buscarUsuarioText() "+ex);}
   
    } 
    
    public int lastUser() throws DataAccessException{
        ArrayList listaUsuarios = this.buscarTodo();
        int codigo = 100;
        for(Object obj:listaUsuarios){
               Usuario usuario =(Usuario)obj;

            if (usuario.getCod()>codigo)
               {
                   codigo = usuario.getCod();
               }
        }
        return codigo;
    }
    public void eliminar(boolean b, int cod) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE usuarios SET activo="+b+" WHERE cod_usuario="+cod);
            ps.execute();
            ps.close();
        }catch(Exception ex){throw new DataAccessException("Error en UsuarioDAO.agregar() "+ex);}
        
    }
    public boolean isOP(Usuario u){
        int op = 99;
        
        if(u.getCod()==op)
            return false;
        else 
            return true;
    }
    public Object[][] burbuja(Object[][] lista, int cant){
        int i, j;
        Object[][] aux = new Object[1][cant];
        for(i=0;i<lista.length-1;i++)
            for(j=0;j<lista.length-i-1;j++)
                if(Integer.parseInt(lista[j+1][0].toString())<Integer.parseInt(lista[j][0].toString())){
                    for (int k=0; k<cant; k++) {
                        aux[0][k]=lista[j+1][k].toString().trim();
                        lista[j+1][k]=lista[j][k].toString().trim();
                        lista[j][k]=aux[0][k].toString().trim();
                    }
                }
        return lista;
    }
}

