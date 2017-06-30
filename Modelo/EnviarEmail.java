/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
/**
 *
 * @author Jesus
 */
public class EnviarEmail {
      //CorreoElectronico c = new CorreoElectronico();
      
      public EnviarEmail(){
      }

    public boolean enviarCorreo(CorreoElectronico c) {
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host","smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable","true");
            p.setProperty("mail.smtp.port","587");
            p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth","true");
            
            
         
            Session s = Session.getDefaultInstance(p,null );
            BodyPart texto = new  MimeBodyPart();
            //texto.setContent(s, "t");
            texto.setContent(c.getMensaje(), "text/html" ) ; //EL mensaje debe estar en formato html
            BodyPart adjunto = new MimeBodyPart();
            if(!c.getRutaArchivo().equals("")){
                adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
                adjunto.setFileName(c.getNombreArchivo());
            }
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            if(!c.getRutaArchivo().equals("")){
                m.addBodyPart(adjunto);
            }
            
            
            
            
            MimeMessage mensaje = new MimeMessage(s); 
            mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(c.getDestino()));
            mensaje.setSubject(c.getAsunto());
            mensaje.setContent(m);
            
           
            Transport t = s.getTransport("smtp");
            System.out.println(c.getUsuarioCorreo());
            t.connect(c.getUsuarioCorreo(),c.getContrasenia());
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            System.out.println(c.getContrasenia());
            t.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al enviar mensaje");
            return false;
        }
    }
    
}
