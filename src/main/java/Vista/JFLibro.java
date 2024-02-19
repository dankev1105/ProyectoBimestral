package Vista;

import Negocio.Autor;
import Negocio.Fecha;
import Negocio.Libro;
import PConexion.Conexion;
import Vista.JFMenuPrincipal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JFLibro extends javax.swing.JFrame {
    Libro libro;
    Autor autor;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
       
    public JFLibro() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        this.setResizable(false);
        this.jTFlibroEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();

            filtrarTabla(text);
        }
    });
     mostrarTabla();
    }
    public void filtrarTabla(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosLibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3));
        }
    }
    public void mostrarTabla(){
        String sql = "SELECT*FROM Libro";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Genero");
        model.addColumn("IdLibro");
        model.addColumn("Unidades");
        jTdatosLibro.setModel(model);
        String [] datos = new String [4];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=String.valueOf(rs.getLong(3));
                datos[3]=String.valueOf(rs.getInt(4));
                model.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }
    
      public void filtrarTablaAutor(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3));
        }
        }
      
        public void mostrarTablaAutor(){
        String sql = "SELECT*FROM Autor";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("IdAutor");
        jTdatosLibro.setModel(model);
        String [] datosAutor = new String [3];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datosAutor[0]=rs.getString(1);
                datosAutor[1]=rs.getString(2);
                datosAutor[2]=String.valueOf(rs.getLong(3));
                model.addRow(datosAutor);
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
        jTAlibroActual = new javax.swing.JTextArea();
        jBinsertarLibro = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFtituloLibro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFgeneroLibro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFunidadesLibro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFidLibro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTFidAutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFnombreAutor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFfechaNacimientoAutor = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTAautorActual = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jBeditarLibro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFlibroEditar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBborrarLibro = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFlibroBorrar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTdatosLibro = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTdatosAutor = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTAlibroActual.setEditable(false);
        jTAlibroActual.setColumns(20);
        jTAlibroActual.setRows(5);
        jTAlibroActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Libro Actual"));
        jScrollPane2.setViewportView(jTAlibroActual);

        jBinsertarLibro.setText("Insertar");
        jBinsertarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarLibroActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Libro:"));

        jLabel1.setText("Titulo del Libro:");

        jLabel2.setText("Genero:");

        jLabel3.setText("Unidades del Libro:");

        jTFunidadesLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFunidadesLibroActionPerformed(evt);
            }
        });
        jTFunidadesLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFunidadesLibroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFunidadesLibroKeyTyped(evt);
            }
        });

        jLabel4.setText("Id Libro:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFidLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jTFunidadesLibro)
                    .addComponent(jTFgeneroLibro)
                    .addComponent(jTFtituloLibro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFtituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFgeneroLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTFunidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFidLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Autor:"));

        jTFidAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFidAutorActionPerformed(evt);
            }
        });

        jLabel7.setText("Id Autor:");

        jLabel8.setText("Nombre:");

        jTFnombreAutor.setEditable(false);

        jLabel11.setText("Fecha de Nacimiento:");

        jTFfechaNacimientoAutor.setEditable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jTFidAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTFfechaNacimientoAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTFnombreAutor))
                        .addGap(27, 27, 27))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFnombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFfechaNacimientoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFidAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(34, 34, 34))
        );

        jTAautorActual.setEditable(false);
        jTAautorActual.setColumns(20);
        jTAautorActual.setRows(5);
        jTAautorActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Autor Actual:"));
        jScrollPane4.setViewportView(jTAautorActual);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jBinsertarLibro)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jScrollPane4)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBinsertarLibro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jBeditarLibro.setText("Editar");
        jBeditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarLibroActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese el Código del Libro a Editar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFlibroEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jBeditarLibro)))
                .addContainerGap(292, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTFlibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBeditarLibro)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jBborrarLibro.setText("Borrar");
        jBborrarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarLibroActionPerformed(evt);
            }
        });

        jLabel6.setText("Ingrese el Código del Libro a Borrar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFlibroBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jBborrarLibro)))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTFlibroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBborrarLibro)
                .addContainerGap(303, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Borrar", jPanel3);

        jTdatosLibro = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTdatosLibro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTdatosLibro);

        jTdatosAutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTdatosAutor);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel10.setText("LIBRO");

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
                .addGap(212, 212, 212)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private boolean existeLibro(long idLibro) {
        String query = "SELECT * FROM Libro WHERE IdLibro = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idLibro);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true; 
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del libro: " + ex.toString());
        }
        return false;
        }
   
    private void jBinsertarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarLibroActionPerformed
        TableModel model1 = jTdatosAutor.getModel();
        TableModel model2 = jTdatosLibro.getModel();
        try {
            
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Libro(NombreLibro, Genero , IdLibro, "
                    + "UnidadesDisponibles, IdAutor) VALUES (?,?,?,?)");
            pps.setString(1, jTFtituloLibro.getText());
            pps.setString(2, jTFgeneroLibro.getText());
            pps.setInt(3, Integer.parseInt(jTFunidadesLibro.getText()));
            pps.setLong(4, Long.parseLong(jTFidLibro.getText()));
            
            pps.executeUpdate();
             
            JOptionPane.showMessageDialog(null, "Datos guardados");
       
            libro = new Libro( Long.parseLong(jTFidLibro.getText()),
            Integer.parseInt(jTFunidadesLibro.getText()), jTFtituloLibro.getText(), 
            jTFgeneroLibro.getText());
            
            jTAlibroActual.setText(libro.toString());
            
            mostrarTabla();
            
            int filaEncontrada = -1;
            
            if(this.jTFidAutor.getText().length()==0)
             JOptionPane.showMessageDialog(null, "El id esta vacio","Error",JOptionPane.WARNING_MESSAGE);    
             else{
               for (int fila = 0; fila < model1.getRowCount(); fila++) {
            String idEnFila = model1.getValueAt(fila, 3).toString(); 
             if (idEnFila.equals(jTFidAutor.getText())) {
            filaEncontrada = fila;
            break;
        }
        }
        }
         if(filaEncontrada==-1){
             JOptionPane.showMessageDialog(null, "El id no existe");
         }  
         else{
        jTFnombreAutor.setText(model1.getValueAt(filaEncontrada, 0).toString());
        jTFfechaNacimientoAutor.setText(model1.getValueAt(filaEncontrada, 1).toString());
          
    }
        }
  
        
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Libro ya registrado");
        }
        
        

    }//GEN-LAST:event_jBinsertarLibroActionPerformed

    private void jTFunidadesLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFunidadesLibroActionPerformed
 
    }//GEN-LAST:event_jTFunidadesLibroActionPerformed
    private void eliminarLibroEnBaseDeDatos(long idLibro) {
        String query = "DELETE FROM Libro WHERE IdLibro = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idLibro);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el libro de la base de datos: " + ex.toString());
        }
    }
    private void jBborrarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarLibroActionPerformed
        try {
            long idLibro = Long.parseLong(this.jTFlibroBorrar.getText());

            if (existeLibro(idLibro)) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    eliminarLibroEnBaseDeDatos(idLibro);
                    mostrarTabla();

                    JOptionPane.showMessageDialog(null, "Libro eliminado correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El libro con el id ingresado no existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBborrarLibroActionPerformed
    private void actualizarLibroEnBaseDeDatos(long idLibro, int nuevaCantidad, String nuevoTitulo, String nuevoGenero){
        String queryCantidad = "UPDATE Libro SET UnidadesDisponibles = ? WHERE IdLibro = ?";
        String queryNombre = "UPDATE Libro SET NombreLibro = ? WHERE IdLibro = ?";
        String queryGenero = "UPDATE Libro SET Genero = ? WHERE IdLibro = ?";

        try (PreparedStatement stCantidad = cn.prepareStatement(queryCantidad);
            PreparedStatement stNombre = cn.prepareStatement(queryNombre);
            PreparedStatement stGenero = cn.prepareStatement(queryGenero)) {

            stCantidad.setInt(1, nuevaCantidad);
            stCantidad.setLong(2, idLibro);
            stCantidad.executeUpdate();

            stNombre.setString(1, nuevoTitulo);
            stNombre.setLong(2, idLibro);
            stNombre.executeUpdate();

            stGenero.setString(1, nuevoGenero);
            stGenero.setLong(2, idLibro);
            stGenero.executeUpdate();
            mostrarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estudiante en la base de datos: " + ex.toString());
        }
    }
    private void jBeditarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarLibroActionPerformed
        try {
            long idLibro = Long.parseLong(this.jTFlibroEditar.getText());

            if (existeLibro(idLibro)) {
                String nuevoTitulo = JOptionPane.showInputDialog(null, "Nuevo Titulo del Libro:");
                String nuevoGenero = JOptionPane.showInputDialog(null, "Nuevo Genero del Libro:");
                String nuevaCantidadStr = JOptionPane.showInputDialog(null, "Nueva Cantidad del Libro:");
                int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                
                // Aquí es donde actualizamos el libro en la base de datos
                actualizarLibroEnBaseDeDatos(idLibro, nuevaCantidad, nuevoTitulo, nuevoGenero);
                mostrarTabla();

                JOptionPane.showMessageDialog(null, "Libro actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "El libro con el id ingresado no existe");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }
    }//GEN-LAST:event_jBeditarLibroActionPerformed
   
    private void jTFunidadesLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFunidadesLibroKeyTyped
    }//GEN-LAST:event_jTFunidadesLibroKeyTyped

    private void jTFunidadesLibroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFunidadesLibroKeyReleased
    }//GEN-LAST:event_jTFunidadesLibroKeyReleased
 
//    private boolean existeAutor(long idAutor) {
//        String query = "SELECT * FROM Autor WHERE IdAutor = ?";
//
//        try (PreparedStatement st = cn.prepareStatement(query)) {
//            st.setLong(1, idAutor);
//            ResultSet rs = st.executeQuery();
//
//            if (rs.next()) {
//                return true; 
//            } else {
//                return false;
//            }
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del libro: " + ex.toString());
//        }
//        return false;
//        }
    private void jTFidAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidAutorActionPerformed
//   try {
//            PreparedStatement pps = cn.prepareStatement("INSERT INTO Autor(NombreAutor, FechaNacimiento ,IdAutor) VALUES (?,?,?)");
//            pps.setString(1,jTFnombreAutor.getText());
//            String diaSeleccionado = (String) jCBdiaAutor.getSelectedItem();
//            String mesSeleccionado = (String) jCBmesAutor.getSelectedItem();
//            String anioSeleccionado = (String) jCBañoAutor.getSelectedItem();
//
//            // Combina los elementos seleccionados en el formato de fecha deseado (asumo que es 'YYYY-MM-DD')
//            String fechaNacimiento = anioSeleccionado + "/" + mesSeleccionado + "/" + diaSeleccionado;
//
//            pps.setString(2, fechaNacimiento);
//            pps.setInt(3,Integer.parseInt(jTFidAutor.getText()));
//            pps.executeUpdate();
//            JOptionPane.showMessageDialog(null,"Datos guardados");
//            Fecha fechaNacimientoAutor = new Fecha(fechaNacimiento);
//            autor = new Autor(Long.parseLong(jTFidAutor.getText()),jTFnombreAutor.getText(),fechaNacimientoAutor);
//            jTAautorActual.setText(autor.toString());
//            mostrarTabla();
//            //jTAlistaAutor.setText(listaAutor.toString());
//        }
//        catch (SQLException ex){
//            JOptionPane.showMessageDialog(null, "Autor ya registrado");
//        }
    }//GEN-LAST:event_jTFidAutorActionPerformed
     
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
    private javax.swing.JButton jBborrarLibro;
    private javax.swing.JButton jBeditarLibro;
    private javax.swing.JButton jBinsertarLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTAautorActual;
    private javax.swing.JTextArea jTAlibroActual;
    private javax.swing.JTextField jTFfechaNacimientoAutor;
    private javax.swing.JTextField jTFgeneroLibro;
    private javax.swing.JTextField jTFidAutor;
    private javax.swing.JTextField jTFidLibro;
    private javax.swing.JTextField jTFlibroBorrar;
    private javax.swing.JTextField jTFlibroEditar;
    private javax.swing.JTextField jTFnombreAutor;
    private javax.swing.JTextField jTFtituloLibro;
    private javax.swing.JTextField jTFunidadesLibro;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTable jTdatosLibro;
    // End of variables declaration//GEN-END:variables
}
