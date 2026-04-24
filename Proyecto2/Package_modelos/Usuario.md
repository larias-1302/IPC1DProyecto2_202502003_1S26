```java
package modelos;

public class Usuario {
    public String nombre;
    public int xp;

    public Usuario(String nombre, int xp) {
        this.nombre = nombre;
        this.xp = xp;
    }

    @Override
    public String toString() {
        return nombre + " - " + xp + " XP";
    }
}
