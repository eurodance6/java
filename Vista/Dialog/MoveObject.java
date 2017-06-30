/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Dialog;

import DataAccess.BibliotecaDB;
import Modelo.Biblioteca;
import Modelo.Piso;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class MoveObject extends javax.swing.JPanel {

    private ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
    private ArrayList<Piso> pisos = null;
    
    private Biblioteca selecBiblio = null;
    private Piso selecPiso = null;
    
    /**
     * Creates new form MoveObject
     * @param showMantenimiento
     */
    public MoveObject(boolean showMantenimiento) {
        initComponents();
        if(!showMantenimiento)
            cbMantenimiento.setVisible(false);
        updateItems();
    }
    
    private void updateItems() {
        cbBiblioteca.removeAllItems();
        cbArea.removeAllItems();
        
        cbBiblioteca.addItem(" -- Seleccione biblioteca -- ");
        cbArea.addItem(" -- Seleccione area -- ");
        
        BibliotecaDB bibliodb = new BibliotecaDB();
        bibliotecas = bibliodb.getBibliotecas();
        
        for(Biblioteca biblioteca: bibliotecas)
            cbBiblioteca.addItem(biblioteca.getNombre());
    }
    
    public boolean inputSuccessful() {
        return selecBiblio!=null && selecPiso!=null;
    }

    public Biblioteca getBiblioteca() {
        return selecBiblio;
    }
    
    public Piso getPiso() {
        return selecPiso;
    }
    
    public boolean enMantenimiento() {
        return cbMantenimiento.isSelected();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbBiblioteca = new javax.swing.JComboBox<>();
        cbArea = new javax.swing.JComboBox<>();
        cbMantenimiento = new javax.swing.JCheckBox();

        cbBiblioteca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbBiblioteca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBibliotecaItemStateChanged(evt);
            }
        });

        cbArea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbArea.setEnabled(false);
        cbArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAreaItemStateChanged(evt);
            }
        });

        cbMantenimiento.setText("Poner en mantenimiento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBiblioteca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbMantenimiento)
                        .addGap(0, 207, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addComponent(cbMantenimiento)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbBibliotecaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBibliotecaItemStateChanged
        // TODO add your handling code here:
        int index = cbBiblioteca.getSelectedIndex() - 1;
        cbArea.removeAllItems();
        cbArea.addItem(" -- Seleccione area -- ");
        
        if(index<0) {
            cbArea.setEnabled(false);
            return;
        }
        
        cbArea.setEnabled(true);
        selecBiblio = bibliotecas.get(index);
        pisos = selecBiblio.getPisos();
        for(Piso piso: pisos)
            cbArea.addItem(piso.getPiso());
    }//GEN-LAST:event_cbBibliotecaItemStateChanged

    private void cbAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAreaItemStateChanged
        // TODO add your handling code here:
        int index = cbArea.getSelectedIndex() - 1;
        if(index<0) {
            selecPiso = null;
            return;
        }
        selecPiso = pisos.get(index);
    }//GEN-LAST:event_cbAreaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbArea;
    private javax.swing.JComboBox<String> cbBiblioteca;
    private javax.swing.JCheckBox cbMantenimiento;
    // End of variables declaration//GEN-END:variables
}
