/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.EjemplarLibro;
import Modelo.EstadoEjemplar;
import Modelo.Privilegio;
import Modelo.Reserva;
import Modelo.UsuarioBiblioteca;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class ReservaDB {
    
    private static Reserva getReserva(ResultSet rs) throws SQLException {
        int idReserva = rs.getInt("idReserva");
        int idUsuario = rs.getInt("idUsuario");
        int idEjemplarLibro = rs.getInt("idEjemplarLibro");
        Date fechaSolicitud = rs.getDate("fechaSolicitud");
        Date fechaRecojo = rs.getDate("fechaRecojo");
        
        UsuarioBiblioteca usuario = UsuarioBibliotecaDB.buscarUsuario(idUsuario);
        EjemplarLibro ejemplar = new EjemplarLibroDB().getEjemplar(idEjemplarLibro);
        
        Reserva reserva = new Reserva(ejemplar, usuario, fechaSolicitud, fechaRecojo);
        reserva.setIdReserva(idReserva);
        return reserva;
    }
    
    public Reserva getReserva(EjemplarLibro libro) {
        ConexionDB.connect();
        try {
            DBSelector sel = new DBSelector("Reserva");
            sel.addColumns("idReserva" , "idUsuario", "idEjemplarLibro", 
                    "fechaSolicitud", "fechaRecojo");
            sel.addConditions("idEjemplarLibro=" + libro.getId());
            
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            if(rs.next()) {
                return getReserva(rs);
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {    
            Logger.getLogger(ReservaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Reserva> getAllReservas(UsuarioBiblioteca usuario) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        if(!usuario.isHabilitado()) return reservas;
        
        ConexionDB.connect();
        try {
            DBSelector sel = new DBSelector("Reserva");
            sel.addColumns("idReserva" , "idUsuario", "idEjemplarLibro", 
                    "fechaSolicitud", "fechaRecojo");
            sel.addConditions("idUsuario=" + usuario.getCodigo());
            
            Session session = Session.getSession();
            session.addSessionConditions(sel, "Reserva", Privilegio.LOAN_BOOKS);
            
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()) {
                reservas.add(getReserva(rs));
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {    
            Logger.getLogger(ReservaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }
    
    public void removeAllReservas(ArrayList<Reserva> reservas) {
        for(Reserva reserva: reservas) {
            try {
                ConexionDB.connect();
                
                Connection conn = ConexionDB.getConnection();
                String sql = "DELETE FROM Reserva WHERE idUsuario=" + reserva.getUsuario().getCodigo() + 
                        " AND idEjemplarLibro=" + reserva.getLibro().getId() + " AND idReserva="
                        + reserva.getIdReserva();
                conn.createStatement().executeUpdate(sql);
                DBUpdater upt = new DBUpdater("EjemplarLibro", "idEstadoEjemplar=" + EstadoEjemplar.DISPONIBLE.id);
                upt.addConditions("idEjemplarLibro=" + reserva.getLibro().getId(),
                        "idEstadoEjemplar=" + EstadoEjemplar.RESERVADO.id);
                conn.createStatement().executeUpdate(upt.generateQuery());
            } catch (SQLException ex) {
                ConexionDB.handleException(getClass(), ex);
            }
            
        }
    }
    
}
