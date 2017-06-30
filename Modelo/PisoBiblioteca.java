/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author franc
 */
public class PisoBiblioteca {
    
    private boolean activo;
    private int numLibros;
    private int numBibliotecarios;
    
    public PisoBiblioteca(boolean activo, int numLibros, int numBibliotecarios) {
        this.activo = activo;
        this.numLibros = numLibros;
        this.numBibliotecarios = numBibliotecarios;
    }
    
    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * @return the numLibros
     */
    public int getNumLibros() {
        return numLibros;
    }

    /**
     * @param numLibros the numLibros to set
     */
    public void setNumLibros(int numLibros) {
        this.numLibros = numLibros;
    }

    /**
     * @return the numBibliotecarios
     */
    public int getNumBibliotecarios() {
        return numBibliotecarios;
    }

    /**
     * @param numBibliotecarios the numBibliotecarios to set
     */
    public void setNumBibliotecarios(int numBibliotecarios) {
        this.numBibliotecarios = numBibliotecarios;
    }
    
}
