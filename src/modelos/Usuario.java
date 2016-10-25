
package modelos;
public class Usuario {
    protected String nombre;
    protected String apellido;
    protected Integer dni;
    protected String pass;
    protected Integer cargo;
    protected String foto;
    protected Boolean activo;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, Integer dni, String pass, Integer cargo, String foto, Boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.pass = pass;
        this.cargo = cargo;
        this.foto = foto;
        this.activo = activo;
    }
       
    
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }
    
    public Integer getDni(){
        return this.dni;
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
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    
}

