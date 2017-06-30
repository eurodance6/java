/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Gestion;

import DataAccess.BibliotecaDB;
import DataAccess.BibliotecarioDB;
import DataAccess.ConexionDB;
import DataAccess.LibroDB;
import DataAccess.Session;
import Modelo.Biblioteca;
import Modelo.Piso;
import Modelo.PisoBiblioteca;
import Vista.Dialog.AddPiso;
import Vista.Dialog.MoveObject;
import Vista.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author franc
 */
public class GestionBiblioteca extends javax.swing.JInternalFrame {
    
    private ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
    private Map<Biblioteca, DefaultListModel> pisos = new HashMap<>();
    private Map<Biblioteca, Map<Piso, PisoBiblioteca> > pisosBiblios = new HashMap<>();
    
    private Biblioteca selecBiblio = null;
    private Piso selecPiso = null;
    
    public GestionBiblioteca() {
        initComponents();
        
        updateBibliotecas();
    }
    
    private void disableButtons() {
        bAddPiso.setEnabled(false);
        bElimBiblio.setEnabled(false);
        bElimPiso.setEnabled(false);
        bMoverBiblio.setEnabled(false);
        bMoverLibros.setEnabled(false);
    }
    
    public void updateBibliotecas() {
        disableButtons();
        bibliotecas = new ArrayList<>();
        pisos = new HashMap<>();
        pisosBiblios = new HashMap<>();
        
        final BibliotecaDB bibliodb = new BibliotecaDB();
        bibliotecas = bibliodb.getBibliotecas();
        
        DefaultListModel<String> lm = new DefaultListModel<>();
        for(Biblioteca biblioteca : bibliotecas) {
            lm.addElement(biblioteca.getNombre());
            DefaultListModel<String> plm = new DefaultListModel<>();
            pisosBiblios.put(biblioteca, new HashMap<>());
            for(Piso piso : biblioteca.getPisos()) {
                plm.addElement(piso.getPiso());
                pisosBiblios.get(biblioteca).put(piso, bibliodb.pisoBiblioteca(biblioteca, piso));
            }
            pisos.put(biblioteca, plm);
        }
        listaBiblios.setModel(lm);
        listaPisos.setModel(new DefaultListModel<>());
       
        listaBiblios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                labelCantBiblio.setText("---");
                labelCantLibros.setText("---");
                labelEstadoArea.setText("Sin estado");
                
                int biblioIndex = listaBiblios.getSelectedIndex();
                if(biblioIndex==-1) {
                    listaPisos.setModel(new DefaultListModel<>());
                    listaPisos.setEnabled(false);
                    labelEstadoBiblio.setText("Sin estado");
                    
                    disableButtons();
                    return;
                }
                selecBiblio = bibliotecas.get(biblioIndex);
                
                disableButtons();
                bAddPiso.setEnabled(true);
                bElimBiblio.setEnabled(true);
                bElimBiblio.setText(selecBiblio.isActiva()?"Eliminar biblioteca":
                        "Reestablecer biblioteca");
                
                if(selecBiblio.isActiva()) {
                    labelEstadoBiblio.setText("Activa");
                } else labelEstadoBiblio.setText("Inactiva");
                
                listaPisos.setModel(pisos.get(selecBiblio));
                listaPisos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int pisoIndex = listaPisos.getSelectedIndex();
                        if(pisoIndex==-1) {
                            labelCantBiblio.setText("---");
                            labelCantLibros.setText("---");
                            labelEstadoArea.setText("Sin estado");
                            labelEstadoBiblio.setText("Sin estado");
                            
                            bElimPiso.setEnabled(false);
                            bMoverBiblio.setEnabled(false);
                            bMoverLibros.setEnabled(false);
                            return;
                        }
                        selecPiso = selecBiblio.getPiso(pisoIndex);
                        
                        bElimPiso.setEnabled(true);
                        bMoverBiblio.setEnabled(true);
                        bMoverLibros.setEnabled(true);
                        
                        PisoBiblioteca pisobiblio = pisosBiblios.get(selecBiblio).get(selecPiso);
                        labelCantBiblio.setText(""+pisobiblio.getNumBibliotecarios());
                        labelCantLibros.setText(""+pisobiblio.getNumLibros());
                        labelEstadoArea.setText(pisobiblio.isActivo()?"Activa":"Inactivo");
                        bElimPiso.setText(pisobiblio.isActivo()?"Eliminar area":"Reestablecer area");
                        //System.out.println(estaBiblio.getID() + ", " + estePiso.getId());
                    }
                });
                listaPisos.setEnabled(true);
            }
        });
        
        updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaBiblios = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaPisos = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelCantLibros = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelCantBiblio = new javax.swing.JLabel();
        bAddBiblio = new javax.swing.JButton();
        bAddPiso = new javax.swing.JButton();
        bElimBiblio = new javax.swing.JButton();
        bElimPiso = new javax.swing.JButton();
        bMoverLibros = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        labelEstadoArea = new javax.swing.JLabel();
        bMoverBiblio = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelEstadoBiblio = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestión de bibliotecas");

        listaBiblios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaBiblios);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Bibliotecas:");

        listaPisos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaPisos.setEnabled(false);
        jScrollPane2.setViewportView(listaPisos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Áreas:");

        jLabel3.setText("Cantidad de libros:");

        labelCantLibros.setText("----");

        jLabel5.setText("<html><body>Cantidad de<br>bibliotecarios:</body></html>");

        labelCantBiblio.setText("----");

        bAddBiblio.setText("Añadir biblioteca");
        bAddBiblio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddBiblioActionPerformed(evt);
            }
        });

        bAddPiso.setText("Añadir Piso");
        bAddPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddPisoActionPerformed(evt);
            }
        });

        bElimBiblio.setText("Eliminar biblioteca");
        bElimBiblio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bElimBiblioActionPerformed(evt);
            }
        });

        bElimPiso.setText("Eliminar piso");
        bElimPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bElimPisoActionPerformed(evt);
            }
        });

        bMoverLibros.setText("Mover libros");
        bMoverLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMoverLibrosActionPerformed(evt);
            }
        });

        jLabel7.setText("Estado area:");

        labelEstadoArea.setText("Sin estado");

        bMoverBiblio.setText("Mover bibliotecarios");
        bMoverBiblio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMoverBiblioActionPerformed(evt);
            }
        });

        jLabel8.setText("Estado biblioteca:");

        labelEstadoBiblio.setText("Sin estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bAddBiblio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bAddPiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bElimBiblio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bElimPiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bMoverLibros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelEstadoArea, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(labelCantBiblio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstadoBiblio, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(labelCantLibros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(bMoverBiblio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jLabel2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelCantLibros))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCantBiblio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(labelEstadoArea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(labelEstadoBiblio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bMoverBiblio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bMoverLibros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bElimPiso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bElimBiblio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAddPiso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAddBiblio)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bMoverBiblioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMoverBiblioActionPerformed
        // TODO add your handling code here:
        MoveObject panel = new MoveObject(false);
        int opt = Util.buildPanelPane(this, "Mover bibliotecarios de " + selecBiblio.getNombre(), panel,
                new String[]{"Mover", "Cancelar"}, 1);
        if(opt!=0 || !panel.inputSuccessful()) return;
        
        String pass = Util.buildInputPassword(this, selecBiblio.getNombre() + " - Nueva area");
        if(Session.getSession().revalidatePassword(pass)) {
            BibliotecarioDB bibliodb = new BibliotecarioDB();
            bibliodb.moverBibliotecarios(selecBiblio, selecPiso, panel.getBiblioteca(), panel.getPiso());
            updateBibliotecas();
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_bMoverBiblioActionPerformed

    private void bAddBiblioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddBiblioActionPerformed
        // TODO add your handling code here:
        String nombreBiblioteca = JOptionPane.showInputDialog(this,
                "Ingrese el nombre de la nueva biblioteca", "Nueva Biblioteca", JOptionPane.PLAIN_MESSAGE);
        if(nombreBiblioteca==null) return;
        
        String pass = Util.buildInputPassword(this, nombreBiblioteca + " - Nueva biblioteca");
        if(Session.getSession().revalidatePassword(pass)) {
            BibliotecaDB bibliodb = new BibliotecaDB();
            bibliodb.addBiblioteca(nombreBiblioteca);
            updateBibliotecas();
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAddBiblioActionPerformed

    private void bAddPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddPisoActionPerformed
        // TODO add your handling code here:
        AddPiso panel = new AddPiso();
        int opt = Util.buildPanelPane(this, selecBiblio.getNombre() + " - Nueva area",
                panel, new String[]{"Añadir", "Cancelar"}, 1);
        if(opt==0) {
            Piso piso = panel.getSelectedPiso();
            if(piso==null) {
                String nombrePiso = panel.getNewPiso();
                if(nombrePiso==null || nombrePiso.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de un nuevo piso",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String pass = Util.buildInputPassword(this, nombrePiso + " - Crear piso");
                if(Session.getSession().revalidatePassword(pass)) {
                    BibliotecaDB bibliodb = new BibliotecaDB();
                    piso = bibliodb.addPiso(nombrePiso);
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            //Link biblioteca-piso
            BibliotecaDB bibliodb = new BibliotecaDB();
            String pass = Util.buildInputPassword(this, selecBiblio.getNombre() + " - Nueva area");
            if(Session.getSession().revalidatePassword(pass)) {
                bibliodb.addPisoTo(piso, selecBiblio);
                updateBibliotecas();
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_bAddPisoActionPerformed

    private void bElimBiblioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bElimBiblioActionPerformed
        // TODO add your handling code here:
        if(selecBiblio.isActiva()) {
            for(Map.Entry<Piso, PisoBiblioteca> e: pisosBiblios.get(selecBiblio).entrySet()) {
                if(e.getValue().getNumBibliotecarios()!=0 || e.getValue().getNumLibros()!=0) {
                    JOptionPane.showMessageDialog(this, "Esta biblioteca no se encuentra vacia!", 
                            "No se puede hacer", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        
        String pass = Util.buildInputPassword(this, selecBiblio.getNombre() + " - Nueva area");
        if(Session.getSession().revalidatePassword(pass)) {
            BibliotecaDB bibliodb = new BibliotecaDB();
            for(Piso piso: selecBiblio.getPisos()) {
                if(selecBiblio.isActiva())
                    bibliodb.disablePiso(piso, selecBiblio);
                else bibliodb.enablePiso(piso, selecBiblio);
            }
            if(selecBiblio.isActiva())
                bibliodb.disableBiblioteca(selecBiblio);
            else bibliodb.enableBiblioteca(selecBiblio);
            updateBibliotecas();
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_bElimBiblioActionPerformed

    private void bElimPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bElimPisoActionPerformed
        // TODO add your handling code here:
        PisoBiblioteca pisobiblio = pisosBiblios.get(selecBiblio).get(selecPiso);
        if(pisobiblio.getNumBibliotecarios()!=0 || pisobiblio.getNumLibros()!=0) {
            JOptionPane.showMessageDialog(this, "Esta area no se encuentra vacia!", 
                    "No se puede hacer", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String pass = Util.buildInputPassword(this, selecBiblio.getNombre() + " - Nueva area");
        if(Session.getSession().revalidatePassword(pass)) {
            BibliotecaDB bibliodb = new BibliotecaDB();
            if(pisobiblio.isActivo())
                bibliodb.disablePiso(selecPiso, selecBiblio);
            else bibliodb.enablePiso(selecPiso, selecBiblio);
            updateBibliotecas();
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_bElimPisoActionPerformed

    private void bMoverLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMoverLibrosActionPerformed
        // TODO add your handling code here:
        MoveObject panel = new MoveObject(true);
        int opt = Util.buildPanelPane(this, "Mover libros de " + selecBiblio.getNombre(), panel,
                new String[]{"Mover", "Cancelar"}, 1);
        if(opt!=0 || !panel.inputSuccessful()) return;
        
        String pass = Util.buildInputPassword(this, selecBiblio.getNombre() + " - Nueva area");
        if(Session.getSession().revalidatePassword(pass)) {
            LibroDB librodb = new LibroDB();
            librodb.moverLibros(selecBiblio, selecPiso, panel.getBiblioteca(), panel.getPiso(),
                    panel.enMantenimiento());
            updateBibliotecas();
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_bMoverLibrosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddBiblio;
    private javax.swing.JButton bAddPiso;
    private javax.swing.JButton bElimBiblio;
    private javax.swing.JButton bElimPiso;
    private javax.swing.JButton bMoverBiblio;
    private javax.swing.JButton bMoverLibros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCantBiblio;
    private javax.swing.JLabel labelCantLibros;
    private javax.swing.JLabel labelEstadoArea;
    private javax.swing.JLabel labelEstadoBiblio;
    private javax.swing.JList<String> listaBiblios;
    private javax.swing.JList<String> listaPisos;
    // End of variables declaration//GEN-END:variables

}
