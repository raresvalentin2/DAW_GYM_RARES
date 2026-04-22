package usuarios;

public class Entrenador extends Empleado {
    private String especialidad;

    public Entrenador(String dni, String nombre, String email, String telefono, String numSS, double sueldoBase, String especialidad) {
        super(dni, nombre, email, telefono, numSS, sueldoBase);
        this.especialidad = especialidad;
    }

    public void gestionarPlanificacion() { }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
