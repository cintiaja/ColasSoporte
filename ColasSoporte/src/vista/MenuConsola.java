package vista;

import controlador.SistemaTurnos;
import java.util.Scanner;
import modelo.Solicitud;

public class MenuConsola {

    private static final String[] TIPOS = {"Hardware", "Software", "Red", "Plataforma"};

    private final SistemaTurnos sistema = new SistemaTurnos();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ", 0, 7);
            switch (opcion) {
                case 1: registrarUna(); break;
                case 2: verSiguiente(); break;
                case 3: atender(); break;
                case 4: mostrarPendientes(); break;
                case 5: System.out.println("Solicitudes pendientes: " + sistema.cantidadPendientes()); break;
                case 6: revisarVacia(); break;
                case 7: registrarVarias(); break;
                case 0: System.out.println("¡Hasta pronto!"); break;
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== SOPORTE TÉCNICO UNIVERSITARIO =====");
        System.out.println("1. Registrar solicitud");
        System.out.println("2. Ver siguiente a atender");
        System.out.println("3. Atender solicitud");
        System.out.println("4. Mostrar solicitudes pendientes");
        System.out.println("5. Cantidad de pendientes");
        System.out.println("6. ¿La cola está vacía?");
        System.out.println("7. Registrar varias solicitudes seguidas");
        System.out.println("0. Salir");
    }

    private void registrarUna() {
        System.out.print("Nombre del usuario: ");
        String nombre = scanner.nextLine();
        String tipo = elegirTipo();
        System.out.print("Descripción del problema: ");
        String descripcion = scanner.nextLine();
        try {
            Solicitud s = sistema.registrarSolicitud(nombre, tipo, descripcion);
            System.out.println("Solicitud registrada con el turno #" + s.getNumeroTurno());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + " No se registró la solicitud.");
        }
    }

    private void registrarVarias() {
        int cantidad = leerEntero("¿Cuántas solicitudes quieres registrar? (1-10): ", 1, 10);
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\n--- Solicitud " + i + " de " + cantidad + " ---");
            registrarUna();
        }
    }

    private void verSiguiente() {
        Solicitud s = sistema.verSiguiente();
        if (s == null) {
            System.out.println("La cola está vacía, no hay solicitudes por atender.");
        } else {
            System.out.println("Siguiente a atender:\n" + s);
        }
    }

    private void atender() {
        Solicitud s = sistema.atenderSiguiente();
        if (s == null) {
            System.out.println("La cola está vacía, no hay solicitudes por atender.");
        } else {
            System.out.println("Solicitud atendida:\n" + s);
        }
    }

    private void mostrarPendientes() {
        if (!sistema.hayPendientes()) {
            System.out.println("La cola está vacía, no hay solicitudes pendientes.");
            return;
        }
        System.out.println("Pendientes (en orden de llegada):");
        for (Solicitud s : sistema.obtenerPendientes()) {
            System.out.println(s);
        }
    }

    private void revisarVacia() {
        if (sistema.hayPendientes()) {
            System.out.println("La cola NO está vacía. Hay " + sistema.cantidadPendientes() + " pendiente(s).");
        } else {
            System.out.println("La cola está vacía.");
        }
    }

    private String elegirTipo() {
        System.out.println("Tipo de incidencia:");
        for (int i = 0; i < TIPOS.length; i++) {
            System.out.println((i + 1) + ". " + TIPOS[i]);
        }
        int opcion = leerEntero("Elige el tipo: ", 1, TIPOS.length);
        return TIPOS[opcion - 1];
    }

    private int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            try {
                int numero = Integer.parseInt(texto);
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
            } catch (NumberFormatException e) {
                // cae al mensaje de error de abajo
            }
            System.out.println("Entrada inválida. Escribe un número entre " + minimo + " y " + maximo + ".");
        }
    }
}
