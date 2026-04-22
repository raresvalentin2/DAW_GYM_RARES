package instalaciones;

public class Maquinaria {
    private int idMaquina;
    private String nombre;
    private String estadoMantenimiento;

    public Maquinaria(int idMaquina, String nombre, String estadoMantenimiento) {
        this.idMaquina = idMaquina;
        this.nombre = nombre;
        this.estadoMantenimiento = estadoMantenimiento;
    }

    public void notificarAveria() { }

    public int getIdMaquina() { return idMaquina; }
    public void setIdMaquina(int idMaquina) { this.idMaquina = idMaquina; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstadoMantenimiento() { return estadoMantenimiento; }
    public void setEstadoMantenimiento(String estadoMantenimiento) { this.estadoMantenimiento = estadoMantenimiento; }
}
