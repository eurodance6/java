/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Gestion;
import DataAccess.BibliotecarioDB;
import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Piso;
import Modelo.TurnoBibliotecario;
import Modelo.Usuario;
import Vista.DataModel.NonEditableTableModel;
import Vista.Listener.ChangeStringListener;
import Vista.Selectors.ListaBibliotecas;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Jesus
 */
public class RegistroBibliotecario extends javax.swing.JFrame implements ChangeStringListener {

    private ArrayList<Biblioteca> bibliotecasDisponibles = new ArrayList<>();
    private ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
    private ArrayList<TurnoBibliotecario> turnos = new ArrayList<>();
    private ArrayList<Piso> pisos = new ArrayList<Piso>();
    
    private Bibliotecario seleccionado = null;
    
    private Biblioteca objBiblioteca = null;
    //private static JTable table;  //Ini_mod
   
    
    private BibliotecarioDB dbBibliotecario = new BibliotecarioDB();
    private static final String [] TABLE_COLUMNS = {"ID", "Biblioteca", "Area","Turno","Encargado"};
    public RegistroBibliotecario() {
        initComponents();
        this.setResizable(false);
        Biblioteca b1 = new Biblioteca(1, "Biblioteca Central", true);
        bibliotecasDisponibles.add(b1);
        bibliotecasDisponibles.add(new Biblioteca(2, "Complejo de Innovación Académica", true));
        bibliotecasDisponibles.add(new Biblioteca(3, "Biblioteca de EEGGCC", true));
        bibliotecasDisponibles.add(new Biblioteca(4, "Biblioteca de EEGGLL", true));
        
        
        Piso p1 = new Piso(5,"Sótano 2");
        pisos.add(p1);
        pisos.add(new Piso(5,"Sótano 2"));
        pisos.add(new Piso(1,"Piso 1"));
        pisos.add(new Piso(2,"Piso 2"));
        pisos.add(new Piso(3,"Piso 3"));
        
        TurnoBibliotecario turno;
        turnos.add(TurnoBibliotecario.MAÑANA);
        turnos.add(TurnoBibliotecario.TARDE); 
        turnos.add(TurnoBibliotecario.NOCHE); 
        gestionbibliotecarios.GestionBibliotecas.centerWindow(this);  
        
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        cancelButton.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        buscarButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        areaBox = new javax.swing.JComboBox<>();
        turnoBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        selectedBiblioteca = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        apellidoMat = new javax.swing.JTextField();
        apellidoPat = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        codigoField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nuevoButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tfDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        emailField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellido materno:");

        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Correo electrónico:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Codigo:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Área:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Turno:");

        areaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sótano 2", "Sótano 1", "Piso 1", "Piso 2", "Piso 3", " " }));
        areaBox.setEnabled(false);

        turnoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mañana", "Tarde", "Noche" }));
        turnoBox.setEnabled(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Biblioteca:");

        selectedBiblioteca.setText("<Haga click aquí>");
        selectedBiblioteca.setEnabled(false);
        selectedBiblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedBibliotecaMouseClicked(evt);
            }
        });

        nameField.setEnabled(false);

        apellidoMat.setEnabled(false);

        apellidoPat.setEnabled(false);

        emailField.setEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Apellido paterno:");

        nuevoButton.setText("Nuevo");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        addButton.setText("Añadir");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Actualizar");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Direccion:");

        tfDireccion.setText("<autogenerado>");
        tfDireccion.setEnabled(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Password:");

        emailField2.setText("<autogenerado>");
        emailField2.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectedBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(turnoBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nuevoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buscarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellidoPat)
                                    .addComponent(apellidoMat)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailField)
                                    .addComponent(tfDireccion)
                                    .addComponent(emailField2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(emailField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedBiblioteca)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(areaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(turnoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(nuevoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(buscarButton))
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void setCurrentBibliotecario(Bibliotecario b) {
        nameField.setText(b.getNombre());
        apellidoPat.setText(b.getApPaterno());
        apellidoMat.setText(b.getApMaterno());
        emailField.setText(b.getCorreo());
        
        objBiblioteca = b.getBiblioteca();
        
        System.out.println(objBiblioteca.getNombre());
        
        selectedBiblioteca.setText(objBiblioteca.getNombre());
        System.out.println(b.getTurno());
        turnoBox.setSelectedItem(b.getTurno().getTurno());
        //areaBox.setSelectedIndex(b.getPiso().getPiso());  //pisos.indexOf(b.getPiso()));
        areaBox.setSelectedItem(b.getPiso().getPiso());
        System.out.println(b.getPiso().getPiso());
    }
    
    //BOTON BUSCAR
    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        // TODO add your handling code here:
        buscarBibliotecario();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void selectedBibliotecaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectedBibliotecaMouseClicked
        // TODO add your handling code here:
        if(selectedBiblioteca.isEnabled()) {
            new ListaBibliotecas(this, true, this, bibliotecasDisponibles).setVisible(true);
        }
    }//GEN-LAST:event_selectedBibliotecaMouseClicked

    
    //BOTON NUEVO
    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        nuevoBibliotecario();
        
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        cancelarBibliotecario();
        cleanCampos();
        buscarButton.setEnabled(true);
        cancelButton.setEnabled(false);
        JOptionPane.showMessageDialog(this,"       Operación cancelada   "," " , JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        anhadirBibliotecario();          
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        actualizarBibliotecario();          
    }//GEN-LAST:event_updateButtonActionPerformed
    private void mostrarMensajeError(String title, String msg) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    
   public void buscarBibliotecario() {
        // TODO add your handling code here:
        if(!codigoField.isEnabled()) {
            codigoField.setEnabled(true);
            return;
        }
        boolean encontrado = false;
        int codigo = Integer.parseInt(codigoField.getText());
        
        bibliotecarios = dbBibliotecario.getBibliotecarios(); //Extrae los datos de la tabla Bibliotecario
        
        for(Bibliotecario b: bibliotecarios) {
            if(b.getCodigo()==codigo) {
                seleccionado = b;
                //setCurrentBibliotecario(b);
                encontrado = true;
                break;
            }
        }
        
        if(encontrado){
            setCurrentBibliotecario(seleccionado);
        }else{
            JOptionPane.showMessageDialog(null,"El bibliotecario no existe ");
            return;
        }
        codigoField.setEnabled(true);
        nameField.setEnabled(true);
        apellidoPat.setEnabled(true);
        apellidoMat.setEnabled(true);
        emailField.setEnabled(true);
        selectedBiblioteca.setEnabled(true);
        areaBox.setEnabled(true);
        turnoBox.setEnabled(true);
        
        nuevoButton.setEnabled(false);
        buscarButton.setEnabled(false);
        cancelButton.setEnabled(true);
        updateButton.setEnabled(true);
        addButton.setEnabled(false);
    }
     
    public void updateDataBibliotecarios(){
      
        bibliotecarios = dbBibliotecario.getBibliotecarios();
        String [][] data = new String[bibliotecarios.size()][5];
        for(int i=0;i<bibliotecarios.size();i++) {
            data[i][0] = bibliotecarios.get(i).getCodigo()+"";
            data[i][1] = bibliotecarios.get(i).getBiblioteca().getNombre() + "";
            data[i][2] = bibliotecarios.get(i).getPiso().getPiso()+ "";
            data[i][3] = bibliotecarios.get(i).getTurno().getTurno()+ "";
            //System.out.println(bibliotecarios.get(i).getTurno().getTurno());
            data[i][4] = bibliotecarios.get(i).getNombre()+ "";
        }
       
      GestionBibliotecarios.bibliotecariosTable.setModel(new NonEditableTableModel(data, TABLE_COLUMNS));
      
    }
    
      private void anhadirBibliotecario() {
        // TODO add your handling code here:
        if(objBiblioteca==null) {
            mostrarMensajeError("Campos incompletos", "No ha seleccionado ninguna biblioteca");
            return;
        }
        String nombre, apPat, apMat, email;
        
        String strcod = codigoField.getText();
        if(strcod.length() != 8){ //Depende de la cantidad de digitos del codigoUsuario
            JOptionPane.showMessageDialog(this,null,"El codigo debe ser de 8 digitos",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int cod = Integer.parseInt(strcod);
        
        nombre = nameField.getText();
        apPat = apellidoPat.getText();
        apMat = apellidoMat.getText();
        email = emailField.getText();
        String password = Usuario.generarPassword("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890", 10);
        String direccion = "Av. Universitaria 1801";//tfDireccion.getText();
        
        //Aqui completar las validaciones de las cadenas si son menores a 30 caracteres
        if(nombre.isEmpty()) {
            mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
            return;
        }else if(nombre.length() > 30){
            mostrarMensajeError("Nombre inválido","Debe contener menos de 30 caracteres");
            return;
        }
        if(apPat.isEmpty()) {
            mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
            return;
        }else if(apPat.length() > 30){
                mostrarMensajeError("Apellido Paterno inválido","Debe contener menos de 30 caracteres");
            return;
        }
        
        if(apMat.isEmpty()) {
            mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
            return;
        }else if(apMat.length() > 30){
            mostrarMensajeError("Apellido Materno inválido","Debe contener menos de 30 caracteres");
            return;
        }
        
        
        if(email.isEmpty()) {
            mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
            return;
        }else if(email.length() > 30){
                mostrarMensajeError("Correo electrónico inválido"," Debe contener menos de 30 caracteres");
            return;
        }
        //Bibliotecario(Usuario usuario, Biblioteca biblioteca, Piso piso, TurnoBibliotecario turno)
        //Si va
          System.out.println(1);
          System.out.println(areaBox.getSelectedItem());
        TurnoBibliotecario turno = turnos.get(turnoBox.getSelectedIndex());
         System.out.println(2);
          System.out.println(turno.getId());
        int numero_biblioteca = getBiblioteca(selectedBiblioteca.getText());
        System.out.println(selectedBiblioteca.getText());
        Biblioteca b1 = new Biblioteca(numero_biblioteca,selectedBiblioteca.getText(), true);
         System.out.println(3);
        Usuario usuario = new Usuario(cod, nombre, apPat, apMat, "Av. Universitaria 1801", email, password, false, true, true);
        //TurnoBibliotecario t.TurnoBibliotecario((String)turnoBox.getSelectedItem(),get_turno);
//        Bibliotecario(Biblioteca biblioteca, int codigo, String nombre, String apPaterno, String apMaterno, String direccion, boolean sesionIniciada,
//            boolean activo, String correo, String password, TurnoBibliotecario turno, String piso)
          System.out.println(pisos.get(areaBox.getSelectedIndex()).getId());
        Bibliotecario b = new Bibliotecario(usuario, b1,pisos.get(areaBox.getSelectedIndex()), turno);
        //bibliotecarios.add(b);
          //System.out.println("antes de agregar a bd");
       if(dbBibliotecario.crearBibliotecario(b)){
           JOptionPane.showMessageDialog(null,"Bibliotecario agregado con exito");
       }
       else{
           JOptionPane.showMessageDialog(null,"Error al registrar bibliotecario.Intenlo nuevamente");
           return;
       }
        
       cleanCampos();
        //codigoField.setEnabled(false);
        nameField.setEnabled(false);
        apellidoPat.setEnabled(false);
        apellidoMat.setEnabled(false);
        emailField.setEnabled(false);
        selectedBiblioteca.setEnabled(false);
        areaBox.setEnabled(false);
        turnoBox.setEnabled(false);
        updateButton.setEnabled(false);
        nuevoButton.setEnabled(true);
        buscarButton.setEnabled(true);
        cancelButton.setEnabled(false);
        addButton.setEnabled(false);
        
       // cleanCampos();
        updateDataBibliotecarios();
    }
    private void actualizarBibliotecario() {
        // TODO add your handling code here:
            if(objBiblioteca==null) {
                mostrarMensajeError("Campos incompletos", "No ha seleccionado ninguna biblioteca");
                return;
            }
            String nombre, apPat, apMat, email;

            String strcod = codigoField.getText();
            if(strcod.length() != 8){ //Depende de la cantidad de digitos del codigoUsuario
                JOptionPane.showMessageDialog(null,"El codigo debe ser de 8 digitos");
                return;
            }
            int cod = Integer.parseInt(strcod);

            nombre = nameField.getText();
            apPat = apellidoPat.getText();
            apMat = apellidoMat.getText();
            email = emailField.getText();
            String direccion = tfDireccion.getText();

            //Aqui completar las validaciones de las cadenas si son menores a 30 caracteres
            if(nombre.isEmpty()) {
                mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
                return;
            }else if(nombre.length() > 30){
                mostrarMensajeError("Nombre inválido","Debe contener menos de 30 caracteres");
                return;
            }
            if(apPat.isEmpty()) {
                mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
                return;
            }else if(apPat.length() > 30){
                    mostrarMensajeError("Apellido Paterno inválido","Debe contener menos de 30 caracteres");
                return;
            }

            if(apMat.isEmpty()) {
                mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
                return;
            }else if(apMat.length() > 30){
                mostrarMensajeError("Apellido Materno inválido","Debe contener menos de 30 caracteres");
                return;
            }


            if(email.isEmpty()) {
                mostrarMensajeError("Campos incompletos", "No ha llenado todos los campos");
                return;
            }else if(email.length() > 30){
                    mostrarMensajeError("Correo electrónico inválido"," Debe contener menos de 30 caracteres");
                return;
            }

            //Si va
            TurnoBibliotecario turno = turnos.get(turnoBox.getSelectedIndex());

            int numero_biblioteca = getBiblioteca(selectedBiblioteca.getText());
            //System.out.println(selectedBiblioteca.getText());
            Biblioteca b1 = new Biblioteca(numero_biblioteca,selectedBiblioteca.getText(), true);
            Usuario usuario = new Usuario(cod, nombre, apPat, apMat, "Av. Universitaria 1801", email, null, false, true, true);
            
            Bibliotecario b = new Bibliotecario(usuario, b1, pisos.get(areaBox.getSelectedIndex()), turno);

            if(dbBibliotecario.actualizarBibliotecario(b)){
               JOptionPane.showMessageDialog(null,"Bibliotecario actualizado con éxito");
            }
            else{
               JOptionPane.showMessageDialog(null,"Error al actualizar bibliotecario.Intenlo nuevamente");
               return;
            }
        
        cleanCampos();
        //codigoField.setEnabled(false);
        nameField.setEnabled(false);
        apellidoPat.setEnabled(false);
        apellidoMat.setEnabled(false);
        emailField.setEnabled(false);
        selectedBiblioteca.setEnabled(false);
        areaBox.setEnabled(false);
        turnoBox.setEnabled(false);
        
        nuevoButton.setEnabled(true);
        buscarButton.setEnabled(true);
        cancelButton.setEnabled(false);
        updateButton.setEnabled(false);
        addButton.setEnabled(false);
//        
         //cleanCampos();
         updateDataBibliotecarios();
    
    }

    private static int getBiblioteca(String biblioteca){
        
       int num_biblioteca = 0;
       switch(biblioteca){
           case "Biblioteca Central" :              num_biblioteca = 1;
                                                     break;
            case "Complejo de Innovación Académica": num_biblioteca = 2;
                                                     break;
            case "Biblioteca de EEGGCC":            num_biblioteca = 3;
                                                     break;
            case "Biblioteca de EEGGLL":    num_biblioteca = 4;
                                            break;
       }
       return num_biblioteca;    
    }
     public void cleanCampos(){
        codigoField.setText("");
        nameField.setText("");
        apellidoPat.setText("");
        apellidoMat.setText("");
        emailField.setText("");
        selectedBiblioteca.setText("<Haz click aquí>");
        selectedBiblioteca.setEnabled(false);
        areaBox.setSelectedItem(null);
        areaBox.setEnabled(false);
        turnoBox.setSelectedItem(null);
        turnoBox.setEnabled(false);
    }
        //ini_mod
        private void nuevoBibliotecario() {
        // TODO add your handling code here:
        codigoField.setEnabled(true);
        nameField.setEnabled(true);
        apellidoPat.setEnabled(true);
        apellidoMat.setEnabled(true);
        emailField.setEnabled(true);
        selectedBiblioteca.setEnabled(true);
        areaBox.setEnabled(true);
        turnoBox.setEnabled(true);
        
        nuevoButton.setEnabled(false);
        cancelButton.setEnabled(true);
        addButton.setEnabled(true);
    }
    
    private void cancelarBibliotecario() {
         // TODO add your handling code here:
        codigoField.setEnabled(true);
        nameField.setEnabled(false);
        apellidoPat.setEnabled(false);
        apellidoMat.setEnabled(false);
        emailField.setEnabled(false);
        selectedBiblioteca.setEnabled(false);
        areaBox.setEnabled(false);
        turnoBox.setEnabled(false);
        
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        //searchButton.setEnabled(true);
        nuevoButton.setEnabled(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroBibliotecario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroBibliotecario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField apellidoMat;
    private javax.swing.JTextField apellidoPat;
    private javax.swing.JComboBox<String> areaBox;
    private javax.swing.JButton buscarButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField codigoField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField emailField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JLabel selectedBiblioteca;
    private javax.swing.JTextField tfDireccion;
    private javax.swing.JComboBox<String> turnoBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

//    @Override
//    public void cambiarTexto(int index, String nuevo) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void cambiarTexto(int index, String nuevo) {
        selectedBiblioteca.setText(nuevo);
        objBiblioteca = bibliotecasDisponibles.get(index);
    }
}