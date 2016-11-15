package modelos;
public class Telefono_Cliente {
    protected Integer codigo_cliente;
    protected String telefono;
    protected String descripcion;
    
    public Telefono_Cliente(){
        
    }
    
    public Telefono_Cliente(Integer codigo_cliente, String telefono,
                            String descripcion){
        this.codigo_cliente = codigo_cliente;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public Integer getCodigo_cliente() {
        return codigo_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo_cliente(Integer codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
