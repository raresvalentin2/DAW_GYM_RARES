package instalaciones;

public class Actividad {
    private int idActividad;
    private String nombre;
    private int duracionMinutos;

    public Actividad(int idActividad, String nombre, int duracionMinutos) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
    }

    public int getIdActividad() { return idActividad; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
}
