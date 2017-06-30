/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author alulab14
 */
public class Solicitud {

    public static class SolicitudStat {
        public final int numPendientes, numTotales;
        
        public SolicitudStat(int numPendientes, int numRevisadas) {
            this.numPendientes = numPendientes;
            this.numTotales = numRevisadas;
        }
    }
    
    public Solicitud(
            Usuario usuario, Biblioteca biblioteca,
            int idSolicitud, String isbn, String descripcion, boolean revisada,
            Date fecha_solicitud) {
        this.usuario = usuario;
        this.idSolicitud = idSolicitud;
        this.isbn = isbn;
        this.descripcion = descripcion;
        this.revisada = revisada;
        this.biblioteca = biblioteca;
        this.fechaSolicitud = fecha_solicitud;
    }
    
    private Usuario usuario;
    private Biblioteca biblioteca;
    private int idSolicitud;
    private String isbn;
    private String descripcion;
    private boolean revisada;
    private Date fechaSolicitud;

     /**
     * @return the idSolicitud
     */
    
    
    public int getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * @param idSolicitud the idSolicitud to set
     */
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRevisada() {
        return revisada;
    }

    public void setRevisada(boolean revisada) {
        this.revisada = revisada;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Date getFecha_solicitud() {
        return fechaSolicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fechaSolicitud = fecha_solicitud;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getEmail() {
        return usuario.getCorreo();
    }
    
}
