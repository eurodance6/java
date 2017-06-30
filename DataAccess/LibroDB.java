/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Biblioteca;
import Modelo.EstadoEjemplar;
import Modelo.Libro;
import Modelo.Piso;
import Modelo.Privilegio;
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
public class LibroDB {
    
    public int getLibrosCount(String query) {
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        
        try {
            Session session = Session.getSession();
            
            DBSelector sel = new DBSelector("Libro");
            sel.addColumns("COUNT(idLibro) as numResultados");
            sel.addConditions("CONCAT(titulo, ' ', isbn, ' ', editorial, ' ', edicion, ' edicion ', anhoPublicacion) LIKE ?");
            session.addSessionConditions(sel, "Libro");
            String sql = sel.generateQuery();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ConexionDB.formatQuery(query));
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
                return rs.getInt("numResultados");
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(LibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList<Libro> getLibros(String query, int offset, int len) {
        ArrayList<Libro> libros = new ArrayList<>();
        AutorDB autordb = new AutorDB();
        TemaDB temadb = new TemaDB();
        EjemplarLibroDB ejemplardb = new EjemplarLibroDB();
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Libro");
        sel.addCondition("CONCAT(titulo, ' ', isbn, ' ', editorial, ' ', edicion, ' edicion ', anhoPublicacion) LIKE ? LIMIT ?, ? ");        
//         "SELECT * FROM Libro "
//                            + "WHERE CONCAT(titulo, ' ', isbn, ' ', "
//                            + "editorial, ' ', edicion, ' edicion ', "
//                            + "anhoPublicacion) LIKE ? LIMIT ?, ?"
        try {
            session.addSessionConditions(sel, "Libro");
            String sql = sel.generateQuery();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ConexionDB.formatQuery(query));
            ps.setInt(2, offset);
            ps.setInt(3, len);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idLibro");
                String titulo = rs.getString("titulo");
                int numPag = rs.getInt("numPaginas");
                String isbn = rs.getString("isbn");
                String idioma = rs.getString("idioma");
                String editorial = rs.getString("editorial");
                int edicion = rs.getInt("edicion");
                int anhoPublicacion = rs.getInt("anhoPublicacion");
                String portada = rs.getString("portada");
                String portadaGrande = rs.getString("portadaGrande");
                
                Libro libro = new Libro(titulo, anhoPublicacion, numPag, isbn,
                        idioma, editorial, edicion, portada, portadaGrande);
                libro.setId(id);
                libro.setAutores(autordb.getAutores(id));
                libro.setTemas(temadb.getTemas(id));
                libro.setEjemplares(ejemplardb.getEjemplares(libro));
                
                libros.add(libro);
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(LibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return libros;
    }
    
    public void crearLibro(Libro libro) {
    }
    
    public void moverLibros(Biblioteca biblioOrigen, Piso pisoOrigen, 
            Biblioteca biblioDest, Piso pisoDest, boolean mantenimiento) {
        ConexionDB.connect();
        
        try {
            String sqlMant = "";
            if(mantenimiento)
                sqlMant = ", idEstadoEjemplar=" + EstadoEjemplar.EN_MANTENIMIENTO.id;
            
            DBUpdater upt = new DBUpdater("EjemplarLibro", "idPiso="+pisoDest.getId()+
                    ", idBiblioteca="+biblioDest.getID() + sqlMant);
            upt.addConditions("idPiso=" + pisoOrigen.getId(), "idBiblioteca=" + biblioOrigen.getID());
            upt.addCondition("(idEstadoEjemplar=" + EstadoEjemplar.DISPONIBLE + " OR idEstadoEjemplar="
                    + EstadoEjemplar.EN_MANTENIMIENTO + " OR idEstadoEjemplar="
                    + EstadoEjemplar.NO_RECUPERADO + " OR idEstadoEjemplar="
                    + EstadoEjemplar.INACTIVO + ")");
            Session.getSession().addSessionConditions(upt, "EjemplarLibro", Privilegio.ADD_BOOKS);
            
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch (SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(LibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Libro getLibro(int bookId) {
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        
        EjemplarLibroDB ejemplardb = new EjemplarLibroDB();
        AutorDB autordb = new AutorDB();
        TemaDB temadb = new TemaDB();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Libro");
        sel.addCondition("idLibro=?");
        //"SELECT * FROM Libro WHERE idLibro=?"
        try {
            session.addSessionConditions(sel, "Libro");
            String sql = sel.generateQuery();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("idLibro");
                String titulo = rs.getString("titulo");
                int numPag = rs.getInt("numPaginas");
                String isbn = rs.getString("isbn");
                String idioma = rs.getString("idioma");
                String editorial = rs.getString("editorial");
                int edicion = rs.getInt("edicion");
                int anhoPublicacion = rs.getInt("anhoPublicacion");
                String portada = rs.getString("portada");
                String portadaGrande = rs.getString("portadaGrande");
                
                Libro libro = new Libro(titulo, anhoPublicacion, numPag, isbn, idioma, editorial, edicion, portada, portadaGrande);
                libro.setId(id);
                libro.setAutores(autordb.getAutores(id));
                libro.setTemas(temadb.getTemas(id));
                libro.setEjemplares(ejemplardb.getEjemplares(libro));
                
                return libro;
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(LibroDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
