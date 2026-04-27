package estructuras;

public class ListaSimple {
    private Nodo cabeza;

//-----------------------------------INSERTAR---------------------------------
    public void insertar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        }else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

//-----------------------------------VER LA CIMA---------------------------------
    public Nodo getCabeza() {
        return cabeza;
    }

//-----------------------------------LIMPIAR---------------------------------
    public void limpiar() {
        cabeza = null;
    }

//-----------------------------------INSERTAR AL INICIO---------------------------------
    public void insertarInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }
}
