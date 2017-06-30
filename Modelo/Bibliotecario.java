package Modelo;

public class Bibliotecario extends Usuario {
    
    private TurnoBibliotecario turno;   
    private Piso piso;
    
    private Biblioteca biblioteca;

    public Bibliotecario(Usuario usuario, Biblioteca biblioteca, Piso piso, TurnoBibliotecario turno) {
        super(usuario);
        this.biblioteca = biblioteca;
        this.piso = piso;
        this.turno = turno;
    }
    
    public Bibliotecario(Biblioteca biblioteca, int codigo, String nombre,
            String apPaterno, String apMaterno, String direccion, boolean sesionIniciada,
            boolean activo, String correo, String password, TurnoBibliotecario turno, Piso piso,boolean esBibliotecario) {
        super(codigo, nombre, apPaterno, apMaterno, direccion, correo, password, sesionIniciada, activo,esBibliotecario);
        this.turno = turno;
        this.piso = piso;
        
        this.biblioteca = biblioteca;
    }
    
    void registrarPrestamo(Reserva reserva) {}
    void registrarPrestamo(Prestamo prestamo) {}
    void finalizarPrestamo(Prestamo prestamo) {}

    /**
     * @return the turno
     */
    public TurnoBibliotecario getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(TurnoBibliotecario turno) {
        this.turno = turno;
    }

    /**
     * @return the area
     */
    public Piso getPiso() {
        return piso;
    }

    /**
     * @param piso the area to set
     */
    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    /**
     * @return the biblioteca
     */
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca the biblioteca to set
     */
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
