/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Excepciones.DataAccessException;
import java.sql.Connection;
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
    
//    public float total(int cod){
//        PedidoDAO ped = new PedidoDAO();
//        Pedido pedido = ped.buscarPedidoCod(cod, estado)
//    }
}
