
package modelos;

public class Persona {
    protected Integer dni;
    protected String nombre;
    protected String apellido;
    protected Boolean activo;
    
    public Persona() {
    }

    public Persona(String nombre, String apellido, Integer dni, Boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.activo = activo;
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
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }   
    
    
}
