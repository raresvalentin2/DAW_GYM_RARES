package instalaciones;

public class Sala {
    private int idSala;
    private String nombre;
    private int aforoMax;

    public Sala(int idSala, String nombre, int aforoMax) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.aforoMax = aforoMax;
    }

    public boolean comprobarDisponibilidad() { return true; }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getAforoMax() { return aforoMax; }
    public void setAforoMax(int aforoMax) { this.aforoMax = aforoMax; }
}
