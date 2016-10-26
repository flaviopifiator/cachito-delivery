/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachitodelivery;

import Excepciones.DataAccessException;
import Excepciones.UsuarioExistenteException;
import Excepciones.UsuarioInexistenteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.GestorUsuario;
import modelos.Usuario;

/**
 *
 * @author flavio
 */
public class Cachitodelivery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Principal p= new Principal();
        p.setSize(399,629); //DAR 39 PX MÁS DE ALTURA 
        p.setTitle("Cachito Delivery");
        p.setLocationRelativeTo(null);
        p.setResizable(false);
        p.setVisible(true);
        
        Usuario user  = new Usuario("CACHITO", "ROBIN", 1234,"",true, "galleta",1);
        GestorUsuario gesU = new GestorUsuario();
        
        
        try {
            //user= gesU.buscarUsuario(37548666);
            gesU.agregarNuevoUsuario(user);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        } catch (UsuarioExistenteException ex) {
            Logger.getLogger(Cachitodelivery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}
}