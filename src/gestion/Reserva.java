package gestion;

import java.time.LocalDate;

public class Reserva {
    private int idReserva;
    private LocalDate fecha;
    private boolean confirmada;

    public Reserva(int idReserva, LocalDate fecha) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.confirmada = true;
    }

    public void cancelarReserva() { this.confirmada = false; }

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }
}
