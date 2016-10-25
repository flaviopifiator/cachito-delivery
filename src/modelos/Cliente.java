package modelos;
public class Cliente extends Persona{
    protected Integer codigo;
    protected String fecha_nacimiento;
    protected String calle;
    protected String numero_calle;
    protected String barrio;
    protected String departamento;
    protected String casa;
    protected String localidad;
    protected String observacion;
    
    public Cliente(){
        
    }

    public Cliente(String nombre, String apellido, Integer dni, Boolean activo,
                    String fecha_nacimiento, String calle, String numero_calle,
                    String barrio, String departamento, String casa,
                    String localidad, String observacion, Integer codigo){
        
       super(nombre,apellido,dni,activo);
       this.fecha_nacimiento = fecha_nacimiento;
       this.calle = calle;
       this.numero_calle = numero_calle;
       this.barrio = barrio;
       this.departamento = departamento;
       this.casa = casa;
       this.localidad = localidad;
       this.observacion = observacion;
       this.codigo = codigo;

    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero_calle() {
        return numero_calle;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCasa() {
        return casa;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getObservacion() {
        return observacion;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero_calle(String numero_calle) {
        this.numero_calle = numero_calle;
    }
   
       public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
