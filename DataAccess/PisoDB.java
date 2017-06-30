/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Piso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franco
 */
public class PisoDB {
    
    public PisoDB() {
        ConexionDB.connect();
    }
    
    public ArrayList<Piso> getPisos() {
        ConexionDB.connect();
        
        ArrayList<Piso> pisos = new ArrayList<>();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Piso");
       
        sel.addColumns("idPiso","piso");
        try {
            session.addSessionConditions(sel, "Piso");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            //"SELECT idPiso, piso FROM Piso"
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                pisos.add(new Piso(rs.getInt("idPiso"), rs.getString("piso")));
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(PisoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pisos;
    }
    
    public ArrayList<Piso> getPisos(int idBiblioteca) {
        ConexionDB.connect();
        
        ArrayList<Piso> pisos = new ArrayList<>();
         Session session = Session.getSession();
        DBSelector sel = new DBSelector("Piso INNER JOIN PisoXBiblioteca AS PxB","Piso",true);
            

            sel.addColumns("Piso.idPiso","piso");
         
            sel.addCondition("Piso.idPiso=PxB.idPiso AND PxB.idBiblioteca=?");

//            "SELECT Piso.idPiso, piso "
//                        + "FROM Piso "
//                        + "INNER JOIN PisoXBiblioteca AS PxB "
//                        + "WHERE Piso.idPiso=PxB.idPiso AND PxB.idBiblioteca=?"
        try {
            session.addSessionConditions(sel, "Piso");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);          
            ps.setInt(1, idBiblioteca);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                pisos.add(new Piso(rs.getInt("idPiso"), rs.getString("piso")));
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(PisoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pisos;
    }
    
    public void deleteBiblioteca(int idBiblioteca) {
        ConexionDB.connect();
        try {
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate("DELETE FROM PisoXBiblioteca WHERE idBiblioteca=" + idBiblioteca);
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
    }
    
    public void setPisos(int idBiblioteca, ArrayList<Piso> pisos) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate("DELETE FROM PisoXBiblioteca WHERE idBiblioteca=" + idBiblioteca);
            for(Piso piso: pisos) {
                int idPiso = piso.getId();
                conn.createStatement().executeUpdate("INSERT INTO PisoXBiblioteca VALUES (" + idBiblioteca + ", " + idPiso + ")");
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
    }
    
}
