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
public enum Privilegio {
    
    ACCESS_ALL_LIBRARIES(1, "acceder a todas las bibliotecas"),
    ACCESS_ALL_FLOORS(2, "acceder a todos los pisos"),
    ADD_BOOKS(3, "agregar libros"),
    ADD_SAMPLES(4, "agregar ejemplares"),
    ADD_LIBRARIANS(5, "agregar bibliotecarios"),
    ADD_USERS(6, "agregar usuarios"),
    LOAN_BOOKS(7, "prestar libros"),
    ADD_LIBRARIES(10, "añadir bibliotecas"),
    MODIFY_SAMPLES(8, "modificar estado de ejemplares"),
    MODIFY_USERS(9, "modificar usuarios"),
    REVIEW_REQUESTS(12, "revisar solicitudes");
    
    public final int id;
    public final String action;
    
    Privilegio(int id, String action) {
        this.id = id;
        this.action = action;
    }
    
    public static Privilegio getPrivilegio(int id) {
        for(Privilegio p: Privilegio.values())
            if(p.id == id)
                return p;
        return null;
    }
    
}
