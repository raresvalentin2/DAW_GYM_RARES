package modelo;

/**
 * Clase para guardar una actividad con fecha y hora
 */
public class ActividadProgramada {
    private int id;
    private String sala;
    private String actividad;
    private String entrenador;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private int capacidad;
    private int plazasLibres;

    public ActividadProgramada(int id, String sala, String actividad, String entrenador, String fecha, String horaInicio, String horaFin, int capacidad, int plazasLibres) {
        this.id = id;
        this.sala = sala;
        this.actividad = actividad;
        this.entrenador = entrenador;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.capacidad = capacidad;
        this.plazasLibres = plazasLibres;
    }

    public int getId() {
        return id;
    }

    public String getSala() {
        return sala;
    }

    public String getActividad() {
        return actividad;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }
}