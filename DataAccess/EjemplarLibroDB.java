/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Biblioteca;
import Modelo.EjemplarLibro;
import Modelo.EstadoEjemplar;
import Modelo.Libro;
import Modelo.Piso;
import Modelo.Privilegio;
import Modelo.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franco
 */
public class EjemplarLibroDB {
    
    
    private ArrayList<EjemplarLibro> toList(Libro libro, ResultSet rs) throws SQLException {
        ArrayList<EjemplarLibro> items = new ArrayList<>();
        while(rs.next()) {
            int idEjemplarLibro = rs.getInt("idEjemplarLibro");
            int idEstadoEjemplar = rs.getInt("idEstadoEjemplar");
            int idPiso = rs.getInt("idPiso");
            int idBiblioteca = rs.getInt("idBiblioteca");
            int numEjemplar = rs.getInt("numEjemplar");
            
            //System.out.println(" - " + idEjemplarLibro);
            
            EjemplarLibro item = new EjemplarLibro(libro, numEjemplar, idEstadoEjemplar, idPiso, idBiblioteca);
            item.setId(idEjemplarLibro);
            items.add(item);
        }
        return items;
    }
    
    public void devolver(EjemplarLibro ejemplar) {
        ConexionDB.connect();
        
        int newState = EstadoEjemplar.DISPONIBLE.id;
        ReservaDB rdb = new ReservaDB();
        Reserva r = rdb.getReserva(ejemplar);
        if(r!=null) newState = EstadoEjemplar.RESERVADO.id;
        
        DBUpdater upt = new DBUpdater("EjemplarLibro", "idEstadoEjemplar=" + newState);
        upt.addConditions("idEjemplarLibro=" + ejemplar.getId());
        
        try {
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
            upt = new DBUpdater("DetallePrestamo", "fechaDevolucion = NOW()");
            upt.addConditions("idEjemplarLibro=" + ejemplar.getId(), "fechaDevolucion IS NULL");
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
    }
    
    public ArrayList<EjemplarLibro> getEjemplares(Libro libro) {
        ArrayList<EjemplarLibro> ejemplares = null;
        ConexionDB.connect();
        
        String idLibro = Integer.toString(libro.getId());
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("EjemplarLibro");

        sel.addColumns("idEjemplarLibro","numEjemplar","idEstadoEjemplar","idPiso","idBiblioteca");
        sel.addCondition("EjemplarLibro.idLibro=" + idLibro);
        try {
            session.addSessionConditions(sel, "EjemplarLibro");
        } catch (Exception ex) {
            Logger.getLogger(EjemplarLibroDB.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
        String sql = sel.generateQuery();
//        "SELECT idEjemplarLibro, "
//                    + "numEjemplar, idEstadoEjemplar, idPiso, idBiblioteca FROM EjemplarLibro WHERE "
//                    + "EjemplarLibro.idLibro=" + libro.getId()
        try {
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            ejemplares = toList(libro, rs);
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
        
        return ejemplares;
    }
    
    public void crearEjemplar(EstadoEjemplar estado, Libro libro, Biblioteca biblioteca, Piso piso) {
        ConexionDB.connect();
        Session session = Session.getSession();
        DBInserter insert = new DBInserter("EjemplarLibro");
        insert.addColumns("idEstadoEjemplar", "idLibro", "idBiblioteca", "idPiso", "numEjemplar", "fechaCreacion");
        insert.addValues(estado.id+"", libro.getId()+"", biblioteca.getID()+"", piso.getId()+"", "NOW()");
        
        try {
            Connection conn = ConexionDB.getConnection();
            session.addSessionConditions(insert, "EjemplarLibro", Privilegio.ADD_SAMPLES);
            conn.createStatement().executeUpdate(insert.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(EjemplarLibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public EjemplarLibro getEjemplar(int idEjemplar) {
        ConexionDB.connect();
        String idEjemplarS = Integer.toString(idEjemplar);
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("EjemplarLibro");

        sel.addColumns("idEjemplarLibro","idLibro","numEjemplar","idEstadoEjemplar","idPiso","idBiblioteca");
        sel.addCondition("idEjemplarLibro=" + idEjemplarS);
//        "SELECT idEjemplarLibro, idLibro, "
//                    + "numEjemplar, idEstadoEjemplar, idPiso, idBiblioteca FROM EjemplarLibro WHERE "
//                    + "idEjemplarLibro=" + idEjemplar
        try {
            session.addSessionConditions(sel, "EjemplarLibro");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if(rs.next()) {
                int idLibro = rs.getInt("idLibro");
                Libro libro = new LibroDB().getLibro(idLibro);
                
                int idEjemplarLibro = rs.getInt("idEjemplarLibro");
                int idEstadoEjemplar = rs.getInt("idEstadoEjemplar");
                int idPiso = rs.getInt("idPiso");
                int idBiblioteca = rs.getInt("idBiblioteca");
                int numEjemplar = rs.getInt("numEjemplar");
                
                EjemplarLibro item = new EjemplarLibro(libro, numEjemplar, idEstadoEjemplar, idPiso, idBiblioteca);
                item.setId(idEjemplarLibro);
                
                return item;
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(EjemplarLibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
