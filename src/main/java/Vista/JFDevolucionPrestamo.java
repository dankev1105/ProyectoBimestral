package Vista;

import Negocio.Prestamo;
import PConexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JFDevolucionPrestamo extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    JFPrestamo prestamoVentana = new JFPrestamo();
    public JFDevolucionPrestamo() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        
        this.jTFnombreLibroDevolver.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        filtrarTablaPrestamoPorNombreLibro(text);
        if (text.trim().isEmpty()) {
        mostrarTablaPrestamo();
        }}});

        this.jTFnombreEstudianteDevolver.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        filtrarTablaPrestamoPorNombreEstudiante(text);
        if (text.trim().isEmpty()) {
        mostrarTablaPrestamo();
        }}});
        mostrarTablaPrestamo();
    }
    public void mostrarTablaPrestamo(){
        String sql = "SELECT p.IdPrestamo, l.IdLibro, l.NombreLibro, e.NombreEstudiante, p.FechaPrestamo, p.FechaDevolucion FROM Prestamo p INNER JOIN Libro l ON p.IdLibro = l.IdLibro INNER JOIN Estudiante e ON p.IdEstudiante = e.IdEstudiante";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Prestamo");
        model.addColumn("ID Libro");
        model.addColumn("Nombre Libro");
        model.addColumn("Nombre Estudiante");
        model.addColumn("Fecha Prestamo");
        model.addColumn("Fecha Devolucion");
        jTprestamo.setModel(model);
        String [] datos = new String [6];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=String.valueOf(rs.getInt(1));
                datos[1]=String.valueOf(rs.getInt(2));
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                model.addRow(datos);
            }
            jTprestamo.removeColumn(jTprestamo.getColumnModel().getColumn(1));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }
        public void filtrarTablaPrestamoPorNombreEstudiante(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query,3)); 
        }
    }
    public void filtrarTablaPrestamoPorNombreLibro(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query,2)); 
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTFnombreEstudianteDevolver = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTFnombreLibroDevolver = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTprestamo = new javax.swing.JTable();
        jBcancelar = new javax.swing.JButton();
        jBdevolver = new javax.swing.JButton();
        jBnuevoDevolucion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Devolver Préstamo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        jLabel21.setText("Nombre del Libro:");

        jLabel22.setText("Nombre del Estudiante:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFnombreLibroDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFnombreEstudianteDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTFnombreLibroDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFnombreEstudianteDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTprestamo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTprestamo);

        jBcancelar.setText("Cancelar");
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jBdevolver.setText("Devolver");
        jBdevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdevolverActionPerformed(evt);
            }
        });

        jBnuevoDevolucion.setText("Nuevo");
        jBnuevoDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnuevoDevolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jBdevolver)
                .addGap(60, 60, 60)
                .addComponent(jBcancelar)
                .addGap(69, 69, 69)
                .addComponent(jBnuevoDevolucion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBdevolver)
                    .addComponent(jBcancelar)
                    .addComponent(jBnuevoDevolucion))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        this.setVisible(false);
        prestamoVentana.mostrarTablaPrestamo();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jBdevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdevolverActionPerformed
        try {
            int selectedRow = jTprestamo.getSelectedRow();
            if (selectedRow != -1 && jTprestamo.getColumnCount() > 1) {
                int idPrestamo = Integer.parseInt(jTprestamo.getModel().getValueAt(selectedRow, 0).toString());
                int idLibro = Integer.parseInt(jTprestamo.getModel().getValueAt(selectedRow, 1).toString()); // Asegúrate de que este es el índice correcto para el idLibro en tu tabla
                Prestamo devolucion = new Prestamo();
                devolucion.eliminarRegistro(idPrestamo);
                devolucion.aumentarUnidadesLibro(idLibro);
                mostrarTablaPrestamo();
                JOptionPane.showMessageDialog(null, "Devolución exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un préstamo para devolver.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }//GEN-LAST:event_jBdevolverActionPerformed

    private void jBnuevoDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnuevoDevolucionActionPerformed
        quitarFiltrado(jTprestamo);
        this.jTFnombreLibroDevolver.setText("");
        this.jTFnombreEstudianteDevolver.setText("");
    }//GEN-LAST:event_jBnuevoDevolucionActionPerformed
    
    public void quitarFiltrado(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }
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
            java.util.logging.Logger.getLogger(JFDevolucionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFDevolucionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFDevolucionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFDevolucionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFDevolucionPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBdevolver;
    private javax.swing.JButton jBnuevoDevolucion;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTFnombreEstudianteDevolver;
    private javax.swing.JTextField jTFnombreLibroDevolver;
    private javax.swing.JTable jTprestamo;
    // End of variables declaration//GEN-END:variables
}
