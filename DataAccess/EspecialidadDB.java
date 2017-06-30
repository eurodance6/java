/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Especialidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class EspecialidadDB {
    
    public static Especialidad getEspecialidad(int idEspecialidad) {
        ConexionDB.connect();
        String idEspcString= Integer.toString(idEspecialidad);
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Especialidad");

        sel.addCondition( "idEspecialidad="+ idEspcString);

        try {
            session.addSessionConditions(sel, "Especialidad");
//        "SELECT * FROM Especialidad WHERE idEspecialidad="
//                    + idEspecialidad
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            if(rs.next()) {
                int idFacultad = rs.getInt("idFacultad");
                String especialidad = rs.getString("nombre");
                
                return new Especialidad(idEspecialidad, especialidad, new FacultadDB().getFacultad(idFacultad));
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(new EspecialidadDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
