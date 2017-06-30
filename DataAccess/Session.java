/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DataAccess.DBInfo;
import DataAccess.DBSelector;
import Modelo.Biblioteca;
import Modelo.Piso;
import Modelo.Privilegio;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author franc
 */
public class Session {
    
    private static Session session = new Session();
    
    private final int idUser;
    private final int idLibrary;
    private final int idArea;
    private final Map<String, String> locks = new HashMap<>();
    private final Set<Privilegio> privilegios = new HashSet<>();
    
    private Session() {
        this.idUser = -1;
        this.idLibrary = 1;
        this.idArea = 1;
        privilegios.add(Privilegio.ACCESS_ALL_FLOORS);
        privilegios.add(Privilegio.ACCESS_ALL_LIBRARIES);
    }
    
    public Session(int idUser, int idLibrary, int idArea) {
        this.idUser = idUser;
        this.idLibrary = idLibrary;
        this.idArea = idArea;
        privilegios.clear();
    }
    
    public Session putLock(String table, String lock) {
        locks.put(table, lock);
        return this;
    }
    
    public String getLock(String tableName) {
        return locks.get(tableName);
    }
    
    public void clearLocks() {
        for(Map.Entry<String, String> e: locks.entrySet()) {
            if(e.getValue()==null) continue;
            ConexionDB.releaseLock(getClass(), e.getKey(), e.getValue());
        }
    }
    
    public Biblioteca getBiblioteca() {
        return BibliotecaDB.getBiblioteca(idLibrary);
    }
    
    public Piso getPiso() {
        return Piso.getPiso(idArea);
    }
    
    public void addPrivilegio(Privilegio privilegio) {
        this.privilegios.add(privilegio);
    }
    
    public boolean containsPrivilegio(Privilegio privilegio) {
        return privilegios.contains(privilegio);
    }
    
    public boolean addSessionConditions(DBQuery q, String table, Privilegio... actions) throws Exception {
        for(Privilegio action: actions)
            if(!containsPrivilegio(action)) {
                JOptionPane.showMessageDialog(null, "No cuenta con los privilegios suficientes para " + action.action,
                        "Error", JOptionPane.ERROR_MESSAGE);
                throw new Exception("El usuario no cuenta con los privilegios suficientes.");
            }
        if(table.equals("EjemplarLibro")) System.out.println(!containsPrivilegio(Privilegio.ACCESS_ALL_FLOORS));
        if(DBInfo.tableHasAccess(table, DBInfo.ATTR_PISO) && !containsPrivilegio(Privilegio.ACCESS_ALL_FLOORS))
            q.addCondition(q.formatAttribute("idPiso") + "=" + idArea);
        if(DBInfo.tableHasAccess(table, DBInfo.ATTR_BIBLIOTECA) && !containsPrivilegio(Privilegio.ACCESS_ALL_LIBRARIES))
            q.addCondition(q.formatAttribute("idBiblioteca") + "=" + idLibrary);
        //if(prop.belongsBook() && !con)
        return true;
    }
    
    public static void setGlobalSession(Session session) {
        System.out.println("Se ha iniciado sesion!");
        Session.session = session;
    }
    
    public static Session getSession() {
        return session;
    }
 
    public boolean revalidatePassword(String password) {
        UsuarioDB udb = new UsuarioDB();
        return udb.login(idUser+"", password)==1;
    }
    
}