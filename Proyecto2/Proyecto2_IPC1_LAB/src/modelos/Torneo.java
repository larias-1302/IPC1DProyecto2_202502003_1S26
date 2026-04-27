package modelos;

public class Torneo {
    public String id;
    public String nombre;
    public String juego;
    public String fecha;
    public String hora;
    public double precio;
    public int ticketsDisponibles;

    public Torneo(String id, String nombre, String juego,String fecha, String hora, double precio, int ticketsDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.juego = juego;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.ticketsDisponibles = ticketsDisponibles;
    }

    @Override
    public String toString() {
        return nombre + " (" + juego + ")";
    }
}