package modelo;

// socio del gimnasio
/**
 * Clase para guardar los datos de un socio
 */
public class Socio extends Persona {
    private String fechaAlta;
    private String estado;
    private String tipoPlan;

    public Socio(String dni, String nombre, String apellido, String telefono, String email, String fechaAlta, String estado, String tipoPlan) {
        super(dni, nombre, apellido, telefono, email);
        this.fechaAlta = fechaAlta;
        this.estado = estado;
        this.tipoPlan = tipoPlan;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
}
