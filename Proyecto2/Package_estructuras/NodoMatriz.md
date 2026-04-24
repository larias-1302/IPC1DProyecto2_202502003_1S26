```java
}package estructuras;
import modelos.Carta;

public class NodoMatriz {

    public Carta dato;
    public NodoMatriz arriba;
    public NodoMatriz abajo;
    public NodoMatriz izquierda;
    public NodoMatriz derecha;

    public int fila;
    public int columna;

    public NodoMatriz(Carta dato, int fila, int columna) {
        this.dato = dato;
        this.fila = fila;
        this.columna = columna;

        arriba = abajo = izquierda = derecha = null;
    }
}
