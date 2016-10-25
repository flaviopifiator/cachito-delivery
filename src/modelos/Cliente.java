package modelos;
public class Cliente extends Persona{
    protected Integer codigo;
    protected Character fecha_nacimiento;
    protected Character calle;
    protected Character numero_calle;
    protected Character barrio;
    protected Character departamento;
    protected Character casa;
    protected Character localidad;
    protected Character observacion;
    
    public Cliente(){
        
    }

    public Cliente(String nombre, String apellido, Integer dni, Boolean activo,
                    Character fecha_nacimiento, Character calle, Character numero_calle,
                    Character barrio, Character departamento, Character casa,
                    Character localidad, Character observacion, Integer codigo){
        
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
}
