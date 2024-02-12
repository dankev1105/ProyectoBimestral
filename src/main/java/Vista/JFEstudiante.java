package Vista;

import Negocio.Estudiante;
import Negocio.Fecha;
import PConexion.Conexion;
import Vista.JFMenuPrincipal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFEstudiante extends javax.swing.JFrame {
    Estudiante estudiante;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
       
    public JFEstudiante() {
        initComponents();
        //icono
        File file = new File("C:/Users/Francis Bravo/Videos/ProyectoBimestral/src/main/java/Imagenes/Estudiante.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        //
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        this.setResizable(false);
        this.jTFestudianteEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();

            filtrarTabla(text);
        }
    });
     mostrarTabla();
    }
    public void filtrarTabla(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosEstudiantes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosEstudiantes.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3));
        }
    }
    public void mostrarTabla(){
        String sql = "SELECT*FROM Estudiante";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Correo institucional");
        model.addColumn("ID");
        jTdatosEstudiantes.setModel(model);
        String [] datos = new String [4];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=String.valueOf(rs.getInt(4));
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAestudianteActual = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFnombreEstudiante = new javax.swing.JTextField();
        jTFidEstudiante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFcorreoInstitucionalEstudiante = new javax.swing.JTextField();
        jBinsertarEstudiante = new javax.swing.JButton();
        jCBanioEstudiante = new javax.swing.JComboBox<>();
        jCBmesEstudiante = new javax.swing.JComboBox<>();
        jCBdiaEstudiante = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jBeditarEstudiante = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFestudianteEditar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBborrarEstudiante = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFestudianteBorrar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTdatosEstudiantes = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTAestudianteActual.setEditable(false);
        jTAestudianteActual.setColumns(20);
        jTAestudianteActual.setRows(5);
        jTAestudianteActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiante Actual"));
        jScrollPane2.setViewportView(jTAestudianteActual);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Fecha de Nacimiento:");

        jLabel3.setText("Código Único:");

        jTFidEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFidEstudianteActionPerformed(evt);
            }
        });
        jTFidEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFidEstudianteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFidEstudianteKeyTyped(evt);
            }
        });

        jLabel4.setText("Correo Institucional:");

        jBinsertarEstudiante.setText("Insertar");
        jBinsertarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarEstudianteActionPerformed(evt);
            }
        });

        jCBanioEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970" }));

        jCBmesEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jCBdiaEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFidEstudiante)
                                    .addComponent(jTFcorreoInstitucionalEstudiante)
                                    .addComponent(jTFnombreEstudiante))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCBanioEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jCBmesEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jCBdiaEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel7)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(64, 64, 64)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jBinsertarEstudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFnombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBanioEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBmesEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBdiaEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFidEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFcorreoInstitucionalEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBinsertarEstudiante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jBeditarEstudiante.setText("Editar");
        jBeditarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarEstudianteActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese el Código del Estudiante a Editar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFestudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jBeditarEstudiante)))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTFestudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBeditarEstudiante)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jBborrarEstudiante.setText("Borrar");
        jBborrarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarEstudianteActionPerformed(evt);
            }
        });

        jLabel6.setText("Ingrese el Código del Estudiante a Borrar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFestudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jBborrarEstudiante)))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTFestudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBborrarEstudiante)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Borrar", jPanel3);

        jTdatosEstudiantes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTdatosEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTdatosEstudiantes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel10.setText("ESTUDIANTE");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(206, 206, 206))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBinsertarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarEstudianteActionPerformed
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Estudiante(NombreEstudiante, FechaNacimiento, CorreoInstitucional, IdEstudiante) VALUES (?,?,?,?)");
            pps.setString(1, jTFnombreEstudiante.getText());

            String diaSeleccionado = (String) jCBdiaEstudiante.getSelectedItem();
            String mesSeleccionado = (String) jCBmesEstudiante.getSelectedItem();
            String anioSeleccionado = (String) jCBanioEstudiante.getSelectedItem();

            // Combina los elementos seleccionados en el formato de fecha deseado (asumo que es 'YYYY-MM-DD')
            String fechaNacimiento = anioSeleccionado + "/" + mesSeleccionado + "/" + diaSeleccionado;

            pps.setString(2, fechaNacimiento);
            pps.setString(3, jTFcorreoInstitucionalEstudiante.getText());
            pps.setInt(4, Integer.parseInt(jTFidEstudiante.getText()));

            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados");

            Fecha fechaNacimientoEstudiante = new Fecha(fechaNacimiento);
            estudiante = new Estudiante(Integer.parseInt(jTFidEstudiante.getText()),jTFcorreoInstitucionalEstudiante.getText(), jTFnombreEstudiante.getText(), fechaNacimientoEstudiante);
            jTAestudianteActual.setText(estudiante.toString());
            mostrarTabla();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Estudiante ya registrado");
        }

    }//GEN-LAST:event_jBinsertarEstudianteActionPerformed

    private void jTFidEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFidEstudianteActionPerformed
    private void eliminarEstudianteEnBaseDeDatos(long idEstudiante) {
        String query = "DELETE FROM Estudiante WHERE IdEstudiante = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idEstudiante);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante de la base de datos: " + ex.toString());
        }
    }
    
    private boolean existeEstudiante(int idEstudiante) {
        String query = "SELECT * FROM Estudiante WHERE IdEstudiante = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, idEstudiante);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true; 
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del estudiante: " + ex.toString());
        }
        return false;
    }
    private void jBborrarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarEstudianteActionPerformed
        try {
            int idEstudiante = Integer.parseInt(this.jTFestudianteBorrar.getText());

            if (existeEstudiante(idEstudiante)) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    eliminarEstudianteEnBaseDeDatos(idEstudiante);
                    mostrarTabla();

                    JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante con el id ingresado no existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBborrarEstudianteActionPerformed

    private void jBeditarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarEstudianteActionPerformed
        try {
            int idEstudiante = Integer.parseInt(this.jTFestudianteEditar.getText());

            if (existeEstudiante(idEstudiante)) {
                String nuevoNombre = JOptionPane.showInputDialog(null, "Nuevo Nombre del Estudiante:");
                String nuevaFechaNacimiento = JOptionPane.showInputDialog(null, "Nueva Fecha de Nacimiento del Estudiante:");
                String nuevoCorreoInstitucional = JOptionPane.showInputDialog(null, "Nueva Correo Institucional del Estudiante:");
                // Aquí es donde actualizamos el autor en la base de datos
                actualizarEstudianteEnBaseDeDatos(idEstudiante, nuevoNombre, nuevaFechaNacimiento, nuevoCorreoInstitucional);
                mostrarTabla();

                JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante con el id ingresado no existe");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBeditarEstudianteActionPerformed
    private void actualizarEstudianteEnBaseDeDatos(int idEstudiante, String nuevoNombre, String nuevaFechaNacimiento, String nuevoCorreoInstitucional) {
        String queryNombre = "UPDATE Estudiante SET NombreEstudiante = ? WHERE IdEstudiante = ?";
        String queryFecha = "UPDATE Estudiante SET FechaNacimiento = ? WHERE IdEstudiante = ?";
        String queryCorreo = "UPDATE Estudiante SET CorreoInstitucional = ? WHERE IdEstudiante = ?";

        try (PreparedStatement stNombre = cn.prepareStatement(queryNombre);
            PreparedStatement stFecha = cn.prepareStatement(queryFecha);
            PreparedStatement stCorreo = cn.prepareStatement(queryCorreo)) {

            stNombre.setString(1, nuevoNombre);
            stNombre.setInt(2, idEstudiante);
            stNombre.executeUpdate();

            stFecha.setString(1, nuevaFechaNacimiento);
            stFecha.setInt(2, idEstudiante);
            stFecha.executeUpdate();

            stCorreo.setString(1, nuevoCorreoInstitucional);
            stCorreo.setInt(2, idEstudiante);
            stCorreo.executeUpdate();
            mostrarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estudiante en la base de datos: " + ex.toString());
        }
    }
    private void jTFidEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyTyped
    }//GEN-LAST:event_jTFidEstudianteKeyTyped

    private void jTFidEstudianteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyReleased
    }//GEN-LAST:event_jTFidEstudianteKeyReleased

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
            java.util.logging.Logger.getLogger(JFEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBborrarEstudiante;
    private javax.swing.JButton jBeditarEstudiante;
    private javax.swing.JButton jBinsertarEstudiante;
    private javax.swing.JComboBox<String> jCBanioEstudiante;
    private javax.swing.JComboBox<String> jCBdiaEstudiante;
    private javax.swing.JComboBox<String> jCBmesEstudiante;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTAestudianteActual;
    private javax.swing.JTextField jTFcorreoInstitucionalEstudiante;
    private javax.swing.JTextField jTFestudianteBorrar;
    private javax.swing.JTextField jTFestudianteEditar;
    private javax.swing.JTextField jTFidEstudiante;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTdatosEstudiantes;
    // End of variables declaration//GEN-END:variables
}
