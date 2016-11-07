/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.ResultSet;
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
}
