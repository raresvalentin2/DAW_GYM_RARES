package modelo;

// reserva sencilla
public class Reserva {
    private int id;
    private String dniSocio;
    private String actividad;
    private String fecha;

    public Reserva(int id, String dniSocio, String actividad, String fecha) {
        this.id = id;
        this.dniSocio = dniSocio;
        this.actividad = actividad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public String getActividad() {
        return actividad;
    }

    public String getFecha() {
        return fecha;
    }
}
