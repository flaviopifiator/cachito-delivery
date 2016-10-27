package modelos;
public class Cadete extends Persona{

    protected Integer estado;
    protected String foto;
    
    public Cadete(){
        
    }
    public Cadete (Integer dni, String nombre, String apellido, String foto, 
                    Boolean activo, Integer estado){
        
        super(nombre,apellido,dni,activo);
        this.foto = foto;
        this.estado = estado;
    }
    
    public Integer getEstado(){
        return estado;
    }
    
    public void setEstado(Integer estado){
        this.estado = estado;
    }
    
    
    public String getFoto() {
        return foto;
    }
    
    public void setFoto(String foto){
        this.foto = foto;
    }

}
