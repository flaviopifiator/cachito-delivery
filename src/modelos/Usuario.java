
package modelos;

import java.io.FileInputStream;
import java.io.InputStream;

public class Usuario extends Persona{
    protected String pass;
    protected Integer cargo;
    protected String foto;
    protected FileInputStream fis;
    protected int longitud;
    protected int cod;
    protected InputStream codFoto;

    
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
    
    public int getCod(){
        return this.cod;
    }
    
    public void setCod(int cod){
        this.cod=cod;
    }
    
    public InputStream getCodFoto(){
        return this.codFoto;
    }
    
    public void setCodFoto(InputStream fis){
        this.codFoto=fis;
    }
}

