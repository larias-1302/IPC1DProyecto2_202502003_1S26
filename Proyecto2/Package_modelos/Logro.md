```java
package modelos;

public class Logro {
    public String nombre;
    public String descripcion;
    public boolean desbloqueado;

    public Logro(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.desbloqueado = false;
    }
}
