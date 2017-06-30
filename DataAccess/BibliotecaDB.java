/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Biblioteca;
import Modelo.Piso;
import Modelo.PisoBiblioteca;
import Modelo.Privilegio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author franco
 */
public class BibliotecaDB {
    
    public BibliotecaDB() {
        ConexionDB.connect();
    }
    
    public PisoBiblioteca pisoBiblioteca(Biblioteca biblioteca, Piso piso) {
        ConexionDB.connect();
        
        try {
            DBSelector select = new DBSelector("PisoXBiblioteca");
            select.addColumns("activo");
            select.addConditions("idPiso=" + piso.getId(), "idBiblioteca=" + biblioteca.getID());
            Connection conn = ConexionDB.getConnection();
            
            ResultSet rs = conn.createStatement().executeQuery(select.generateQuery());
            
            if(!rs.next())
                return null;            
            boolean activo = rs.getBoolean("activo");
            
            select = new DBSelector("EjemplarLibro");
            select.addColumn("COUNT(idEjemplarLibro) AS cantidad");
            select.addConditions("idPiso=" + piso.getId(), "idBiblioteca=" + biblioteca.getID());
            rs = conn.createStatement().executeQuery(select.generateQuery());
            
            if(!rs.next())
                return null;            
            int cantLibros = rs.getInt("cantidad");
            
            select = new DBSelector("Bibliotecario");
            select.addColumn("COUNT(idUsuario) AS cantidad");
            select.addConditions("idPiso=" + piso.getId(), "idBiblioteca=" + biblioteca.getID());
            rs = conn.createStatement().executeQuery(select.generateQuery());
            
            if(!rs.next())
                return null;            
            int cantBibliotecarios = rs.getInt("cantidad");
            
            return new PisoBiblioteca(activo, cantLibros, cantBibliotecarios);
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
        return null;
    }
    
    public ArrayList<Biblioteca> getBibliotecas() {
        return getBibliotecas(true);
    }
    
    public ArrayList<Biblioteca> getBibliotecas(boolean onlyActive) {
        ConexionDB.connect();
        ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
        PisoDB pisoDB = new PisoDB();
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Biblioteca");

        sel.addColumns("idBiblioteca","nombre","activo");
        try {
            if(onlyActive) sel.addCondition("activo=TRUE");
            session.addSessionConditions(sel, "Biblioteca");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
                                                            //"SELECT idBiblioteca, nombre FROM Biblioteca"
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                Biblioteca b = new Biblioteca(rs.getInt("idBiblioteca"), rs.getString("nombre"),
                                                rs.getBoolean("activo"));
                b.addAll(pisoDB.getPisos(b.getID()));
                
                bibliotecas.add(b);
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bibliotecas;
    }
    
    public Piso addPiso(String nombre) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            DBInserter insert = new DBInserter("Piso");
            insert.addColumns("piso");
            insert.addValues("?");
            Session.getSession().addSessionConditions(insert, "Piso", Privilegio.ADD_LIBRARIES);
            
            PreparedStatement ps = conn.prepareStatement(insert.generateQuery(),
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                return Piso.getPiso(rs.getInt(1));
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addPisoTo(Piso piso, Biblioteca biblioteca) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            DBInserter insert = new DBInserter("PisoXBiblioteca");
            insert.addColumns("idPiso", "idBiblioteca");
            insert.addValues(""+piso.getId(), ""+biblioteca.getID());
            Session.getSession().addSessionConditions(insert, "PisoXBiblioteca", Privilegio.ADD_LIBRARIES);
            PreparedStatement ps = conn.prepareStatement(insert.generateQuery());
            ps.executeUpdate();
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBiblioteca(String nombre) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            DBInserter insert = new DBInserter("Biblioteca");
            insert.addColumns("nombre, activo");
            insert.addValues("?", "TRUE");
            Session.getSession().addSessionConditions(insert, "Biblioteca", Privilegio.ADD_LIBRARIES);
            PreparedStatement ps = conn.prepareStatement(insert.generateQuery());
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disableBiblioteca(Biblioteca b) {
        ConexionDB.connect();
        
        try {
            DBUpdater upt = new DBUpdater("Biblioteca", "activo=FALSE");
            upt.addConditions("idBiblioteca=" + b.getID());
            Session.getSession().addSessionConditions(upt, "Biblioteca", Privilegio.ADD_LIBRARIES);
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disablePiso(Piso p, Biblioteca b) {
        ConexionDB.connect();
        
        try {
            DBUpdater upt = new DBUpdater("PisoXBiblioteca", "activo=FALSE");
            upt.addConditions("idPiso=" + p.getId(), "idBiblioteca=" + b.getID());
            Session.getSession().addSessionConditions(upt, "PisoXBiblioteca", Privilegio.ADD_LIBRARIES);
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enableBiblioteca(Biblioteca b) {
        ConexionDB.connect();
        
        try {
            DBUpdater upt = new DBUpdater("Biblioteca", "activo=TRUE");
            upt.addConditions("idBiblioteca=" + b.getID());
            Session.getSession().addSessionConditions(upt, "Biblioteca", Privilegio.ADD_LIBRARIES);
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enablePiso(Piso p, Biblioteca b) {
        ConexionDB.connect();
        
        try {
            DBUpdater upt = new DBUpdater("PisoXBiblioteca", "activo=TRUE");
            upt.addConditions("idPiso=" + p.getId(), "idBiblioteca=" + b.getID());
            Session.getSession().addSessionConditions(upt, "PisoXBiblioteca", Privilegio.ADD_LIBRARIES);
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBiblioteca(Biblioteca b) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            DBInserter insert = new DBInserter("Biblioteca");
            insert.addColumns("idBiblioteca", "nombre", "activo");
            insert.addValues("DEFAULT", "?", (b.isActiva()?"TRUE":"FALSE"));
            Session.getSession().addSessionConditions(insert, "Biblioteca", Privilegio.ADD_LIBRARIES);
            PreparedStatement ps = conn.prepareStatement(insert.generateQuery(),
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, b.getNombre());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                b.setID(rs.getInt(1));
            }
            PisoDB pisodb = new PisoDB();
            pisodb.setPisos(b.getID(), b.getPisos());
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBiblioteca(Biblioteca b) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Biblioteca SET nombre=? WHERE idBiblioteca=?");
            ps.setString(1, b.getNombre());
            ps.setInt(2, b.getID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
        
        PisoDB pisodb = new PisoDB();
        pisodb.setPisos(b.getID(), b.getPisos());
    }
    
    public static Biblioteca getBiblioteca(int id) {
        for(Biblioteca biblio : new BibliotecaDB().getBibliotecas()) {
            if(biblio.getID()==id) return biblio;
        }
        return null;
    }
    
    public void deleteBiblio(int idBiblioteca) {
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            PisoDB pisodb = new PisoDB();
            pisodb.deleteBiblioteca(idBiblioteca);
            
            conn.createStatement().executeUpdate("DELETE FROM Biblioteca WHERE idBiblioteca=" + idBiblioteca);
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
    }
    
}
