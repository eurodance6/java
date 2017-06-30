package Modelo;

import java.util.ArrayList;
import java.util.Random;
enum EstadoUsuario{ACTIVO,INACTIVO};
public class Usuario {

    private int codigo;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String direccion;
   // private String usuario;
    private String correo;
    private String password;
    private boolean sesionIniciada;
    private boolean activo;
    private boolean esBibliotecario;
    
    public Usuario(Usuario usuario) {
        this(usuario.codigo, usuario.nombre, usuario.apPaterno, usuario.apMaterno, usuario.direccion,
                usuario.correo, usuario.password, usuario.sesionIniciada, usuario.activo, usuario.esBibliotecario);
    }
    
    //Ya no considerar el campo string usuario  
    public Usuario(int codigo, String nombre, String apPaterno, String apMaterno, String direccion,
            String correo, String password, boolean sesionIniciada, boolean activo,boolean esBibliotecario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.direccion = direccion;
        //this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.sesionIniciada = sesionIniciada;
        this.activo = activo;
        this.esBibliotecario = esBibliotecario;
    }
    
    public void iniciarSesion(String usuario, String password) {}
    public void cerrarSesion() {}
    public void buscarLibro(String busqueda) {}
    public void buscarLibro(String titulo, String autor, String tipo, ArrayList<Integer> idTemas) {}

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
      /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    public String getNombreCompleto() {
        return getNombre() + " " + getApPaterno() + " " + getApMaterno();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apPaterno
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * @param apPaterno the apPaterno to set
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * @return the apMaterno
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * @param apMaterno the apMaterno to set
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the sesionIniciada
     */
    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    /**
     * @param sesionIniciada the sesionIniciada to set
     */
    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

     /**
     * @return the esBibliotecario
     */
    public boolean isEsBibliotecario() {
        return esBibliotecario;
    }

    /**
     * @param esBibliotecario the esBibliotecario to set
     */
    public void setEsBibliotecario(boolean esBibliotecario) {
        this.esBibliotecario = esBibliotecario;
    }
    /**
     * @return the usuario
     */
//    public String getUsuario() {
//        return usuario;
//    }
//
//    /**
//     * @param usuario the usuario to set
//     */
//    public void setUsuario(String usuario) {
//        this.usuario = usuario;
//    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public static String generarPassword(String validChars, int len) {
        char[] password = new char[len];
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < len; i++) {
            password[i] = validChars.charAt(rand.nextInt(validChars.length()));
        }
        return new String(password);
    }
    
    public void enviarCorreo(String subject, String message) {
    }
    
}
