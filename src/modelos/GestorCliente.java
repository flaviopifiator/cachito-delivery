package modelos;
import java.util.ArrayList;
import Excepciones.ClienteExistenteException;
import Excepciones.ClienteInexistenteException;
import Excepciones.DataAccessException;

public class GestorCliente {
    private ArrayList listaClientes = new ArrayList();
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public void buscarTodo() throws DataAccessException{
        this.listaClientes = clienteDAO.buscarTodo();
    }
    
      
    public void agregarNuevoCliente(Cliente nuevo) throws ClienteExistenteException, DataAccessException{
        buscarTodo();
        for(Object obj:listaClientes)
            {
            Cliente cliente =(Cliente)obj;                
            if (cliente.getCodigo() == nuevo.getCodigo())
                {
                    throw new ClienteExistenteException("El Cliente ya existe");
                }            
            }        
        clienteDAO.agregar(nuevo);
        listaClientes.add(nuevo);
    }
    
    public Cliente buscarCliente (int codigo) throws ClienteInexistenteException, DataAccessException{   
        buscarTodo(); 
        for(Object obj:listaClientes)
            {
            Cliente cliente =(Cliente)obj;
            if (cliente.getCodigo() == codigo)
                {                                           
                    return cliente;    
                }            
            }
        throw new ClienteInexistenteException("El cliente no existe");
    }

    
    public void modificarCliente (Cliente client) throws DataAccessException {
        buscarTodo();
        int indice=0;
        for(Object obj:listaClientes)
            {
            Cliente cliente =(Cliente)obj;
            if (cliente.getDni() == client.getDni())
                {
                    clienteDAO.actualizar(client);
                    listaClientes.set(indice, client);
                    return;
                }  
            indice++;
            }
    }
    
    public void eliminarUsuario (int codigo) throws DataAccessException {
       buscarTodo();
        int indice=0;
        for(Object obj:listaClientes)
            {
            Cliente cliente =(Cliente)obj;
            if (cliente.getCodigo() == codigo)
                {
                    clienteDAO.eliminar(codigo);
                    listaClientes.remove(indice);
                    return;
                }  
            indice++;
            }
    }
}