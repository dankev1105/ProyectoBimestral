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

public class JFEliminacionPrestamo extends javax.swing.JFrame {
Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    public JFEliminacionPrestamo() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        this.jTFnombreLibroEliminar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        filtrarTablaPrestamoPorLibro(text);
        if (text.trim().isEmpty()) {
            mostrarTablaPrestamo();
        }}});
        mostrarTablaPrestamo();
    }
    
    public void mostrarTablaPrestamo(){
        String sql = "SELECT p.IdPrestamo, l.NombreLibro, e.NombreEstudiante, p.FechaPrestamo, p.FechaDevolucion FROM Prestamo p INNER JOIN Libro l ON p.IdLibro = l.IdLibro INNER JOIN Estudiante e ON p.IdEstudiante = e.IdEstudiante";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Prestamo");
        model.addColumn("Nombre Libro");
        model.addColumn("Nombre Estudiante");
        model.addColumn("Fecha Prestamo");
        model.addColumn("Fecha Devolucion");
        jTprestamo.setModel(model);
        String [] datos = new String [5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=String.valueOf(rs.getInt(1));
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                model.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }
    public void filtrarTablaPrestamoPorLibro(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query,1)); 
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel19 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jTFnombreLibroEliminar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTFcodigoLibroEliminar = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTprestamo = new javax.swing.JTable();
        jBcancelar = new javax.swing.JButton();
        jBeliminar = new javax.swing.JButton();
        jBnuevoEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminar Préstamo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        jLabel23.setText("Nombre del Libro:");

        jLabel24.setText("Código del Libro:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFnombreLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFcodigoLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTFnombreLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFcodigoLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
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

        jBeliminar.setText("Eliminar");
        jBeliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeliminarActionPerformed(evt);
            }
        });

        jBnuevoEliminar.setText("Nuevo");
        jBnuevoEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnuevoEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBeliminar)
                .addGap(75, 75, 75)
                .addComponent(jBcancelar)
                .addGap(60, 60, 60)
                .addComponent(jBnuevoEliminar)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBcancelar)
                    .addComponent(jBeliminar)
                    .addComponent(jBnuevoEliminar))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jBeliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeliminarActionPerformed
        try {
            int selectedRow = jTprestamo.getSelectedRow();
            if (selectedRow != -1 && jTprestamo.getColumnCount() > 1) {
                int idPrestamo = Integer.parseInt(jTprestamo.getModel().getValueAt(selectedRow, 0).toString());
                int idLibro = Integer.parseInt(jTprestamo.getModel().getValueAt(selectedRow, 1).toString()); // Asegúrate de que este es el índice correcto para el idLibro en tu tabla
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(idPrestamo);
                if (prestamo.existe()) {
                    prestamo.eliminarRegistro(idPrestamo);
                    prestamo.aumentarUnidadesLibro(idLibro);
                    mostrarTablaPrestamo();
                    JOptionPane.showMessageDialog(null, "Eliminación exitosa.");
                } else {
                    JOptionPane.showMessageDialog(null, "El préstamo con el ID proporcionado no existe.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un préstamo para eliminar.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jBeliminarActionPerformed

    private void jBnuevoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnuevoEliminarActionPerformed
        quitarFiltrado(jTprestamo);
        this.jTFcodigoLibroEliminar.setText("");
        this.jTFnombreLibroEliminar.setText("");
    }//GEN-LAST:event_jBnuevoEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(JFEliminacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEliminacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEliminacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEliminacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFEliminacionPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBeliminar;
    private javax.swing.JButton jBnuevoEliminar;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTFcodigoLibroEliminar;
    private javax.swing.JTextField jTFnombreLibroEliminar;
    private javax.swing.JTable jTprestamo;
    // End of variables declaration//GEN-END:variables
}
