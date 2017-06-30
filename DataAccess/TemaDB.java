/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Tema;
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
public class TemaDB {
    
    private ArrayList<Tema> toList(ResultSet rs) throws SQLException {
        ArrayList<Tema> temas = new ArrayList<>();
        
        while(rs.next()) {
            int id = rs.getInt("idTema");
            String nombre = rs.getString("nombre");

            Tema tema = new Tema(nombre);
            tema.setId(id);

            temas.add(tema);
        }
        
        return temas;
    }
    
    public ArrayList<Tema> getTemas(int idLibro) {
        ArrayList<Tema> temas = new ArrayList<>();
        ConexionDB.connect();
        
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Tema INNER JOIN TemaXLibro ON Tema.idTema=TemaXLibro.idTema ","Tema",true);
        sel.addColumns("Tema.idTema","nombre");
       
        sel.addCondition("TemaXLibro.idLibro=?");
        
        
        //ResultSet rsAll = ConexionDB.execute(sel, getClass());
        
//        "SELECT Tema.idTema, nombre FROM Tema "
//                    + "INNER JOIN TemaXLibro ON Tema.idTema=TemaXLibro.idTema "
//                    + "WHERE TemaXLibro.idLibro=?"
        try {
            session.addSessionConditions(sel, "Tema");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            temas = toList(rs);
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(TemaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temas;
    }
    
    //En la cadena, los temas estan separados por ';'
    public ArrayList<Tema> getTemasFromSeparados(String sep) {
        ArrayList<Tema> temas = new ArrayList<>();
        ConexionDB.connect();
        
        String[] individuales = sep.split(";");
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Tema");

        sel.addColumns("idTema","nombre");
        
        sel.addCondition("nombre=?");
        
        
        try {
            //        "SELECT idTema, nombre FROM Tema "
            //                        + "WHERE nombre=?"
            session.addSessionConditions(sel, "Tema");
        } catch (Exception ex) {
            Logger.getLogger(TemaDB.class.getName()).log(Level.SEVERE, null, ex);
            return temas;
        }
        String sql = sel.generateQuery();
        for(String tema : individuales ) {
            tema = tema.trim();
            if(tema.isEmpty()) continue;
            
            try {
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, tema);
                ResultSet rs = ps.executeQuery();
                
                ArrayList<Tema> tmp = toList(rs);
                if(tmp.isEmpty()) {
                    int ans = JOptionPane.showConfirmDialog(null, "El tema " + tema + " no se encuentra disponible. Continuar?",
                            "Tema no encontrado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR);
                    
                    if(ans==JOptionPane.NO_OPTION) return null;
                } else if(tmp.size()==1) {
                    temas.addAll(tmp);
                }
            } catch(SQLException ex) {
                ConexionDB.handleException(getClass(), ex);
            }
        }
        
        return temas;
    }
    
}
