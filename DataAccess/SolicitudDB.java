/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static DataAccess.DBInfo.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public class SolicitudDB {
    
    public SolicitudDB() {
        ConexionDB.connect();
    }
    
    public Solicitud.SolicitudStat getStats() {
        Session session = Session.getSession();

        try {
            DBSelector sel = new DBSelector(TB_SOLICITUD);
            sel.addColumn("COUNT(idSolicitud) AS cantidad");
            session.addSessionConditions(sel, TB_SOLICITUD);

            ResultSet rsAll = ConexionDB.execute(sel, getClass());
            sel.addCondition("revisada=FALSE");
            ResultSet rsUnchecked = ConexionDB.execute(sel, getClass());

            if(rsAll==null || rsUnchecked==null)
                return new Solicitud.SolicitudStat(0, 0);

        
            if(rsAll.next() && rsUnchecked.next()) {
                return new Solicitud.SolicitudStat(
                        rsUnchecked.getInt("cantidad"),
                        rsAll.getInt("cantidad")
                );
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Solicitud.SolicitudStat(0, 0);
    }
    
    public ArrayList<Solicitud> getSolicitudes(boolean revisada, int offset, int len) {
        Session session = Session.getSession();
        
        ConexionDB.connect();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        
        try {
            Connection conn = ConexionDB.getConnection();
            
            DBSelector sel = new DBSelector(TB_SOLICITUD);
            sel.addCondition("revisada=" + (revisada + "").toUpperCase());
            sel.setTail("LIMIT " + offset + ", " + len);
            
            session.addSessionConditions(sel, TB_SOLICITUD);
            
            String sql = sel.generateQuery();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                Solicitud s = new Solicitud(UsuarioDB.getUsuario(rs.getInt("idUsuario")),
                        Biblioteca.getBiblioteca(rs.getInt("idBiblioteca")),
                        rs.getInt("idSolicitud"),rs.getString("isbn_Libro_Nuevo"),
                        rs.getString("descripcion"),rs.getBoolean("revisada"),
                        rs.getDate("fechaSolicitud"));
                //b.addAll(pisoDB.getPisos(b.getID()));
                
                solicitudes.add(s);
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //codigo al, numero sol, fecha, descripcion
        return solicitudes;
    }
    
    public ArrayList<Solicitud> getSolicitudes(int offset, int len) {
        Session session = Session.getSession();
        
        ConexionDB.connect();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        
        try {
            Connection conn = ConexionDB.getConnection();
            
            //String sql = "SELECT * FROM Solicitud WHERE idBiblioteca=3";// + "LIMIT " + offset + ", " + len;
            DBSelector sel = new DBSelector(TB_SOLICITUD);
            sel.setTail("LIMIT " + offset + ", " + len);
            
            session.addSessionConditions(sel, TB_SOLICITUD);
            
            String sql = sel.generateQuery();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                Solicitud s = new Solicitud(UsuarioDB.getUsuario(rs.getInt("idUsuario")),
                        Biblioteca.getBiblioteca(rs.getInt("idBiblioteca")),
                        rs.getInt("idSolicitud"),rs.getString("isbn_Libro_Nuevo"),
                        rs.getString("descripcion"),rs.getBoolean("revisada"),
                        rs.getDate("fechaSolicitud"));
                //b.addAll(pisoDB.getPisos(b.getID()));
                
                solicitudes.add(s);
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //codigo al, numero sol, fecha, descripcion
        return solicitudes;
    }
    
    public void revisar_solicitud(int id_solicitud){
        Session session = Session.getSession();
        
        ConexionDB.connect();
        try {
            Connection conn = ConexionDB.getConnection();
            
            //String sqlold = "UPDATE Solicitud SET revisada=? WHERE idSolicitud=?";
            DBUpdater upt = new DBUpdater("Solicitud", "revisada=?");
            upt.addCondition("idSolicitud=?");
            session.addSessionConditions(upt, TB_SOLICITUD, Privilegio.REVIEW_REQUESTS);
            
            String sql = upt.generateQuery();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, true);
            pstmt.setInt(2,id_solicitud);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //codigo al, numero sol, fecha, descripcion

    }
}
