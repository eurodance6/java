/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Bibliotecario;
import Modelo.Privilegio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class PrivilegioDB {
    
    public ArrayList<Privilegio> getPrivilegios(Bibliotecario biblio) {
        ArrayList<Privilegio> privilegios = new ArrayList<>();
        
        ConexionDB.connect();
        try {
            DBSelector sel = new DBSelector("RolXBibliotecario");
            sel.addColumns("idRol");
            sel.addCondition("idUsuario=" + biblio.getCodigo());
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sel.generateQuery());
            while(rs.next()) {
                Privilegio p = Privilegio.getPrivilegio(rs.getInt("idRol"));
                
                if(p!=null) privilegios.add(p);
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        }
        
        return privilegios;
    }
    
}
