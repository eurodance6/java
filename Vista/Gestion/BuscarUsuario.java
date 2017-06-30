/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Gestion;

import DataAccess.UsuarioBibliotecaDB;
import Modelo.CorreoElectronico;
import Modelo.EnviarEmail;
import Modelo.UsuarioBiblioteca;
import Vista.DataModel.NonEditableTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alulab14
 */
public class BuscarUsuario extends javax.swing.JInternalFrame {

    private static final String [] TABLE_COLUMNS = {"ID", "Nombre", "Apellido Paterno","Apellido Materno","Tipo"};
    
    //private ArrayList<Usuario> bibliotecasDisponibles = new ArrayList<>();
    private ArrayList<UsuarioBiblioteca> usuarios = new ArrayList<>();
    
    private UsuarioBiblioteca seleccionado = null;
    
    private UsuarioBiblioteca objBiblioteca = null;
    private UsuarioBibliotecaDB dbUsuario;
    private String correoDeUsuario = null;
    private CorreoElectronico c = new CorreoElectronico();
    
    /**
     * Creates new form BuscarUsuario
     */
    public BuscarUsuario() {
        initComponents();
        dbUsuario = new  UsuarioBibliotecaDB();
        
        //gestionbibliotecarios.GestionBibliotecas.centerWindow(this);
        codigoField.setEnabled(true);
        botonLimpiar.setEnabled(false);
        botonRecordatorio.setEnabled(false);
//        areaBox.setEditable(false);
//        areaBox.setEnabled(false);
       
    }

    public void updateDataUsuarios(){
         //usuarios = dbUsuario.getUsuarios();
        String [][] data = new String[usuarios.size()][5];
        for(int i=0;i<usuarios.size();i++) {
            data[i][0] = usuarios.get(i).getCodigo()+"";
            data[i][1] = usuarios.get(i).getNombre()+ "";
            data[i][2] = usuarios.get(i).getApPaterno() + "";
            data[i][3] = usuarios.get(i).getApMaterno()+ "";
            
            data[i][4] = usuarios.get(i).getTipo()+ "";
        }
        GestionUsuarios.usuariosTable.setModel(new NonEditableTableModel(data, TABLE_COLUMNS));
    }
    
    public void enviarCorreo(){
        c.setUsuarioCorreo("biblioteca.service@gmail.com");
        c.setContrasenia("passgrupo4");
        c.setAsunto("Devolución de Préstamo");
        c.setMensaje("<html>"+
                    "<p>Su situación es de <font color='ff0000'>SUSPENDIDO</font>, por lo que no podrá realizar reservas y préstamos.</p>"
                    
                    + "<p>Le sugerimos resolverlo lo más pronto posible.</p>"
                    + "<p>Sistema de Bibliotecas UUU</p>"
                    + "</html>");
                
        c.setDestino(correoDeUsuario);
        c.setNombreArchivo("");
        c.setRutaArchivo("");
        EnviarEmail e= new EnviarEmail();
        if(e.enviarCorreo(c)){
            //System.out.println("Se envio el correo");
            JOptionPane.showMessageDialog(this,null,"El recordatorio fue enviado con éxito",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,null,"Error al enviar.Inténtelo Nuevamente",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void deshabilitarCampos(){
         nameField.setEnabled(false);
        apellidoPat.setEnabled(false);
        apellidoMat.setEnabled(false);
        emailField.setEnabled(false);
        //areaBox.setEnabled(false);
       
    }
     public void anhadirUsuario(){
         
     }
     
     public void actualizarUsuario(){
         
     }
     
     public void setCurrentUsuario(UsuarioBiblioteca u){
         
         //codigoField.setText(t);
         nameField.setText(u.getNombre());
         apellidoPat.setText(u.getApPaterno());
         apellidoMat.setText(u.getApMaterno());
         emailField.setText(u.getCorreo());
         correoDeUsuario = u.getCorreo();
         
         //System.out.println(u.getTipo().getTipo());
         //areaBox.setSelectedItem(u.getTipo().getTipo().toUpperCase());
         tipoUsuario.setText(u.getTipo().getTipo());
         nombreFacultad.setText(u.getFacultad().getNombre());
         nombreEspecialidad.setText(u.getEspecialidad().getEspecialidad());
                 
         
     }
     public void buscarUsuario(){
          if(!codigoField.isEnabled()) {
            codigoField.setEnabled(true);
            return;
        }
        boolean encontrado = false;
        int codigo = Integer.parseInt(codigoField.getText());
        
        UsuarioBiblioteca tmp = UsuarioBibliotecaDB.buscarUsuario(codigo); 
        encontrado = tmp!=null;
        
        if(encontrado){
            seleccionado = tmp;
            setCurrentUsuario(seleccionado);
        }else{
            JOptionPane.showMessageDialog(null,"El usuario no existe ");
            return;
        }
        codigoField.setEnabled(true);
        nameField.setEnabled(true);
        apellidoPat.setEnabled(true);
        apellidoMat.setEnabled(true);
        emailField.setEnabled(true);
        if(seleccionado.isHabilitado()) botonRecordatorio.setEnabled(false); else  botonRecordatorio.setEnabled(true);
        // areaBox.setEnabled(true);
        botonLimpiar.setEnabled(true);
        searchButton.setEnabled(false);
//        nuevoButton.setEnabled(false);
//        buscarButton.setEnabled(false);
//        cancelButton.setEnabled(true);
//        updateButton.setEnabled(true);
//        addButton.setEnabled(false);
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreEspecialidad = new javax.swing.JLabel();
        apellidoMat = new javax.swing.JTextField();
        codigoField = new javax.swing.JTextField();
        apellidoPat = new javax.swing.JTextField();
        botonLimpiar = new javax.swing.JButton();
        emailField = new javax.swing.JTextField();
        botonRecordatorio = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        nombreFacultad = new javax.swing.JLabel();
        tipoUsuario = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Buscar usuario");

        nombreEspecialidad.setText("<Especialidad>");

        apellidoMat.setEnabled(false);

        codigoField.setEnabled(false);

        apellidoPat.setEnabled(false);

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        emailField.setEnabled(false);

        botonRecordatorio.setText("Enviar Recordatorio");
        botonRecordatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRecordatorioActionPerformed(evt);
            }
        });

        searchButton.setText("Buscar");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido paterno:");

        jLabel4.setText("Correo electrónico:");

        jLabel3.setText("Apellido materno:");

        jLabel5.setText("ID:");

        jLabel6.setText("Facultad:");

        jLabel7.setText("Tipo :");

        jLabel8.setText("Especialidad:");

        nameField.setEnabled(false);

        nombreFacultad.setText("<Facultad>");

        tipoUsuario.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tipoUsuario.setText("<Tipo Usuario>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(apellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(apellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(78, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreEspecialidad)
                                    .addComponent(nombreFacultad))
                                .addContainerGap(151, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tipoUsuario)
                                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(botonRecordatorio)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(apellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nombreFacultad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nombreEspecialidad))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tipoUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(botonLimpiar))
                .addGap(18, 18, 18)
                .addComponent(botonRecordatorio)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        // TODO add your handling code here:
        codigoField.setText("");
        nameField.setText("");
        apellidoPat.setText("");
        apellidoMat.setText("");
        emailField.setText("");
        //areaBox.setSelectedItem("");
        tipoUsuario.setText("<Tipo Usuario>");
        nombreFacultad.setText("");
        nombreEspecialidad.setText("");
        searchButton.setEnabled(true);
        botonLimpiar.setEnabled(false);
        nombreFacultad.setEnabled(true);
        nombreEspecialidad.setEnabled(true);
        botonRecordatorio.setEnabled(false);
        deshabilitarCampos();

    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonRecordatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRecordatorioActionPerformed
        // TODO add your handling code here:
        //        Recordatorio r = new Recordatorio();
        //        r.setVisible(true);
        //
        enviarCorreo();
        botonRecordatorio.setEnabled(false);
    }//GEN-LAST:event_botonRecordatorioActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        if(codigoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el codigo de algun usuario para realizar la busqueda",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        botonRecordatorio.setEnabled(false);
        buscarUsuario();
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoMat;
    private javax.swing.JTextField apellidoPat;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonRecordatorio;
    private javax.swing.JTextField codigoField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nombreEspecialidad;
    private javax.swing.JLabel nombreFacultad;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel tipoUsuario;
    // End of variables declaration//GEN-END:variables
}
