package gestion;

import java.time.LocalDate;

public class Pago {
    private int idTransaccion;
    private double importe;
    private LocalDate fecha;

    public Pago(int idTransaccion, double importe, LocalDate fecha) {
        this.idTransaccion = idTransaccion;
        this.importe = importe;
        this.fecha = fecha;
    }

    public void generarComprobante() { }

    public int getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(int idTransaccion) { this.idTransaccion = idTransaccion; }
    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
