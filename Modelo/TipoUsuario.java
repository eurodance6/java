/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DataAccess.TipoUsuarioDB;

/**
 *
 * @author franc
 */
public enum TipoUsuario {
    
    ALUMNO(1, "Alumno", 3, 3),
    PROFESOR(2, "Profesor", 7, 5);
    
    public final int idTipoUsuario;
    public final String tipo;
    private int maxReserva;
    private int tiempoReserva;
    
    TipoUsuario(int idTipoUsuario, String tipo, int maxReserva, int tiempoReserva) {
        this.idTipoUsuario = idTipoUsuario;
        this.tipo = tipo;
        this.maxReserva = maxReserva;
        this.tiempoReserva = tiempoReserva;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    public TipoUsuario setMaxReserva(int maxReserva) {
        this.maxReserva = maxReserva;
        return this;
    }
    
    public int getMaxReserva() {
        return maxReserva;
    }

    public int getTiempoReserva() {
        return tiempoReserva;
    }

    public TipoUsuario setTiempoReserva(int tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
        return this;
    }
    
    public static TipoUsuario getTipoUsuario(int idTipoUsuario) {
        TipoUsuarioDB.validarTipoUsuario();
        for(TipoUsuario tipo : TipoUsuario.values()) {
            if(tipo.idTipoUsuario==idTipoUsuario)
                return tipo;
        }
        return null;
    }
    
}
