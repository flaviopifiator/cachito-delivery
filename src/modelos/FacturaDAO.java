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
import javax.swing.JOptionPane;

/**
 *
 * @author Cusipuma
 */
public class FacturaDAO {
    Factura facfac = new Factura();
    PedidoDAO pedido = new PedidoDAO();
    
    public float total(int cod) throws ClassNotFoundException, SQLException{
        PedidoDAO ped = new PedidoDAO();
        Pedido pedido = ped.buscarPedido(cod);
        Connection con = BaseDeDatos.getInstance();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM detalle_pedido WHERE cod_detalle_pedido = "+pedido.getCod_ped());
        float total = 0;
        int tam=0, i=0;
        while(rs.next())
            tam++;
        rs.close();
        rs = st.executeQuery("SELECT * FROM detalle_pedido WHERE cod_detalle_pedido = "+pedido.getCod_ped());
        Object[][] tabla = new Object[tam][2];
        while(rs.next()){
            tabla[i][0]=rs.getInt("cod_comida");
            tabla[i][1]=rs.getInt("cantidad_comida");
            i++;
        }
        rs.close();
        for (int j = 0; j < tabla.length; j++) {
            rs = st.executeQuery("SELECT * FROM comidas WHERE cod_comida = "+Integer.parseInt(tabla[j][0].toString()));
            tabla[j][0]=rs.getFloat("precio_comida");
            rs.close();
        }
        for (int j = 0; j < tabla.length; j++) {
            total=total+(Float.parseFloat(tabla[j][0].toString())*Float.parseFloat(tabla[j][1].toString()));
        }
        st.close();
        return total;
    }
//    public void agregarFactura(String f, String h, int pedido, int cadete,  float total, int zona, int usuario){
//        try{
//            Connection con = BaseDeDatos.getInstance();
//            PreparedStatement ps = con.prepareStatement("INSERT INTO facturas (cod_factura, "
//                    + "cod_pedido, cod_cadete, fecha_factura, hora_factura, "
//                    + "foto_usuario, activo ) VALUES (?,?,?,?,?,?,?)");
//            ps.setInt(1, usuario.getDni());
//            ps.setString(2, usuario.getNombre());
//            ps.setString(3, usuario.getApellido());
//            ps.setString(4, usuario.getPass());
//            ps.setInt(5, usuario.getCargo());
//            ps.setBinaryStream(6, usuario.getFis(),usuario.getLongitud());
//            ps.setBoolean(7, usuario.getActivo()); 
//            ps.execute();
//            ps.close();
//        }catch(Exception ex){throw new DataAccessException("Error en UsuarioDAO.agregar() "+ex);}
//    }
}
