/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;
import Modelo.Especialidad;
import Modelo.TipoUsuario;
import Modelo.UsuarioBiblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jesus
 */
public class UsuarioBibliotecaDB {
    
     public  boolean crearUsuarioBiblioteca(UsuarioBiblioteca user){
        UsuarioDB udb = new UsuarioDB();
        udb.crearUsuario(user);
        boolean resultUser = false;
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        String result = null;                //idUsuario idBiblioteca  idPiso turno
        String query  =  "INSERT INTO UsuarioBiblioteca (idUsuario, idTipoUsuario, idEspecialidad, habilitado) values(?,?,?,?)";
        PreparedStatement pst = null;
        
        try {
            pst = conn.prepareStatement(query);
            //En el diagrama MYSQL Workbench, los tipos de datos son Int
            pst.setInt(1, user.getCodigo());
            //agregar switch para alumno o profesor
//            pst.setInt(2, user.getTipo());
//            //crear metodo getFaculta(idEspecialidad) haciendo un match con Especialidad y Facultad
//            int especialidad = getEspecialidad(user.getFacultad()); //hacer un query
//            pst.setInt(3,espcialidad);
//            pst.setInt(4, user.getHabilitado());
//            //AQUI FALTA AGREGAR PARA SETEAR MAXIMA RESERVA
            //int n
            System.out.println("execute b");
            pst.executeUpdate();
            System.out.println("execute b");
            /*if( n!= 0)*/ resultUser = true; //;else resultUser = false;
            if(resultUser) result = "Usuario registrado con exito , ID:"+ user.getCodigo();
            System.out.println(result);
        } catch (Exception e) {
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
    
    public  boolean actualizarUsuarioBiblioteca(UsuarioBiblioteca user){
//        ConexionDB.connect();
//        UsuarioDB udb = new UsuarioDB();
//        //udb.actualizarUsuario(b);
//        
//         Connection conn = ConexionDB.getConnection();
//         String sql = null; boolean resultUpdate = false; 
//         //idUsuario idBiblioteca  idPiso turno
//         sql = "UPDATE UsuarioBiblioteca  set idTipoUsuario = ?,idEspecialidad =?, habilitado = ? " + 
//                 "where idUsuario = ? ";
//        
//        try {
//               PreparedStatement pst = conn.prepareStatement(sql);
//              // pst.setInt(1,b.getCodigo());
//               pst.setInt(1,user.getTipo());
//               int numero_piso = getPisoBibliotecario(b.getArea());
//               pst.setInt(2,numero_piso);
//               pst.setInt(3,b.getTurno().getId());
//               pst.setInt(4,b.getCodigo()); //Es el mismo codigo del primer pst.setInt
//               //int n = 
//               System.out.println("actualizo 1");
//               pst.executeUpdate();
//               udb.actualizarUsuario(b);
//               System.out.println("actualizo 2");
//               //if(n!=0) 
//               resultUpdate = true ;//; else resultUpdate =false;
//        } catch (SQLException e) {
//            resultUpdate = false;
//            System.out.println("Error al actualizar Bibliotecario" + e.getMessage());
//        }//finally{
////            try {
////                if(conn != null){
////                    conn.close();
////                    pst.close();
////                }
////            } catch (Exception e) {
////                result = "Error en finally:" + e;
////                resultUpdate = false;
////            }
////        }
//        udb.actualizarUsuario(b);
//        ConexionDB.disconnect();
        return false;
          
    }
    
    public boolean eliminarUsuarioBiblioteca(UsuarioBiblioteca user){
        
//        ConexionDB.connect();
//        Connection conn = ConexionDB.getConnection();
//        UsuarioDB udb = new UsuarioDB();
//        
//        boolean resultDelete = false;
//        String sql = "Delete Bibliotecario where idUsuario = ?";
//        
//        try {
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setInt(1, b.getCodigo());
//            
//            int n = pst.executeUpdate();
//            udb.eliminarUsuario(b);
//            //if(n!=0)
//            resultDelete = true; //else resultDelete =false;
//        } catch (SQLException ex) {
//            ConexionDB.handleException(ex);
//        }
//        ConexionDB.disconnect();
        return false;
    }
    
    public ArrayList<UsuarioBiblioteca> getUsuarios(int offset, int len){
        ConexionDB.connect();
        ArrayList<UsuarioBiblioteca> usuarios= new ArrayList<>();
        
//        String query = "SELECT Usuario.idUsuario, Usuario.nombre, Usuario.apellidoPaterno, Usuario.apellidoMaterno,UsuarioBiblioteca.idTipoUsuario " +
//                        " FROM UsuarioBiblioteca " +
//                         " INNER JOIN Usuario ON Usuario.idUsuario = UsuarioBiblioteca.idUsuario ";

      // sqlold
//          String query = "SELECT idUsuario, idTipoUsuario, idEspecialidad " +
//                            " FROM UsuarioBiblioteca " +
//                            " ORDER BY UsuarioBiblioteca.idUsuario LIMIT " + offset + ", " + len;
                                

        try {
            Connection conn = ConexionDB.getConnection();
            //Agregado hoy
            Session session = Session.getSession();
            DBSelector sel = new DBSelector("UsuarioBiblioteca");

            sel.addColumns("idUsuario","idTipoUsuario","idEspecialidad", "habilitado");
            sel.setTail(" ORDER BY UsuarioBiblioteca.idUsuario LIMIT " + offset + ", " + len);


            session.addSessionConditions(sel, "UsuarioBiblioteca");
            String sql = sel.generateQuery();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                int codigo = rs.getInt("idUsuario");
                int tipo = rs.getInt("idTipoUsuario");
                TipoUsuario tipoUsuario = TipoUsuario.getTipoUsuario(tipo);
                boolean habilitado = rs.getBoolean("habilitado");
                
                int idEspecialidad = rs.getInt("UsuarioBiblioteca.idEspecialidad");
                Especialidad especialidad = EspecialidadDB.getEspecialidad(idEspecialidad);
                
                UsuarioBiblioteca ub = 
                        new UsuarioBiblioteca(UsuarioDB.getUsuario(codigo),tipoUsuario,especialidad,
                                habilitado);
                usuarios.add(ub);
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioBibliotecaDB().getClass(), ex);
        } catch (Exception ex) {
             Logger.getLogger(UsuarioBibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        ConexionDB.disconnect();
        //codigo al, numero sol, fecha, descripcion
        return usuarios;
        
    } 
    
  
    public static UsuarioBiblioteca buscarUsuario(int codigo) {
        ConexionDB.connect();
        
        String cod = Integer.toString(codigo);
        try {
            Connection conn = ConexionDB.getConnection();
            Session session = Session.getSession();
            DBSelector sel = new DBSelector("UsuarioBiblioteca");

            sel.addColumns("idTipoUsuario","idEspecialidad", "habilitado");

            sel.addCondition("idUsuario="+ cod);
            session.addSessionConditions(sel, "UsuarioBiblioteca");
            String sql = sel.generateQuery();
//            "SELECT "
//                  + "idTipoUsuario, idEspecialidad FROM UsuarioBiblioteca "
//                    + "WHERE idUsuario="+codigo
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            if(rs.next()) {
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                int idEspecialidad = rs.getInt("idEspecialidad");
                Especialidad especialidad = EspecialidadDB.getEspecialidad(idEspecialidad);
                boolean habilitado = rs.getBoolean("habilitado");
                
                UsuarioBiblioteca ub = new UsuarioBiblioteca( UsuarioDB.getUsuario(codigo), 
                        TipoUsuario.getTipoUsuario(idTipoUsuario),especialidad, habilitado);
                
                return ub;
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioBibliotecaDB().getClass(), ex);
        } catch (Exception ex) {
             Logger.getLogger(UsuarioBibliotecaDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
    
}
 