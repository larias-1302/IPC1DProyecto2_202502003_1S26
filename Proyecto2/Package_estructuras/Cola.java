package estructuras;

public class Cola {
    private NodoCola frente;
    private NodoCola fin;
    private int size;

    public Cola() {
        frente = null;
        fin = null;
        size = 0;
    }

//-------------------------------------ENCOLAR----------------------------------
    public void encolar(Object dato) {
        NodoCola nuevo = new NodoCola(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        }else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        size++;
    }

//-------------------------------------DESENCOLAR----------------------------------
    public synchronized Object desencolar() {
        if (estaVacia()) return null;
       
        Object dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) fin = null;
        size--;
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamaño() {
        return size;
    }

    public Object peek() {
        if (estaVacia()) return null;
        return frente.dato;
    }

    public NodoCola getFrente() {
        return frente;
    }
}
