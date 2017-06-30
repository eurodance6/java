/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alulab14
 */
public class UsuarioDB {
    
    public UsuarioDB() {
        //ConexionDB.connect();
    }
    
    public boolean crearUsuario(Usuario user){
        Boolean resultUser;
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        String result = null;
        
        //idUsuario nombre ApePat ApeMat correoElectronic password direccion sesionIniciada activo esBibliotecario
        String query  =  "INSERT INTO Usuario values(?,?,?,?,?,?,?,?,?,?)"; //10
        
        PreparedStatement pst = null;
        
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, user.getCodigo());
            pst.setString(2, user.getNombre());
            pst.setString(3, user.getApPaterno());
            pst.setString(4, user.getApMaterno());
            pst.setString(5, user.getCorreo());
            pst.setString(6, user.getPassword());
             pst.setString(7, user.getDireccion());
            pst.setBoolean(8, user.isSesionIniciada());
            pst.setBoolean(9, user.isActivo());
            pst.setBoolean(10, user.isEsBibliotecario());
            
            
            
            int n= pst.executeUpdate();
            
            if( n!= 0) resultUser = true;else resultUser = false;  
            if(resultUser) result = "Usuario registrado con exito , ID:"+ user.getCodigo();
            
        } catch (Exception e) {
            result = "Error en la consulta : " + e.getMessage();
            resultUser = false;
        }
//        finally{
//            try {
//                if(conn != null){
//                    conn.close();
//                    pst.close();
//                }
//            } catch (Exception e) {
//                result = "Error:" + e;
//                resultUser = false;
//            }
//        }
        ConexionDB.disconnect();
        System.out.println(result + "usuarioDB");
        return resultUser;
    }
//    public static void main(String [] args) {
//        UsuarioDB udb = new UsuarioDB();
//        udb.crearUsuario(new Usuario(12, "asdf", "asdf", "asdf", "asdf", "asdf", "asdfa", false, true));
//        ConexionDB.disconnect();
//    }
    
    public int login(String codigo, String pass) {
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        String sql = "{? = CALL loginUser(?, ?)}";
        try {                                           //Porque correo electronico?
            CallableStatement cs = conn.prepareCall(sql);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, Integer.parseInt(codigo));
            cs.setString(3, pass);
            
            cs.execute();
            return cs.getInt(1);
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioDB().getClass(), ex);
        } catch(Exception ex) {
            return -1;
        }
        return -1;
    }
    
    public static Usuario getUsuario(int idUsuario) {
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        
        
       Session session = Session.getSession();
        DBSelector sel = new DBSelector("Usuario");
        sel.addCondition("idUsuario=?");
        
        try {                                      //"SELECT * FROM Usuario WHERE idUsuario=?"
            session.addSessionConditions(sel, "Usuario");
            String sql = sel.generateQuery();
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidoPat = rs.getString("apellidoPaterno");
                String apellidoMat = rs.getString("apellidoMaterno");
                String correoElectronico = rs.getString("correoElectronico");
                String direccion = rs.getString("direccion");
                boolean sesionIniciada = rs.getBoolean("sesionIniciada");
                boolean activo = rs.getBoolean("activo");
                boolean esBibliotecario = rs.getBoolean("esBibliotecario");
                
                Usuario objUsuario = 
                        new Usuario(idUsuario, nombre, apellidoPat, apellidoMat,
                                direccion, correoElectronico, null, sesionIniciada, activo, esBibliotecario);
                return objUsuario;
            }
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioDB().getClass(), ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //falta actualizar y getUsuarios
//    public static Usuario buscarUsuario(String clave){
//        
//        //Usuario user = new Usuario();
//        String result = null;
//        ConexionDB.connect();
//        Connection conn = ConexionDB.getConnection();
//         PreparedStatement pst = null;
//         
//        return user;
//    } 
    
     public boolean actualizarUsuario(Usuario user){
        ConexionDB.connect();
        
         Connection conn = ConexionDB.getConnection();
         String result;
         String sql = null; boolean resultUpdate = false; 
         Session session = Session.getSession();
         DBUpdater upt;
         //idUsuario nombre apellidoPaterno apellidoMaterno correoElectronico password direccion sesionIniciada activo esBibliotecario
         if(user.getPassword()==null) {
//            sql = "UPDATE Usuario  set  nombre = ?,apellidoPaterno =?, apellidoMaterno = ?,correoElectronico = ?,direccion = ?,sesionIniciada = ?," + 
//                     " activo = ?,esBibliotecario = ? " +
//                    " where idUsuario = ? ";
                upt = new DBUpdater("Usuario", "nombre = ?,apellidoPaterno =?, apellidoMaterno = ?,correoElectronico = ?,direccion = ?,sesionIniciada = ?," + 
                                    " activo = ?,esBibliotecario = ? ");
         } else {
//            sql = "UPDATE Usuario  set  nombre = ?,apellidoPaterno =?, apellidoMaterno = ?,correoElectronico = ?,password = ?,direccion = ?,sesionIniciada = ?," + 
//                     " activo = ?,esBibliotecario = ? " +
//                    " where idUsuario = ? ";
               upt = new DBUpdater("Usuario", "nombre = ?,apellidoPaterno =?, apellidoMaterno = ?,correoElectronico = ?,password = ?,direccion = ?,sesionIniciada = ?,"+
                                        " activo = ?,esBibliotecario = ? ");
         }
         upt.addCondition("idUsuario = ?");
        try {
            session.addSessionConditions(upt, "Usuario");
            
                String query = upt.generateQuery();
               PreparedStatement pst = conn.prepareStatement(query);
                //pst.setInt(1, user.getCodigo());
                pst.setString(1, user.getNombre());
                pst.setString(2, user.getApPaterno());
                pst.setString(3, user.getApMaterno());
                pst.setString(4,user.getCorreo());
                if(user.getPassword()!=null) {
                    pst.setString(5, user.getPassword());
                    pst.setString(6, user.getDireccion());
                    pst.setBoolean(7, user.isSesionIniciada());
                    pst.setBoolean(8, user.isActivo());
                    pst.setBoolean(9, user.isEsBibliotecario());
                    pst.setInt(10,user.getCodigo());
                } else  {
                    pst.setString(5, user.getDireccion());
                    pst.setBoolean(6, user.isSesionIniciada());
                    pst.setBoolean(7, user.isActivo());
                    pst.setBoolean(8, user.isEsBibliotecario());
                    pst.setInt(9, user.getCodigo());
                }
               //int n = 
               System.out.println("actulizo usuario 1" + user.getCodigo());
               pst.executeUpdate();
               
               System.out.println("actulizo usuario 2");
               //if( n!= 0) 
               resultUpdate = true; //else resultUpdate = false;  
               if(resultUpdate) result = "Usuario actualizado con exito , ID:"+ user.getCodigo();
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioDB().getClass(), ex);
            System.out.println(ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConexionDB.disconnect();
        return resultUpdate;
          
    }
    
    public boolean eliminarUsuario(Usuario user){
        
        ConexionDB.connect();
        Connection conn = ConexionDB.getConnection();
        boolean resultDelete = false;
        String sql = "Delete Usuario where idUsuario = ?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getCodigo());
            
            int n = pst.executeUpdate();
            if(n!=0) resultDelete = true; else resultDelete =false;
        } catch (SQLException ex) {
            ConexionDB.handleException(new UsuarioDB().getClass(), ex);
        }
        ConexionDB.disconnect();
        return resultDelete;
    }
    
    public ArrayList<Usuario> getUsuarios(){
        return null;
    }
}
