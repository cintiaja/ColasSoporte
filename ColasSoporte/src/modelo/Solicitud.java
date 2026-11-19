package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Solicitud {

    public enum Estado { PENDIENTE, ATENDIDA }

    private final int numeroTurno;
    private final String nombreUsuario;
    private final String tipoIncidencia;
    private final LocalDateTime horaRegistro;
    private final String descripcion;
    private Estado estado;

    public Solicitud(int numeroTurno, String nombreUsuario, String tipoIncidencia, String descripcion) {
        this.numeroTurno = numeroTurno;
        this.nombreUsuario = nombreUsuario;
        this.tipoIncidencia = tipoIncidencia;
        this.descripcion = descripcion;
        this.horaRegistro = LocalDateTime.now(); 
        this.estado = Estado.PENDIENTE;          
    }

    public int getNumeroTurno() { return numeroTurno; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getTipoIncidencia() { return tipoIncidencia; }
    public LocalDateTime getHoraRegistro() { return horaRegistro; }
    public String getDescripcion() { return descripcion; }
    public Estado getEstado() { return estado; }

    public void marcarComoAtendida() {
        this.estado = Estado.ATENDIDA;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Turno #" + numeroTurno
                + " | Usuario: " + nombreUsuario
                + " | Tipo: " + tipoIncidencia
                + " | Hora: " + horaRegistro.format(formato)
                + " | Problema: " + descripcion
                + " | Estado: " + estado;
    }
}
