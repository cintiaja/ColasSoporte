package modelo;

public class ColaSolicitudes {


    private static class Nodo {
        Solicitud dato;
        Nodo siguiente;

        Nodo(Solicitud dato) {
            this.dato = dato;
        }
    }

    private Nodo frente;  
    private Nodo fin;     
    private int tamanio;


    public boolean isEmpty() {
        return frente == null;
    }

    public int size() {
        return tamanio;
    }

    public void enqueue(Solicitud solicitud) {
        Nodo nuevo = new Nodo(solicitud);
        if (isEmpty()) {
            frente = nuevo;         
        } else {
            fin.siguiente = nuevo;   
        }
        fin = nuevo;
        tamanio++;
    }

    public Solicitud dequeue() {
        if (isEmpty()) {
            return null;
        }
        Solicitud atendida = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;             
        }
        tamanio--;
        return atendida;
    }

    public Solicitud peek() {
        return isEmpty() ? null : frente.dato;
    }

    public Solicitud[] toArray() {
        Solicitud[] arreglo = new Solicitud[tamanio];
        Nodo actual = frente;
        int i = 0;
        while (actual != null) {
            arreglo[i++] = actual.dato;
            actual = actual.siguiente;
        }
        return arreglo;
    }
}
