/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.text.SimpleDateFormat;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author franc
 */
public class Util {
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public static String replaceVariables(String tpt, String ... vars) {
        for(int i=0;i<vars.length;i++) {
            tpt = tpt.replace("{" + i + "}", vars[i]);
        }
        return tpt;
    }
    
    public static String format(java.sql.Date fecha) {
        return dateFormat.format(fecha);
    }
    
    public static String buildInputPassword(JComponent parent, String title) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ingrese su contraseña:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Ok", "Cancelar"};
        int option = JOptionPane.showOptionDialog(parent, panel, title,
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            return new String(password);
        }
        return null;
    }
    
    public static int buildPanelPane(JComponent parent, String title, JPanel panel,
            String [] options, int defOption) {
        int option = JOptionPane.showOptionDialog(parent, panel, title,
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[defOption]);
        return option;
    }
    
}
