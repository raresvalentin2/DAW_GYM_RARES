package modelo;

// sala del gimnasio
public class Sala {
    private int id;
    private String nombre;
    private double metros;
    private int aforo;

    public Sala(int id, String nombre, double metros, int aforo) {
        this.id = id;
        this.nombre = nombre;
        this.metros = metros;
        this.aforo = aforo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMetros() {
        return metros;
    }

    public int getAforo() {
        return aforo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
}
