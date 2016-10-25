/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Excepciones.DataAccessException;
import Excepciones.UsuarioExistenteException;
import Excepciones.UsuarioInexistenteException;
import java.util.ArrayList;

/**
 *
 * @author Seba Cabrera
 */
public class GestorUsuario {
    private ArrayList listaUsuarios = new ArrayList();
    private UsuarioDAO userDAO = new UsuarioDAO();

    public void buscarTodo() throws DataAccessException {
        this.listaUsuarios = userDAO.buscarTodo();
    }

    
    public void agregarNuevoUsuario(Usuario nuevo) throws UsuarioExistenteException, DataAccessException{
        buscarTodo();
        for(Object obj:listaUsuarios)
            {
            Usuario usuario =(Usuario)obj;                
            if (usuario.getDni() == nuevo.getDni())
                {
                    throw new UsuarioExistenteException("El Usuario ya existe");
                }            
            }        
        userDAO.agregar(nuevo);
        listaUsuarios.add(nuevo);
    }
    
    public Usuario buscarUsuario (int dni) throws UsuarioInexistenteException, DataAccessException{   
        buscarTodo(); // Aqui debería llamar a cliDAO.buscarTodo(); para tener la ultima actualizacion de la BD
        for(Object obj:listaUsuarios)
            {
            Usuario usuario =(Usuario)obj;
            if (usuario.getDni() == dni)
                {                                           
                    return usuario;    
                }            
            }
        throw new UsuarioInexistenteException("El usuario no existe");
    }

    
    public void modificarUsuario (Usuario user) throws DataAccessException {
        buscarTodo();
        int indice=0;
        for(Object obj:listaUsuarios)
            {
            Usuario usuario =(Usuario)obj;
            if (usuario.getDni() == user.getDni())
                {
                    userDAO.actualizar(user);
                    listaUsuarios.set(indice, user);
                    return;
                }  
            indice++;
            }
    }
    
    public void eliminarUsuario (int dni) throws DataAccessException {
       buscarTodo();
        int indice=0;
        for(Object obj:listaUsuarios)
            {
            Usuario usuario =(Usuario)obj;
            if (usuario.getDni()==dni)
                {
                    userDAO.eliminar(dni);
                    listaUsuarios.remove(indice);
                    return;
                }  
            indice++;
            }
    }

    
}
