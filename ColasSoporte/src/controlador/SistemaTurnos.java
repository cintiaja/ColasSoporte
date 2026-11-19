package controlador;

import modelo.ColaSolicitudes;
import modelo.Solicitud;


public class SistemaTurnos {

    private final ColaSolicitudes cola = new ColaSolicitudes();
    private int siguienteTurno = 1; 

    public Solicitud registrarSolicitud(String nombre, String tipo, String descripcion) {
        if (nombre == null || nombre.trim().isEmpty()
                || tipo == null || tipo.trim().isEmpty()
                || descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Ningún dato puede estar vacío.");
        }
        Solicitud nueva = new Solicitud(siguienteTurno++, nombre.trim(), tipo.trim(), descripcion.trim());
        cola.enqueue(nueva);
        return nueva;
    }

    public Solicitud verSiguiente() {
        return cola.peek();
    }

    public Solicitud atenderSiguiente() {
        Solicitud solicitud = cola.dequeue();
        if (solicitud != null) {
            solicitud.marcarComoAtendida();
        }
        return solicitud;
    }

    public Solicitud[] obtenerPendientes() {
        return cola.toArray();
    }

    public int cantidadPendientes() {
        return cola.size();
    }

    public boolean hayPendientes() {
        return !cola.isEmpty();
    }
}
