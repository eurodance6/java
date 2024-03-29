/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Dialog;

import DataAccess.PisoDB;
import Modelo.Piso;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class AddPiso extends javax.swing.JPanel {

    private ArrayList<Piso> pisos;
    
    /**
     * Creates new form AddPiso
     */
    public AddPiso() {
        initComponents();
        update();
    }
    
    public void update() {
        PisoDB pisoDB = new PisoDB();
        pisos = pisoDB.getPisos();
        
        cbPisos.removeAllItems();
        for(Piso piso: pisos) {
            cbPisos.addItem(piso.getPiso());
        }
        cbPisos.addItem("Otro");
        tfNuevoPiso.setEnabled(false);
    }
    
    public Piso getSelectedPiso() {
        int index = cbPisos.getSelectedIndex();
        if(index<0) return null;
        if(index<pisos.size()) return pisos.get(index);
        return null;
    }
    
    public String getNewPiso() {
        return tfNuevoPiso.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbPisos = new javax.swing.JComboBox<>();
        tfNuevoPiso = new javax.swing.JTextField();

        cbPisos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbPisos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPisosItemStateChanged(evt);
            }
        });

        tfNuevoPiso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfNuevoPiso.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPisos, 0, 313, Short.MAX_VALUE)
                    .addComponent(tfNuevoPiso))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNuevoPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbPisosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPisosItemStateChanged
        // TODO add your handling code here:
        if(pisos==null) return;
        int index = cbPisos.getSelectedIndex();
        if(index<pisos.size()) tfNuevoPiso.setEnabled(false);
        else tfNuevoPiso.setEnabled(true);
    }//GEN-LAST:event_cbPisosItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPisos;
    private javax.swing.JTextField tfNuevoPiso;
    // End of variables declaration//GEN-END:variables
}
