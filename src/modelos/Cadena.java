/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.swing.JOptionPane;

/**
 *
 * @author Cusipuma
 */
public class Cadena{
    
    public String limitar(String palabra, int tam){
        if(tam==0)
            return "";
        
        if(tam<0){
            JOptionPane.showMessageDialog(null, "El tamaño de la cadena debe ser positivo.","Error en CADENA",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if(tam>palabra.length()){
            
            return palabra;
            
        }
        
        String resul = "";
        for(int i=0; i<tam-3; i++){
            resul= resul+ palabra.charAt(i);
        }
        
        return resul+"...";
    }
    
}
