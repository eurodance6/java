/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.EstadoEjemplar;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franco
 */
public class EstadoLibroDB {
    
    public EstadoLibroDB() {}
    
    public void validarEstados(Component parentComponent) {
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        
        Map<Integer, String> currValues = new HashMap<>();
        for(EstadoEjemplar state: EstadoEjemplar.values()) currValues.put(state.id, state.estado);
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("EstadoEjemplar");
//        "SELECT * FROM EstadoEjemplar"
        try {
            session.addSessionConditions(sel, "EstadoEjemplar");
            String sql = sel.generateQuery();
            
            int count = 0;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("idEstadoEjemplar");
                String estado = rs.getString("estado");
                
                if(!currValues.get(id).equals(estado)) {
                    JOptionPane.showMessageDialog(parentComponent, 
                            "La tabla de estados de EjemplarLibro no esta actualizada",
                            "ERROR FATAL", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                
                count ++;
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(EstadoLibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
