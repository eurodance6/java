/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franco
 */
public class AutorDB {
    
    private ArrayList<Autor> toList(ResultSet rs) throws SQLException {
        ArrayList<Autor> autores = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("idAutor");
            String nombres = rs.getString("nombres");
            String apellidos = rs.getString("apellidos");
            String pais = rs.getString("pais");

            Autor autor = new Autor(nombres, apellidos, pais);
            autor.setId(id);

            autores.add(autor);
        }
        return autores;
    }
    
    public ArrayList<Autor> getAutores(int idLibro) {
        ArrayList<Autor> autores = new ArrayList<>();
        ConexionDB.connect();
        
        try {
            Connection conn = ConexionDB.getConnection();
            Session session = Session.getSession();
            DBSelector sel = new DBSelector("Autor INNER JOIN AutorXLibro ON Autor.idAutor=AutorXLibro.idAutor  ","Autor",true);
            

            sel.addColumns("Autor.idAutor","nombres","apellidos","pais");
         
            sel.addCondition("AutorXLibro.idLibro=?");

            session.addSessionConditions(sel, "Autor");
            String sql = sel.generateQuery();

//            "SELECT Autor.idAutor, nombres, apellidos, pais FROM Autor "
//                    + "INNER JOIN AutorXLibro ON Autor.idAutor=AutorXLibro.idAutor  "
//                    + "WHERE AutorXLibro.idLibro=?"
//            

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            autores = toList(rs);
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(AutorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }
    
    public ArrayList<Autor> getAutoresFromSeparados(String sep) {
        ArrayList<Autor> autores = new ArrayList<>();
        ConexionDB.connect();
        
        try {
            String[] individuales = sep.split(";");
            
            Connection conn = ConexionDB.getConnection();
            for(String autor: individuales) {
                autor = autor.trim();
                if(autor.isEmpty()) continue;
                //Hoy
                Session session = Session.getSession();
                DBSelector sel = new DBSelector("Autor");

                sel.addColumns("Autor.idAutor","nombres","apellidos","pais");
                
                sel.addCondition("CONCAT(nombres, ' ', apellidos) LIKE ?");

                session.addSessionConditions(sel, "Autor");
                String sql = sel.generateQuery();
//                "SELECT idAutor, nombres, apellidos, pais FROM Autor " +
//                        "WHERE CONCAT(nombres, ' ', apellidos) LIKE ?"
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, autor);
                ResultSet rs = ps.executeQuery();
                ArrayList<Autor> tmpList = toList(rs);
                if(tmpList.isEmpty()) {
                    int ans = JOptionPane.showConfirmDialog(null, "No se encontro autor " + autor +
                            ". Quiere añadirlo a la base de datos?", "Autor no encontrado", 
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    switch(ans) {
                        case JOptionPane.YES_OPTION:
                            //TODO Crear autor
                            break;
                        case JOptionPane.NO_OPTION:
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            return null;
                    }
                } else if(tmpList.size()==1) {
                    autores.addAll(tmpList);
                } else {
                    //TODO Preguntar por cual autor de la lista
                }
            }
            
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(AutorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
}
