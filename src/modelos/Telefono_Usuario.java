package modelos;
public class Telefono_Usuario {
    protected Integer dni_usuario;
    protected String telefono;
    protected String descripcion;
          
    public Telefono_Usuario(){
        
    }
    
    public Telefono_Usuario(Integer dni_usuario , String telefono,
                            String descripcion){
        this.dni_usuario = dni_usuario;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Integer getDni_usuario() {
        return dni_usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDni_usuario(Integer dni_usuario) {
        this.dni_usuario = dni_usuario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
