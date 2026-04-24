```java
package estructuras;
import modelos.Carta;

public class MatrizCartas {
    private NodoMatriz inicio;
    private int filas;
    private int columnas;

    public MatrizCartas(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        construirMatriz();
    }

//----------------------------CONSTRUCCION DE MATRIZ----------------------------
    private void construirMatriz() {
        NodoMatriz[][] temp = new NodoMatriz[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                temp[i][j] = new NodoMatriz(null, i, j);

                if (i > 0) {
                    temp[i][j].arriba = temp[i - 1][j];
                    temp[i - 1][j].abajo = temp[i][j];
                }

                if (j > 0) {
                    temp[i][j].izquierda = temp[i][j - 1];
                    temp[i][j - 1].derecha = temp[i][j];
                }
            }
        }
        inicio = temp[0][0];
    }

    public NodoMatriz getInicio() {
        return inicio;
    }
    
//---------------------------------iNSERTAR-------------------------------------
    public boolean insertarPrimeroVacio(Carta carta) {
        NodoMatriz fila = inicio;

        while (fila != null){
            NodoMatriz actual = fila;

            while (actual != null) {
                if (actual.dato == null) {
                    actual.dato = carta;
                    return true;
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        return false;
    }
    
//----------------------------INTERCAMBIAR----------------------------
    public void intercambiar(NodoMatriz a, NodoMatriz b) {
        Carta temp = a.dato;
        a.dato = b.dato;
        b.dato = temp;
    }
    
//----------------------------ENCONTRAR NODO----------------------------
    public NodoMatriz getNodo(int filaBuscada, int colBuscada) {
        NodoMatriz fila = inicio;

        while (fila != null) {
            NodoMatriz actual = fila;
            while (actual != null) {
                if (actual.fila == filaBuscada && actual.columna == colBuscada) {
                    return actual;
                }
                actual = actual.derecha;
            }
            fila = fila.abajo;
        }
        return null;
    }
}
