/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Seba Cabrera
 */
public class UsuarioExistenteException extends Exception{

    public UsuarioExistenteException(String message) {
        super(message);
    }
    
}
