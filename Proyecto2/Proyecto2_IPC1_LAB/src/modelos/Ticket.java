package modelos;

public class Ticket {
    public String nombrePersona;
    public String nombreTorneo;

    public Ticket(String nombrePersona, String nombreTorneo) {
        this.nombrePersona = nombrePersona;
        this.nombreTorneo = nombreTorneo;
    }

    @Override
    public String toString() {
        return nombrePersona + "|" + nombreTorneo;
    }
}