/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author alulab14
 */
//Un ligero cambio
public enum TurnoBibliotecario {
    
    MAÑANA("Mañana", 1),
    TARDE("Tarde", 2),
    NOCHE("Noche", 3);

    private final String turno;
    private final int idTurno;
    
    private TurnoBibliotecario(String turno, int idTurno) {
        this.idTurno = idTurno;
        this.turno = turno;
    }
        
    public int getId() {
        return idTurno;
    }

    public String getTurno() {
        return turno;
    }
    
    public static TurnoBibliotecario getTurno(int idTurno) {
        for(TurnoBibliotecario turno: TurnoBibliotecario.values()) {
            if(turno.idTurno==idTurno) return turno;
        }
        return null;
    }
    
}
