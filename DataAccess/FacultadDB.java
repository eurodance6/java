/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Facultad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class FacultadDB {
    
    public ArrayList<Facultad> getFacultades() {
        ArrayList<Facultad> facultades = new ArrayList<>();
        ConexionDB.connect();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Facultad");

        // "SELECT * FROM Facultad"
        try {
            session.addSessionConditions(sel, "Facultad");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            
            while(rs.next()) {
                int idFacultad = rs.getInt("idFacultad");
                String nombre = rs.getString("nombre");
                
                facultades.add(new Facultad(idFacultad, nombre));
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(FacultadDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return facultades;
    }
    
    public Facultad getFacultad(int idFacultad) {
        for(Facultad facultad : getFacultades()) {
            if(facultad.getId()==idFacultad)
                return facultad;
        }
        return null;
    }
    
}
