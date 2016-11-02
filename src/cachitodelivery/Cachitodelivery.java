/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;


/**
 *
 * @author ElChinoNovioDeSe
 */
public class Cachitodelivery {

    
    
    public static void main(String[] args) throws DataAccessException {
        
        
        Principal p= new Principal();
        p.setSize(399,629); //DAR 39 PX MÁS DE ALTURA 
        p.setTitle("Cachito Delivery");
      // p.setLocationRelativeTo(null);
        p.setResizable(false);
        p.setVisible(true);
        
//        Menu_cajero q = new Menu_cajero();
//        q.setSize(673,299);
//        q.setVisible(true);
        
       /* Modificar_usuario q = new Modificar_usuario();
        q.setSize(1000,706);
        q.setVisible(true);*/
        
        
       /* Usuario user  = new Usuario("FAC FAC", "ROBIN", 37548666, "galleta1234", true, "-----",1);
        GestorUsuario gesU = new GestorUsuario();
        
        
        try {
            gesU.agregarNuevoUsuario(user);
        } catch (UsuarioExistenteException ex) {
            System.out.println(ex);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        }
        */
    
}
}