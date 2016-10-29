package modelos;
import java.util.ArrayList;
import Excepciones.CadeteExistenteException;
import Excepciones.CadeteInexistenteException;
import Excepciones.DataAccessException;

public class GestorCadete {
    private ArrayList listaCadetes = new ArrayList();
    private CadeteDAO cadeteDAO = new CadeteDAO();
    
    public void buscarTodo() throws DataAccessException{
        this.listaCadetes = cadeteDAO.buscarTodo();
    }
    
      
    public void agregarNuevoCadete(Cadete nuevo) throws CadeteExistenteException, DataAccessException{
        buscarTodo();
        for(Object obj:listaCadetes)
            {
            Cadete cadete =(Cadete)obj;                
            if (cadete.getDni() == nuevo.getDni())
                {
                    throw new CadeteExistenteException("El Cadete ya existe");
                }            
            }        
        cadeteDAO.agregar(nuevo);
        listaCadetes.add(nuevo);
    }
    
    public Cadete buscarCadete (int dni) throws CadeteInexistenteException, DataAccessException{   
        buscarTodo(); 
        for(Object obj:listaCadetes)
            {
            Cadete cadete =(Cadete)obj;
            if (cadete.getDni() == dni)
                {                                           
                    return cadete;    
                }            
            }
        throw new CadeteInexistenteException("El cadete no existe");
    }

    
    public void modificarCliente (Cadete cadet) throws DataAccessException {
        buscarTodo();
        int indice=0;
        for(Object obj:listaCadetes)
            {
            Cadete cadete =(Cadete)obj;
            if (cadete.getDni() == cadet.getDni())
                {
                    cadeteDAO.actualizar(cadet);
                    listaCadetes.set(indice, cadet);
                    return;
                }  
            indice++;
            }
    }
    
    public void eliminarCadete (int dni) throws DataAccessException {
       buscarTodo();
        int indice=0;
        for(Object obj:listaCadetes)
            {
            Cadete cadete =(Cadete)obj;
            if (cadete.getDni() == dni)
                {
                    cadeteDAO.eliminar(dni);
                    listaCadetes.remove(indice);
                    return;
                }  
            indice++;
            }
    }
}