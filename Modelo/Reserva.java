package Modelo;

import java.util.Date;

public class Reserva {
    
    private int idReserva = -1;
    private EjemplarLibro libro;
    private UsuarioBiblioteca usuario;
    private Date fechaSolicitud;
    private Date fechaRecojo;

    public Reserva(EjemplarLibro libro, UsuarioBiblioteca usuario, Date fechaSolicitud, Date fechaRecojo) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaRecojo = fechaRecojo;
    }
    
    public int getIdReserva() {
        return idReserva;
    }
    
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    
    /**
     * @return the fechaSolicitud
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud the fechaSolicitud to set
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    /**
     * @return the fechaRecojo
     */
    public Date getFechaRecojo() {
        return fechaRecojo;
    }

    /**
     * @param fechaRecojo the fechaRecojo to set
     */
    public void setFechaRecojo(Date fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    /**
     * @return the libro
     */
    public EjemplarLibro getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(EjemplarLibro libro) {
        this.libro = libro;
    }

    /**
     * @return the usuario
     */
    public UsuarioBiblioteca getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioBiblioteca usuario) {
        this.usuario = usuario;
    }

}
