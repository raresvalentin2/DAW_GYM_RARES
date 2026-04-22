package usuarios;

public class Empleado extends Persona {
    private String numSS;
    private double sueldoBase;

    public Empleado(String dni, String nombre, String email, String telefono, String numSS, double sueldoBase) {
        super(dni, nombre, email, telefono);
        this.numSS = numSS;
        this.sueldoBase = sueldoBase;
    }

    public void ficharEntrada() { }

    public String getNumSS() { return numSS; }
    public void setNumSS(String numSS) { this.numSS = numSS; }
    public double getSueldoBase() { return sueldoBase; }
    public void setSueldoBase(double sueldoBase) { this.sueldoBase = sueldoBase; }
}
