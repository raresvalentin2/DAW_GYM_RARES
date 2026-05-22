package modelo;

// pago sencillo del sistema
/**
 * Clase para guardar un pago
 */
public class Pago {
    private int id;
    private String dniSocio;
    private double importe;
    private String concepto;

    public Pago(int id, String dniSocio, double importe, String concepto) {
        this.id = id;
        this.dniSocio = dniSocio;
        this.importe = importe;
        this.concepto = concepto;
    }

    public int getId() {
        return id;
    }

    public String getDniSocio() {
        return dniSocio;
    }

    public double getImporte() {
        return importe;
    }

    public String getConcepto() {
        return concepto;
    }
}
