
package modelos;
public class Usuario {
    protected Integer codigo;
    protected String nombre;
    protected String apellido;
    protected Integer dni;
    protected String pass;
    protected Integer cargo;
    protected String foto;
       
    public Integer getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public Integer getDni(){
        return dni;
    }
}

