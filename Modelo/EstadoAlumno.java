package Modelo;

public enum EstadoAlumno {
    HABILTADO(0),
    SUSPENDIDO(1);

    final int val;

    EstadoAlumno(int val) {
        this.val = val;
    }
}
