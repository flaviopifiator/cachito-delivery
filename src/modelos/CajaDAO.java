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
public class CajaDAO {
    
    public void nuevaCaja(Caja ca) throws DataAccessException{
         try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO registro_caja ( "
                    + "tipo_registro, fecha_registro, hora_registro, cod_usuario, "
                    + " monto_caja) VALUES (?,?,?,?,?)");
            
            ps.setBoolean(1, ca.isTipo());
            ps.setString(2, ca.getFecha());
            ps.setString(3, ca.getHora());
            ps.setInt(4, ca.getCod_usuario());
            ps.setFloat(5, ca.getMonto());
            ps.execute();
            ps.close();
        }catch(ClassNotFoundException | SQLException ex){throw new DataAccessException("Error en UsuarioDAO.agregar() "+ex);}
        
    }
    
    public boolean seba() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro_caja ORDER BY cod_caja asc");
            boolean si=true;
            while(rs.next())
                si=rs.getBoolean("tipo_registro");
            rs.close();
            st.close();
            
            return si;
        }catch(Exception ex){throw new DataAccessException("Error CajaDAO.apertura "+ex);}
    }
    
    public Caja lastChest() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro_caja ORDER BY cod_caja asc");
            Caja n = new Caja();
            while(rs.next()){
                n.setCod(rs.getInt("cod_caja"));
                n.setTipo(rs.getBoolean("tipo_registro"));
                n.setFecha(rs.getString("fecha_registro"));
                n.setHora(rs.getString("hora_registro"));
                n.setCod_usuario(rs.getInt("cod_usuario"));
                n.setMonto(rs.getFloat("monto_caja"));
            }
                
            rs.close();
            st.close();
            
            return n;
        }catch(ClassNotFoundException | SQLException ex){throw new DataAccessException("Error en CajaDAO.last "+ex);}
    }
    
    public boolean primera() throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro_caja ORDER BY cod_caja asc");
            int i=0;
            while(rs.next())
                i++;
            rs.close();
            st.close();
            
            if (i==0)
                return false;
            return true;
        }catch(Exception ex){throw new DataAccessException("Error CajaDAO.apertura "+ex);}
    }
}
