/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.TipoUsuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alulab14
 */
public class TipoUsuarioDB {
    
    public static class ObjetoTipoUsuario {
        public final int idTipoUsuario;
        public final String tipo;
        public final int maxReserva;
        public final int tiempoReserva;
        
        public ObjetoTipoUsuario(final int id, final String tipo, final int maxReserva, final int tiempoReserva) {
            this.idTipoUsuario = id;
            this.tipo = tipo;
            this.maxReserva = maxReserva;
            this.tiempoReserva = tiempoReserva;
        }
        
    }
    
    private static boolean fueValidado = false;
    
    public static void validarTipoUsuario() {
        if(fueValidado) return;
        fueValidado = true;
        ConexionDB.connect();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("TipoUsuario");

        sel.addColumns("idTipoUsuario","maxReserva","tiempoPrestamo");
        
       
//        "SELECT idTipoUsuario, maxReserva, tiempoPrestamo FROM TipoUsuario"
        try {
            session.addSessionConditions(sel, "TipoUsuario");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("idTipoUsuario");
                int maxReserva = rs.getInt("maxReserva");
                int tiempoPrestamo = rs.getInt("tiempoPrestamo");
                
                TipoUsuario.getTipoUsuario(id).
                        setMaxReserva(maxReserva).
                        setTiempoReserva(tiempoPrestamo);
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(new TipoUsuarioDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(TipoUsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
