/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Privilegio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class PrestamoDB {
    
    public static int crearPrestamo(int idUsuario, Date fechaInicio) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            DBInserter insert = new DBInserter(DBInfo.TB_PRESTAMO);
            insert.addColumns("idUsuario", "FechaInicio");
            insert.addValues("?", "?");
            Session session = Session.getSession();
            session.addSessionConditions(insert, DBInfo.TB_PRESTAMO, Privilegio.LOAN_BOOKS);
            
            String sql = insert.generateQuery();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idUsuario);
            ps.setDate(2, fechaInicio);
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(new PrestamoDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(PrestamoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public static int cuentaPrestamosUsuario(int idUsuario) {
        ConexionDB.connect();
        try {
            DBSelector sel = new DBSelector("DetallePrestamo LEFT JOIN Prestamo ON DetallePrestamo.idPrestamo=Prestamo.idPrestamo", "DetallePrestamo", true);
            sel.addColumn("COUNT(*) AS cantidad");
            sel.addConditions(
                    "DetallePrestamo.fechaDevolucion IS NULL",
                    "Prestamo.idUsuario=" + idUsuario);
            
            Session session = Session.getSession();
            session.addSessionConditions(sel, "DetallePrestamo");
            
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if(rs.next()) {
                return rs.getInt("cantidad");
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(new PrestamoDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(PrestamoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public static int addDetallePrestamo(int idPrestamo, int idEjemplarLibro, Date fechaFin) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            CallableStatement cs = conn.prepareCall("{? = CALL addDetallePrestamo(?, ?, ?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, idPrestamo);
            cs.setInt(3, idEjemplarLibro);
            cs.setDate(4, fechaFin);
            
            cs.executeUpdate();
            
            return cs.getInt(1);
        } catch(SQLException ex) {
            ConexionDB.handleException(new PrestamoDB().getClass(), ex);
        }
        return -1;
    }
    
    public static int updatePrestamo(int idEjemplarLibro){
        
//        int idEjemplarLibro = 0;
//        try {
//            Connection conn = ConexionDB.getConnection();
//            CallableStatement cs = conn.prepareCall("{? = CALL getEjemplarID(?)}");
//            cs.registerOutParameter(1, Types.INTEGER);
//            cs.setInt(2, idPrestamo);
//            cs.executeUpdate();
//            
//            idEjemplarLibro = cs.getInt(1);
//            System.out.println(idEjemplarLibro +"AQUI LLEGO");
//            
//        } catch(SQLException ex) {
//            ConexionDB.handleException(new PrestamoDB().getClass(), ex);
//        }
        if(idEjemplarLibro != 0){
            ConexionDB.connect();
             try {
                Connection conn = ConexionDB.getConnection();
                CallableStatement cs = conn.prepareCall("{? = CALL updateDetallePrestamo(?)}");
                cs.registerOutParameter(1, Types.INTEGER);
                cs.setInt(2, idEjemplarLibro);
                

                cs.executeUpdate();
                return cs.getInt(1);
            } catch(SQLException ex) {
                ConexionDB.handleException(new PrestamoDB().getClass(), ex);
            }
        }
       
        return 0;
    }
    
    public static int existePrestamo(int idEjemplar){
         ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            CallableStatement cs = conn.prepareCall("{? = CALL existePrestamo(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, idEjemplar);
            
                      
            cs.executeUpdate();
            
            return cs.getInt(1);
        } catch(SQLException ex) {
            ConexionDB.handleException(new PrestamoDB().getClass(), ex);
        }
        return 0;
    }
}
