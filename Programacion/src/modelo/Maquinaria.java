package modelo;

/**
 * Clase para guardar una maquina del gimnasio
 */
public class Maquinaria {
    private int id;
    private String tipo;
    private String marca;
    private String numeroSerie;
    private String estado;

    public Maquinaria(int id, String tipo, String marca, String numeroSerie, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getEstado() {
        return estado;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}