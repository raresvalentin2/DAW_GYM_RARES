package usuarios;

public class Socio extends Persona {
    private int idSocio;
    private boolean estaAlCorriente;
    private String iban;

    public Socio(String dni, String nombre, String email, String telefono, int idSocio, String iban) {
        super(dni, nombre, email, telefono);
        this.idSocio = idSocio;
        this.estaAlCorriente = true;
        this.iban = iban;
    }

    public void solicitarReserva() {
        if (estaAlCorriente) System.out.println(nombre + " solicita reserva");
    }

    public void pagarCuota() { this.estaAlCorriente = true; }

    public int getIdSocio() { return idSocio; }
    public void setIdSocio(int idSocio) { this.idSocio = idSocio; }
    public boolean isEstaAlCorriente() { return estaAlCorriente; }
    public void setEstaAlCorriente(boolean estaAlCorriente) { this.estaAlCorriente = estaAlCorriente; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
}
