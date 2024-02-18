package Vista;

import Negocio.Autor;
import Negocio.Fecha;
import PConexion.Conexion;
import Vista.JFMenuPrincipal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFAutor extends javax.swing.JFrame {
    Autor autor;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    
    public JFAutor() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        this.setResizable(false);
        //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Imagenes/bibliotecaImagen.png"));
        this.jTFautorEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();

            filtrarTabla(text);
        }
    });
     mostrarTabla();
}
    
    public void filtrarTabla(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2)); // El segundo argumento es el índice de la columna del ID
        }
    }

    public void mostrarTabla(){
        String sql = "SELECT*FROM Autor";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre Autor");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Id Autor");

        jTdatosAutor.setModel(model);
        String [] datos = new String [3];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=String.valueOf(rs.getInt(3)); // Aquí cambiamos a getInt()
                model.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAautorActual = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFnombreAutor = new javax.swing.JTextField();
        jBinsertarAutor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTfIdAutor = new javax.swing.JTextField();
        jCBanioAutor = new javax.swing.JComboBox<>();
        jCBmesAutor = new javax.swing.JComboBox<>();
        jCBdiaAutor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBeditarAutor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFautorEditar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBborrarAutor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFautorBorrar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTdatosAutor = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTAautorActual.setEditable(false);
        jTAautorActual.setColumns(20);
        jTAautorActual.setRows(5);
        jTAautorActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Autor Actual"));
        jScrollPane2.setViewportView(jTAautorActual);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Fecha de Nacimiento:");

        jBinsertarAutor.setText("Insertar");
        jBinsertarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarAutorActionPerformed(evt);
            }
        });

        jLabel3.setText("Código Autor:");

        jCBanioAutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970" }));

        jCBmesAutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jCBdiaAutor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel7.setText("Año");

        jLabel8.setText("Mes");

        jLabel9.setText("Día");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jCBanioAutor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(22, 22, 22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTFnombreAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCBmesAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCBdiaAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jTfIdAutor)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBinsertarAutor)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel9)
                        .addGap(43, 43, 43)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFnombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBanioAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBmesAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBdiaAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTfIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jBinsertarAutor)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jBeditarAutor.setText("Editar");
        jBeditarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarAutorActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese el Código del Autor a Editar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFautorEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jBeditarAutor)))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTFautorEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBeditarAutor)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jBborrarAutor.setText("Borrar");
        jBborrarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarAutorActionPerformed(evt);
            }
        });

        jLabel6.setText("Ingrese el Código del Autor a Borrar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(173, 173, 173)
                            .addComponent(jTFautorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(243, 243, 243)
                            .addComponent(jBborrarAutor))))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFautorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBborrarAutor)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Borrar", jPanel3);

        jTdatosAutor = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTdatosAutor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTdatosAutor);

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel4.setText("AUTOR");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Menú Principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBborrarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarAutorActionPerformed
        try {
            int idAutor = Integer.parseInt(this.jTFautorBorrar.getText());

            if (existeAutor(idAutor)) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    eliminarAutorEnBaseDeDatos(idAutor);
                    mostrarTabla();
                    // Actualizar el JTextArea con la lista de autores
                    //this.jTAlistaAutor.setText(listaAutor.toString());

                    JOptionPane.showMessageDialog(null, "Autor eliminado correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El autor con el id ingresado no existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBborrarAutorActionPerformed
    private void actualizarAutorEnBaseDeDatos(int idAutor, String nuevoNombre, String nuevaFechaNacimiento) {
        String queryNombre = "UPDATE Autor SET NombreAutor = ? WHERE IdAutor = ?";
        String queryFecha = "UPDATE Autor SET FechaNacimiento = ? WHERE IdAutor = ?";

        try (PreparedStatement stNombre = cn.prepareStatement(queryNombre);
             PreparedStatement stFecha = cn.prepareStatement(queryFecha)) {

            stNombre.setString(1, nuevoNombre);
            stNombre.setInt(2, idAutor);
            stNombre.executeUpdate();

            stFecha.setString(1, nuevaFechaNacimiento);
            stFecha.setInt(2, idAutor);
            stFecha.executeUpdate();
            mostrarTabla();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar autor en la base de datos: " + ex.toString());
        }
    }
    private void jBeditarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarAutorActionPerformed
        try {
            int idAutor = Integer.parseInt(this.jTFautorEditar.getText());

            if (existeAutor(idAutor)) {
                String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo Nombre del Autor:");
                String nuevaFechaNacimiento = JOptionPane.showInputDialog(null, "Nueva Fecha de Nacimiento del Autor:");

                // Aquí es donde actualizamos el autor en la base de datos
                actualizarAutorEnBaseDeDatos(idAutor, nuevoNombre, nuevaFechaNacimiento);
                mostrarTabla();

                JOptionPane.showMessageDialog(null, "Autor actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "El autor con el id ingresado no existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBeditarAutorActionPerformed

    private boolean existeAutor(int idAutor) {
        String query = "SELECT * FROM Autor WHERE IdAutor = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, idAutor);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true; // El autor existe
            } else {
                return false; // El autor no existe
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del autor: " + ex.toString());
        }
    return false;
    }
    
    private void jBinsertarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarAutorActionPerformed
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Autor(NombreAutor, FechaNacimiento ,IdAutor) VALUES (?,?,?)");
            pps.setString(1,jTFnombreAutor.getText());
            String diaSeleccionado = (String) jCBdiaAutor.getSelectedItem();
            String mesSeleccionado = (String) jCBmesAutor.getSelectedItem();
            String anioSeleccionado = (String) jCBanioAutor.getSelectedItem();

            // Combina los elementos seleccionados en el formato de fecha deseado (asumo que es 'YYYY-MM-DD')
            String fechaNacimiento = anioSeleccionado + "/" + mesSeleccionado + "/" + diaSeleccionado;

            pps.setString(2, fechaNacimiento);
            pps.setInt(3,Integer.parseInt(jTfIdAutor.getText()));
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");
            Fecha fechaNacimientoAutor = new Fecha(fechaNacimiento);
            autor = new Autor(Long.parseLong(jTfIdAutor.getText()),jTFnombreAutor.getText(),fechaNacimientoAutor);
            jTAautorActual.setText(autor.toString());
            mostrarTabla();
            //jTAlistaAutor.setText(listaAutor.toString());
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Autor ya registrado");
        }
    }//GEN-LAST:event_jBinsertarAutorActionPerformed
    
    private void eliminarAutorEnBaseDeDatos(long idAutor) {
        String query = "DELETE FROM Autor WHERE IdAutor = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idAutor);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar autor de la base de datos: " + ex.toString());
        }
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
            java.util.logging.Logger.getLogger(JFAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFAutor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFAutor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBborrarAutor;
    private javax.swing.JButton jBeditarAutor;
    private javax.swing.JButton jBinsertarAutor;
    private javax.swing.JComboBox<String> jCBanioAutor;
    private javax.swing.JComboBox<String> jCBdiaAutor;
    private javax.swing.JComboBox<String> jCBmesAutor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTAautorActual;
    private javax.swing.JTextField jTFautorBorrar;
    private javax.swing.JTextField jTFautorEditar;
    private javax.swing.JTextField jTFnombreAutor;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTextField jTfIdAutor;
    // End of variables declaration//GEN-END:variables
}
