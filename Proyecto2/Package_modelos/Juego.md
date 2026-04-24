```java
package modelos;
public class Juego {
    public String codigo;
    public String nombre;
    public String genero;
    public double precio;
    public String plataforma;
    public int stock;
    public String descripcion;

    public Juego(String codigo, String nombre, String genero, double precio,String plataforma, int stock, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.plataforma = plataforma;
        this.stock = stock;
        this.descripcion = descripcion;
    }
}
