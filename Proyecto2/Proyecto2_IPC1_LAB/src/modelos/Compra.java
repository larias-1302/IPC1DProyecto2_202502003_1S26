package modelos;

public class Compra {
    public String nombreJuego;
    public int cantidad;
    public double total;
    public long timestamp;

    public Compra(String nombreJuego, int cantidad, double total) {
        this.nombreJuego = nombreJuego;
        this.cantidad = cantidad;
        this.total = total;
        this.timestamp = System.currentTimeMillis();
    }
}