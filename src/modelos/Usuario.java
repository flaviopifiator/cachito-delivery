
package modelos;
public class Usuario extends Persona{
    protected String pass;
    protected Integer cargo;
    protected String foto;
    protected Telefono_Usuario tel;
    
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, Integer dni,String foto, 
            Boolean activo, String pass, Integer cargo) {
        super(nombre,apellido,dni,activo);

        this.pass = pass;
        this.cargo = cargo;
        this.foto = foto;

    }
    
    public String getPass(){
        return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public Integer getCargo(){
        return cargo;
    }
    
    public void setCargo(Integer cargo){
        this.cargo = cargo;
    }
       
    public String getFoto() {
        return foto;
    }
    
    public void setFoto(String foto){
        this.foto = foto;
    }

    public Telefono_Usuario getTel() {
        return tel;
    }

    public void setTel(Telefono_Usuario tel) {
        this.tel = tel;
    }
    
}

