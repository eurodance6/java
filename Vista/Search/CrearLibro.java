/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Search;

import DataAccess.AutorDB;
import DataAccess.LibroDB;
import Modelo.Libro;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author FrancoP
 */
public class CrearLibro extends javax.swing.JFrame {

    private class UpdateImage implements DocumentListener {

        private Thread imageLoadingThread;
        
        @Override
        public void insertUpdate(DocumentEvent e) {
            tryLoading();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            tryLoading();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            tryLoading();
        }
        
        private void tryLoading() {
            if(imageLoadingThread!=null) imageLoadingThread.stop();
            
            String url = tfPortada.getText();
            imageLoadingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageIcon icon;
                    try {
                        icon = new ImageIcon(new URL(url));
                    } catch (MalformedURLException ex) {
                        icon = null;
                    }
                    if(icon!=null) {
                        imgLibro.setIcon(icon);
                        imgLibro.setText("");
                    } else imgLibro.setText("<html><body>sin<br>imagen</body></html>");
                }
            });
            imageLoadingThread.start();
        }
        
    }
    
    /**
     * Creates new form InformacionLibro
     */
    public CrearLibro() {
        initComponents();
        tfPortada.getDocument().addDocumentListener(new UpdateImage());
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
        jTable1 = new javax.swing.JTable();
        lblAutor = new javax.swing.JLabel();
        lblIdioma = new javax.swing.JLabel();
        lblAnhoPublicacion = new javax.swing.JLabel();
        lblIdentificador = new javax.swing.JLabel();
        crearButton = new javax.swing.JButton();
        imgLibro = new javax.swing.JLabel();
        lblEditorial = new javax.swing.JLabel();
        lblEdicion = new javax.swing.JLabel();
        lblIdentificador1 = new javax.swing.JLabel();
        tfAutores = new javax.swing.JTextField();
        tfEditorial = new javax.swing.JTextField();
        tfEdicion = new javax.swing.JTextField();
        tfIdioma = new javax.swing.JTextField();
        tfAnhoPub = new javax.swing.JTextField();
        tfISBN = new javax.swing.JTextField();
        tfNumPag = new javax.swing.JTextField();
        tfTitulo = new javax.swing.JTextField();
        lblIdentificador2 = new javax.swing.JLabel();
        tfPortada = new javax.swing.JTextField();
        lblAutor1 = new javax.swing.JLabel();
        tfTemas = new javax.swing.JTextField();
        lblIdentificador3 = new javax.swing.JLabel();
        tfPortadaGrande = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblAutor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAutor.setText("Autor(es):");

        lblIdioma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdioma.setText("Idioma:");

        lblAnhoPublicacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAnhoPublicacion.setText("Año de Publicacion:");

        lblIdentificador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdentificador.setText("ISBN:");

        crearButton.setText("Crear");
        crearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearButtonActionPerformed(evt);
            }
        });

        imgLibro.setText("imagen");
        imgLibro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblEditorial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEditorial.setText("Editorial:");

        lblEdicion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblEdicion.setText("Edicion:");

        lblIdentificador1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdentificador1.setText("Número de páginas:");

        tfAutores.setText("Autor del libro");
        tfAutores.setToolTipText("Separar autores con ';'");

        tfEditorial.setText("Editorial del libro");

        tfEdicion.setText("Edicion del libro");

        tfIdioma.setText("Idioma del libro");

        tfAnhoPub.setText("Año de publicacion");

        tfISBN.setText("ISBN");

        tfNumPag.setText("Numero de paginas");

        tfTitulo.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        tfTitulo.setText("Titulo del libro");

        lblIdentificador2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdentificador2.setText("URL Portada:");

        tfPortada.setText("http://...");

        lblAutor1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAutor1.setText("Temas:");

        tfTemas.setText("Temas");
        tfTemas.setToolTipText("Separar temas con ';'");

        lblIdentificador3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdentificador3.setText("URL Portada grande:");

        tfPortadaGrande.setText("http://...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(315, 315, 315)
                                .addComponent(tfNumPag, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblIdentificador2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAutor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfAutores, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEditorial)
                                            .addComponent(lblEdicion)
                                            .addComponent(lblIdioma)
                                            .addComponent(lblAnhoPublicacion))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfEditorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfEdicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfIdioma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfAnhoPub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblIdentificador3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfPortadaGrande, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIdentificador1)
                                            .addComponent(lblIdentificador))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblAutor1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfTemas, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(571, 571, 571)
                        .addComponent(crearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(tfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfAutores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblEditorial)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblEdicion)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblIdioma))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnhoPublicacion)
                    .addComponent(tfAnhoPub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblIdentificador)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNumPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblIdentificador1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPortada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdentificador2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPortadaGrande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdentificador3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAutor1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(crearButton)
                .addGap(32, 32, 32))
        );

        lblAutor.getAccessibleContext().setAccessibleName("txtAutor");
        lblIdioma.getAccessibleContext().setAccessibleName("txtIdioma");
        lblAnhoPublicacion.getAccessibleContext().setAccessibleName("txtAnho");
        lblIdentificador.getAccessibleContext().setAccessibleName("txtIdentificador");
        crearButton.getAccessibleContext().setAccessibleName("btnReservar");
        imgLibro.getAccessibleContext().setAccessibleName("imgPortada");
        lblEditorial.getAccessibleContext().setAccessibleName("txtEditorial");
        lblEdicion.getAccessibleContext().setAccessibleName("txtEdicion");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearButtonActionPerformed
        // TODO add your handling code here:
        try{ 
            String titulo = tfTitulo.getText();
            String autores = tfAutores.getText();
            int anhoPub = Integer.parseInt(tfAnhoPub.getText());
            int numPag = Integer.parseInt(tfNumPag.getText());
            String isbn = tfISBN.getText();
            String idioma = tfIdioma.getText();
            String editorial = tfEditorial.getText();
            int edicion = Integer.parseInt(tfEdicion.getText());
            String portada = tfPortada.getText();
            String portadaGrande = tfPortadaGrande.getText();
            if(idioma.length()!=2) {
                JOptionPane.showMessageDialog(this, "Formato incorrecto de idioma!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(isbn.length()>15) {
                JOptionPane.showMessageDialog(this, "Formato incorrecto de ISBN!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Libro libro = new Libro(titulo, anhoPub, numPag, isbn, idioma, editorial, edicion, portada, portadaGrande);
            AutorDB autordb = new AutorDB();
            libro.setAutores(autordb.getAutoresFromSeparados(autores));
            
            LibroDB librodb = new LibroDB();
            librodb.crearLibro(libro);
        } catch(NumberFormatException e) {
            
        }
    }//GEN-LAST:event_crearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crearButton;
    private javax.swing.JLabel imgLibro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAnhoPublicacion;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblAutor1;
    private javax.swing.JLabel lblEdicion;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblIdentificador;
    private javax.swing.JLabel lblIdentificador1;
    private javax.swing.JLabel lblIdentificador2;
    private javax.swing.JLabel lblIdentificador3;
    private javax.swing.JLabel lblIdioma;
    private javax.swing.JTextField tfAnhoPub;
    private javax.swing.JTextField tfAutores;
    private javax.swing.JTextField tfEdicion;
    private javax.swing.JTextField tfEditorial;
    private javax.swing.JTextField tfISBN;
    private javax.swing.JTextField tfIdioma;
    private javax.swing.JTextField tfNumPag;
    private javax.swing.JTextField tfPortada;
    private javax.swing.JTextField tfPortadaGrande;
    private javax.swing.JTextField tfTemas;
    private javax.swing.JTextField tfTitulo;
    // End of variables declaration//GEN-END:variables
}

