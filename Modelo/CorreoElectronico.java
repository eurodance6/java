/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



/**
 *
 * @author Jesus
 */
public class CorreoElectronico {

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the usuarioCorreo
     */
    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    /**
     * @param usuarioCorreo the usuarioCorreo to set
     */
    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the rutaArchivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * @param rutaArchivo the rutaArchivo to set
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    private String usuarioCorreo;
    private String  contrasenia;
    private String rutaArchivo;//TODO Eliminar rutaArchivo
    private String destino;
    private String asunto;
    private String mensaje;
    
    private String nombreArchivo;
           
    //String nombreArchivo;
    public CorreoElectronico() {
        usuarioCorreo = "biblioteca.service@gmail.com";
        contrasenia = "passgrupo4";
        nombreArchivo = "";
        rutaArchivo = "";
        //TODO Poner parametros por defecto. Por ejemplo ya se deberia asignar usuarioCorreo, contrasenia que siempre seran los mismos.
    }
    

}
