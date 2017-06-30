/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alulab14
 */
public class ConexionDB {
    
    private static Connection conn = null;
    
    public static String getLock(Class _class, String tableName) {
        connect();
        try {
            DBUpdater upt = new DBUpdater("Locker", "locked=1, lockStart=NOW(), object=uuid()");
            upt.addConditions("locked=FALSE", "tableName=?");
            PreparedStatement ps = conn.prepareStatement(upt.generateQuery());
            ps.setString(1, tableName);
            
            if(ps.executeUpdate()==1) {
                DBSelector select = new DBSelector("Locker");
                select.addColumn("object");
                select.addConditions("locked=TRUE", "tableName=?");
                
                ps = conn.prepareStatement(select.generateQuery());
                ps.setString(1, tableName);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    return rs.getString("object");
            }
        } catch(SQLException ex) {
            handleException(_class, ex);
        }
        return null;
    }
    
    public static boolean hasLock(Class _class, String tableName) {
        connect();
        try {
            DBSelector select = new DBSelector("Locker");
            select.addConditions("tableName=?", "object=?", "locked=TRUE");
            PreparedStatement ps = conn.prepareStatement(select.generateQuery());
            
            ps.setString(1, tableName);
            ps.setString(2, Session.getSession().getLock(tableName));
            
            return ps.executeQuery().next();
        } catch(SQLException ex) {
            handleException(_class, ex);
        }
        return false;
    }
    
    public static boolean releaseLock(Class _class, String tableName, String lock) {
        connect();
        try {
            DBUpdater upt = new DBUpdater("Locker", "locked=FALSE, lockStart=NULL, object=NULL");
            upt.addConditions("locked=TRUE", "tableName=?", "object=?");
            PreparedStatement ps = conn.prepareStatement(upt.generateQuery());
            ps.setString(1, tableName);
            ps.setString(2, lock);
            
            return ps.executeUpdate()==1;
        } catch(SQLException ex) {
            handleException(_class, ex);
        }
        return false;
    }
    
    public static ResultSet execute(DBSelector select, Class _class) {
        connect();
        try {
            String sql = select.generateQuery();
            return conn.prepareStatement(sql).executeQuery();
        } catch(SQLException ex) {
            handleException(_class, ex);
        }
        return null;
    }
    
    public static void connect() {
        if(conn!=null) {
            try {            
                if(!conn.isClosed()) {
                    conn.createStatement().execute("SELECT 1");
                }
            } catch (SQLException ex) {
                conn = null;
            }
        }
        
        try {
            if(conn!=null && !conn.isClosed()) return;
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/inf282g4",
                    "inf282g4", "M9PbrAW7e81yFRBa");
        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo establecer la conexión con la base de datos.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con la base de datos",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR: No se encontró driver");
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se encontro driver para acceder a la base de datos",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Savepoint savepoint(Class _class, String name) {
        try {
            conn.setAutoCommit(false);
            return conn.setSavepoint(name);
        } catch (SQLException ex) {
            handleException(_class, ex);
        }
        return null;
    }
    
    public static void rollback(Class _class)  {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            handleException(_class, ex);
        }
    }
    
    public static void commit(Class _class) {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            handleException(_class, ex);
        }
    }
    
    public static String formatQuery(String query) {
        return "%" + query.replace("!", "!!").replace("%", "!%").replace("_", "!_")
                    .replace("[", "![").replace(" ", "%") + "%";
    }
    
    public static Connection getConnection(){
        return conn;
    }
    public static void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println("ERROR: Conexión no se pudo cerrar.");
            System.err.println(ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static String generateInsert(String table, int nparams) {
        String sql = "INSERT INTO " + table + " VALUES(";
        for(int i=0;i<nparams;i++) {
            if(i>0) sql += ",";
            sql += "?";
        }
        sql += ")";
        return sql;
    }
    
    public static void handleException(Class _class, SQLException ex) {
        System.err.println("In class " + _class.getName());
        System.err.println("SQLException: " + ex.getMessage());
        System.err.println("SQLState: " + ex.getSQLState());
        System.err.println("VendorError: " + ex.getErrorCode());
        
        connect();
    }
    
    public static void main(String [] args) {
        connect();
        disconnect();
    }
    
}