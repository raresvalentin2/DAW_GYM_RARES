package modelo;

// actividad que se puede hacer en el gimnasio
public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private String nivel;

    public Actividad(int id, String nombre, String descripcion, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
