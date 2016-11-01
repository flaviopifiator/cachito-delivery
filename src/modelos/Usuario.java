
package modelos;

import java.io.FileInputStream;

public class Usuario extends Persona{
    protected String pass;
    protected Integer cargo;
    protected String foto;
    protected Telefono_Usuario tel;
     FileInputStream fis;
     int longitud;

    
    public Usuario() {
    }

    public Usuario(String nombre, String apellido, Integer dni,String foto, 
            Boolean activo, String pass, Integer cargo, FileInputStream fis2, Integer longitud) {
        super(nombre,apellido,dni,activo);

        this.pass = pass;
        this.cargo = cargo;
        this.foto = foto;
        this.fis=fis2;
        this.longitud=longitud;

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
    
    public FileInputStream getFis(){
        return this.fis;
    }
    
    public void setFis(FileInputStream fis){
        this.fis=fis;
    }
    
    public int getLongitud(){
        return this.longitud;
    }
    
    public void setLongitud(int longi){
        this.longitud=longi;
    }
}

