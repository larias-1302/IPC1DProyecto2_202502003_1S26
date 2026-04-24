```java
package modelos;

public class CarritoItem {
    public Juego juego;
    public int cantidad;

    public CarritoItem(Juego juego, int cantidad) {
        this.juego = juego;
        this.cantidad = cantidad;
    }
}
