/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Search;

import DataAccess.BibliotecaDB;
import DataAccess.LibroDB;
import DataAccess.PisoDB;
import Modelo.Biblioteca;
import Modelo.Libro;
import Modelo.Piso;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 *
 * @author alulab14
 */
public class ResultadosBusqueda extends javax.swing.JFrame {

    private static final int TAMANHO_PAGINA = 20;
    private static final int PAGE_SET_SIZE = 10;
    private static final Color BACKGROUND_COLOR = new Color(0x999f99);
    private static final Color FOREGROUND_COLOR = new Color(0x0);
    
    private LibroDB librodb = new LibroDB();
    
    private int currentPage;
    private String lastQuery;
    private int totResultados;
    private int pageOffset, totPages;
    
    private ArrayList<Libro> resultados;
    private ArrayList<ResultadoLibro> panelesLibros;
    
    /**
     * Creates new form ResultadosBusqueda
     */
    public ResultadosBusqueda() {
        
        initComponents();
        scrllPnlResultados.getVerticalScrollBar().setUnitIncrement(50);
        queryBooks("");
        getContentPane().setBackground(BACKGROUND_COLOR);
        pnlControlResultados.setBackground(BACKGROUND_COLOR);
        pnlControles.setBackground(BACKGROUND_COLOR);
        pnlResultados.setBackground(BACKGROUND_COLOR);
        scrllPnlResultados.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOR, 2));
    }

    public void queryBooks(String q) {
        lastQuery = q;
        totResultados = librodb.getLibrosCount(q);
        
        setPage(0);
    }
    
    public void setPage(int page) {
        if(page*TAMANHO_PAGINA>totResultados)
            page = (totResultados+TAMANHO_PAGINA-1)/TAMANHO_PAGINA;
        
        currentPage = page;
        
        resultados = librodb.getLibros(lastQuery, page*TAMANHO_PAGINA, TAMANHO_PAGINA);
        updateData();
        
        if(totResultados==0) lblCantResultados.setText("No se encontraron resultados para '" + lastQuery + "'");
        else lblCantResultados.setText("Resultado de Busqueda " + (1+page*TAMANHO_PAGINA) + " a " + 
                Math.min(((page+1)*TAMANHO_PAGINA), totResultados)+ " de " + totResultados);
        
        if(totPages<=PAGE_SET_SIZE) {
            pageOffset = 0;
        } else if(currentPage>=PAGE_SET_SIZE/2) {
            if(currentPage<=(totPages-PAGE_SET_SIZE/2))
                pageOffset = currentPage - PAGE_SET_SIZE/2;
            else pageOffset = totPages-PAGE_SET_SIZE - 1;
        } else pageOffset = 0;
        addNumeration();
    }
    
    public void updateData() {
        for(Component c : pnlResultados.getComponents()) {
            if(c instanceof ResultadoLibro) {
                ResultadoLibro rl = (ResultadoLibro)c;
                rl.kill();
            }
        }
        pnlResultados.removeAll();
        pnlResultados.setLayout(new BoxLayout(pnlResultados, BoxLayout.Y_AXIS));
        panelesLibros = new ArrayList<>();
        resultados.stream().map((libro) -> new ResultadoLibro(libro)).forEach((rl) -> {
            panelesLibros.add(rl);
            pnlResultados.add(rl);
        });
        Dimension d = new Dimension(705, 127*panelesLibros.size() + 20);
        pnlResultados.setPreferredSize(d);
        pnlResultados.setMinimumSize(d);
        pnlResultados.setMaximumSize(d);
        scrllPnlResultados.updateUI();
        
        btnAnterior.setEnabled(currentPage>0);
        btnSiguiente.setEnabled((currentPage+1)*TAMANHO_PAGINA<totResultados);
        
        pageOffset = 0;
        totPages = (totResultados+TAMANHO_PAGINA-1)/TAMANHO_PAGINA;
        addNumeration();
    }
    
    private JLabel generateLabel(final int number, boolean selected) {
        JLabel lbl;
        if(!selected) {
            lbl = new JLabel("<html><body><span style='font-size:120%'>" + number + "</span></body></html>");
            lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            lbl = new JLabel("<html><body><span style='font-size:120%'><u><b>" + number + "</b></u></span></body></html>");
        }
        lbl.setBackground(BACKGROUND_COLOR);
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                if(!selected) lbl.setText("<html><body><span style='font-size:120%'><u><b>" + number + "</b></u></span></body></html>");
                lbl.updateUI();
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                if(!selected) lbl.setText("<html><body><span style='font-size:120%'>" + number + "</span></body></html>");
                lbl.updateUI();
            }
            
            @Override
            public void mouseClicked(MouseEvent evt) {
                setPage(number-1);
            }
        });
        return lbl;
    }
    
    void addNumeration() {
        pnlNumeration.removeAll();
        pnlNumeration.setBackground(BACKGROUND_COLOR);
        pnlNumeration.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        for(int i=0;i<Math.min(PAGE_SET_SIZE, totPages-pageOffset);i++) {
            pnlNumeration.add(generateLabel(pageOffset + i + 1, (pageOffset + i)==currentPage));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bttnBuscar = new javax.swing.JButton();
        txtBoxBuscar = new javax.swing.JTextField();
        lblCantResultados = new javax.swing.JLabel();
        pnlControlResultados = new javax.swing.JPanel();
        scrllPnlResultados = new javax.swing.JScrollPane();
        pnlResultados = new javax.swing.JPanel();
        pnlControles = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        pnlNumeration = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscador Universidad UUU");
        setMinimumSize(new java.awt.Dimension(872, 542));

        bttnBuscar.setText("Buscar");
        bttnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnBuscarActionPerformed(evt);
            }
        });

        txtBoxBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBoxBuscarActionPerformed(evt);
            }
        });

        lblCantResultados.setText("Resultado de Busqueda # a # de ####");

        pnlControlResultados.setLayout(new javax.swing.BoxLayout(pnlControlResultados, javax.swing.BoxLayout.Y_AXIS));

        scrllPnlResultados.setPreferredSize(new java.awt.Dimension(500, 300));
        scrllPnlResultados.setRequestFocusEnabled(false);

        pnlResultados.setPreferredSize(new java.awt.Dimension(701, 1));

        javax.swing.GroupLayout pnlResultadosLayout = new javax.swing.GroupLayout(pnlResultados);
        pnlResultados.setLayout(pnlResultadosLayout);
        pnlResultadosLayout.setHorizontalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        pnlResultadosLayout.setVerticalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 444, Short.MAX_VALUE)
        );

        scrllPnlResultados.setViewportView(pnlResultados);

        pnlControlResultados.add(scrllPnlResultados);

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNumerationLayout = new javax.swing.GroupLayout(pnlNumeration);
        pnlNumeration.setLayout(pnlNumerationLayout);
        pnlNumerationLayout.setHorizontalGroup(
            pnlNumerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        pnlNumerationLayout.setVerticalGroup(
            pnlNumerationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlControlesLayout = new javax.swing.GroupLayout(pnlControles);
        pnlControles.setLayout(pnlControlesLayout);
        pnlControlesLayout.setHorizontalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNumeration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addContainerGap())
        );
        pnlControlesLayout.setVerticalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnterior)
                        .addComponent(btnSiguiente))
                    .addComponent(pnlNumeration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlControlResultados.add(pnlControles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblCantResultados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttnBuscar)
                .addGap(35, 485, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlControlResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttnBuscar))
                .addGap(35, 35, 35)
                .addComponent(lblCantResultados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlControlResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBuscarActionPerformed
        // TODO add your handling code here:
        queryBooks(txtBoxBuscar.getText());
    }//GEN-LAST:event_bttnBuscarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if(currentPage>0) setPage(currentPage - 1);
        if(currentPage==0) btnAnterior.setEnabled(false);
        if((currentPage+1)*TAMANHO_PAGINA<totResultados) btnSiguiente.setEnabled(true);
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        setPage(currentPage + 1);
        if(currentPage*TAMANHO_PAGINA>=totResultados) btnSiguiente.setEnabled(false);
        if(currentPage>0) btnAnterior.setEnabled(true);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtBoxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBoxBuscarActionPerformed
        // TODO add your handling code here:
        bttnBuscarActionPerformed(null);
    }//GEN-LAST:event_txtBoxBuscarActionPerformed
    
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResultadosBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadosBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadosBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadosBusqueda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultadosBusqueda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton bttnBuscar;
    private javax.swing.JLabel lblCantResultados;
    private javax.swing.JPanel pnlControlResultados;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlNumeration;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JScrollPane scrllPnlResultados;
    private javax.swing.JTextField txtBoxBuscar;
    // End of variables declaration//GEN-END:variables
}
