/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;
import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Piso;
import Modelo.Privilegio;
import Modelo.TurnoBibliotecario;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alulab14
 */
public class BibliotecarioDB {
    
    
    public  boolean crearBibliotecario(Bibliotecario bibliotecario){
        UsuarioDB udb = new UsuarioDB();
        udb.crearUsuario(bibliotecario);
        boolean resultUser = false;
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        String result = null;                //idUsuario idBiblioteca  idPiso turno
        String query  =  "INSERT INTO Bibliotecario (idUsuario, idBiblioteca, idPiso, turno) values(?,?,?,?)";
        PreparedStatement pst = null;
        
        try {
            pst = conn.prepareStatement(query);
            //En el diagrama MYSQL Workbench, los tipos de datos son Int
            pst.setInt(1, bibliotecario.getCodigo());
            pst.setInt(2, bibliotecario.getBiblioteca().getID());
            //System.out.println(bibliotecario.getBiblioteca().getID() + " "+ bibliotecario.getBiblioteca().getNombre() + "ID biblioteca");
            //System.out.println(bibliotecario.getPiso());
            //int idPiso = bibliotecario.getPiso().getId();
            //System.out.println(bibliotecario.getPiso().getId() + "bibBD");
            
            pst.setInt(3, bibliotecario.getPiso().getId());
            pst.setInt(4, bibliotecario.getTurno().getId());
            //int n
            System.out.println(bibliotecario.getTurno().getId());
            System.out.println("execute b");
            pst.executeUpdate();
            System.out.println("execute b");
            /*if( n!= 0)*/ resultUser = true; //;else resultUser = false;
            if(resultUser) result = "Bibliotecario registrado con exito , ID:"+ bibliotecario.getBiblioteca().getID();
            System.out.println(result);
        } catch (SQLException e) {
            resultUser = false;
            result = "Error en la consulta en crearBibliotecario : " + e.getMessage();
            
            System.out.println(result);
        }
//        finally{
//            try {
//                if(conn != null){
//                    conn.close();
//                    pst.close();
//                }
//            } catch (Exception e) {
//                result = "Error en finally:" + e;
//                resultUser = false;
//            }
//        }
       
        
        ConexionDB.disconnect();
        return resultUser;
    }
    
    public void moverBibliotecarios(Biblioteca biblioOrigen, Piso pisoOrigen,
            Biblioteca biblioDest, Piso pisoDest) {
        ConexionDB.connect();
        
        try {
            DBUpdater upt = new DBUpdater("Bibliotecario", "idPiso=" + pisoDest.getId() +
                    ", idBiblioteca=" + biblioDest.getID());
            upt.addConditions("idPiso=" + pisoOrigen.getId(),
                    "idBiblioteca=" + biblioOrigen.getID());
            Session.getSession().addSessionConditions(upt, "Bibliotecario", Privilegio.ADD_LIBRARIANS);
            
            Connection conn = ConexionDB.getConnection();
            conn.createStatement().executeUpdate(upt.generateQuery());
        } catch(SQLException ex) {
            ConexionDB.handleException(getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  boolean actualizarBibliotecario(Bibliotecario b){
        ConexionDB.connect();
        UsuarioDB udb = new UsuarioDB();
        //udb.actualizarUsuario(b);
        
         Connection conn = ConexionDB.getConnection();
         String sql = null; boolean resultUpdate = false; 
         //idUsuario idBiblioteca  idPiso turno
//         sql = "UPDATE Bibliotecario  set idBiblioteca = ?,idPiso =?, turno = ? " + 
//                 "where idUsuario = ? ";
        Session session = Session.getSession();
         DBUpdater upt = new DBUpdater("Bibliotecario", "idBiblioteca = ?,idPiso =?, turno = ? ");
        upt.addCondition("idUsuario = ?");

        //String query = upt.generateQuery();
        try {
            session.addSessionConditions(upt, "Bibliotecario");
            
            String query = upt.generateQuery();
            PreparedStatement pst = conn.prepareStatement(query);
           // pst.setInt(1,b.getCodigo());
            pst.setInt(1,b.getBiblioteca().getID());
            int idPiso = b.getPiso().getId();
            pst.setInt(2, idPiso);
            pst.setInt(3,b.getTurno().getId());
            pst.setInt(4,b.getCodigo()); //Es el mismo codigo del primer pst.setInt
            //int n = 
            //System.out.println("actualizo 1");
            pst.executeUpdate();
            udb.actualizarUsuario(b);
            //System.out.println("actualizo 2");
            //if(n!=0) 
            resultUpdate = true ;//; else resultUpdate =false;
        } catch (SQLException e) {
            resultUpdate = false;
            System.out.println("Error al actualizar Bibliotecario" + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(BibliotecarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }//finally{
//            try {
//                if(conn != null){
//                    conn.close();
//                    pst.close();
//                }
//            } catch (Exception e) {
//                result = "Error en finally:" + e;
//                resultUpdate = false;
//            }
//        }
        udb.actualizarUsuario(b);
        ConexionDB.disconnect();
        return resultUpdate;
          
    }
    
    public boolean eliminarBibliotecario(Bibliotecario b){
        
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        UsuarioDB udb = new UsuarioDB();
        
        boolean resultDelete = false;
        String sql = "Delete Bibliotecario where idUsuario = ?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.getCodigo());
            
            int n = pst.executeUpdate();
            udb.eliminarUsuario(b);
            //if(n!=0)
            resultDelete = true; //else resultDelete =false;
        } catch (SQLException ex) {
            ConexionDB.handleException(new BibliotecarioDB().getClass(), ex);
        }
        ConexionDB.disconnect();
        return resultDelete;
    }
    
    public static Bibliotecario getBibliotecario(int idBibliotecario) {
        ConexionDB.connect();
        
        String idBibli = Integer.toString(idBibliotecario);
        //String sql = "SELECT idBiblioteca, idPiso, turno FROM Bibliotecario WHERE idUsuario=" + idBibliotecario;
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Bibliotecario");

        sel.addColumns("idBiblioteca","idPiso","turno");
        sel.addCondition("idUsuario=" + idBibli);
        try {
            session.addSessionConditions(sel, "Bibliotecario");
            String sql = sel.generateQuery();
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if(rs.next()) {
                int idBiblioteca = rs.getInt("idBiblioteca");
                int idPiso = rs.getInt("idPiso");
                int turno = rs.getInt("turno");
                
                Bibliotecario biblio = new Bibliotecario(UsuarioDB.getUsuario(idBibliotecario), 
                        BibliotecaDB.getBiblioteca(idBiblioteca), Piso.getPiso(idPiso), TurnoBibliotecario.getTurno(turno));
                return biblio;
            }
        } catch(SQLException ex) {
            ConexionDB.handleException(new BibliotecarioDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Bibliotecario> getBibliotecarios(){
        ConexionDB.connect();
        ArrayList<Bibliotecario> bibliotecarios= new ArrayList<>();
        
//        String query = "SELECT idUsuario, idBiblioteca, idPiso, turno " +
//                                "FROM Bibliotecario";
                                //"ORDER BY Bibliotecario.idUsuario";
     
        Session session = Session.getSession();
        DBSelector sel = new DBSelector("Bibliotecario");

        sel.addColumns("idUsuario","idBiblioteca","idPiso","turno");
        
        try {
            session.addSessionConditions(sel, "Bibliotecario");
            String sql = sel.generateQuery();
            
            Connection conn = ConexionDB.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                int idTurno = rs.getInt("turno");
                int idPiso =  rs.getInt("idPiso");
                int idBiblioteca = rs.getInt("idBiblioteca");
                
                TurnoBibliotecario turno = TurnoBibliotecario.getTurno(idTurno);
                Biblioteca biblio = BibliotecaDB.getBiblioteca(idBiblioteca);
                Usuario usuario = UsuarioDB.getUsuario(idUsuario);
                Piso piso = Piso.getPiso(idPiso);
                
                Bibliotecario b = new Bibliotecario(usuario, biblio, piso, turno);

                bibliotecarios.add(b);
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(new BibliotecarioDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(BibliotecarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionDB.disconnect();
        //codigo al, numero sol, fecha, descripcion
        return bibliotecarios;
        
    }
}
