package Vista;

import Negocio.Autor;
import Negocio.Libro;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
        this.setResizable(false);    
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
    this.jTFlibroEditarFiltrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombreEditar(text);    
        }}); 
        this.jTFlibroEditarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaIdEditar(text);    
        }}); 
        this.jTFlibroBorrarFiltrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombreBorrar(text);
        }});
        this.jTFlibroBorrarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaIdBorrar(text);
        }});
        
    mostrarTabla();
    }
    
     public void filtrarTablaNombreEditar(String query){
        this.jTFlibroEditarFiltrarID.setText("");
        this.jTFlibroBorrarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarID.setText("");    
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosLibro.setRowSorter(tr);
        if (query.trim().length() == 0) {
        tr.setRowFilter(null);
    } else {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
            filters.add(RowFilter.regexFilter("(?i)" + query, 0)); // Ignore case
        }
        tr.setRowFilter(RowFilter.orFilter(filters));
        }
    }
    
    public void filtrarTablaNombreBorrar(String query){
        this.jTFlibroEditarFiltrarID.setText("");
        this.jTFlibroEditarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarID.setText("");    
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
         jTdatosLibro.setRowSorter(tr);
        if (query.trim().length() == 0) {
        tr.setRowFilter(null);
    } else {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
            filters.add(RowFilter.regexFilter("(?i)" + query, 0)); // Ignore case
        }
        tr.setRowFilter(RowFilter.orFilter(filters));
        }
    }

    public void filtrarTablaIdEditar(String query) {
        this.jTFlibroEditarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarID.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosLibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
        }
    }
    public void filtrarTablaIdBorrar(String query) {
        this.jTFlibroEditarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarNombre.setText("");
        this.jTFlibroEditarFiltrarID.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosLibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
        }
    }
    
  
    public void mostrarTabla(){
    String sql = "SELECT*FROM Libro";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Nombre");
        model1.addColumn("Genero");
        model1.addColumn("IdLibro");
        model1.addColumn("IdAutor");
        model1.addColumn("Unidades");
        
        
        jTdatosLibro.setModel(model1);
        String [] datos = new String [5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=String.valueOf(rs.getLong(3));
                datos[3]=String.valueOf(rs.getInt(4));
                datos[4]=String.valueOf(rs.getInt(5));
                model1.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
	String sql2 = "SELECT * FROM Autor";
        Statement st2;
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("Nombre");
        model2.addColumn("Fecha de Nacimiento");
        model2.addColumn("IdAutor");
        
        jTdatosAutor.setModel(model2); 
        String [] datosAutor = new String [3];
        try{
            st2 = conexion.createStatement();
            ResultSet rs = st2.executeQuery(sql2);
            while(rs.next()){
                datosAutor[0]=rs.getString(1);
                datosAutor[1]=rs.getString(2);
                datosAutor[2]=String.valueOf(rs.getLong(3));
                model2.addRow(datosAutor);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFtituloLibro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFgeneroLibro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFidLibro = new javax.swing.JTextField();
        jTFunidadesLibro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTFidAutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFnombreAutor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFfechaNacimientoAutor = new javax.swing.JTextField();
        jBinsertarLib = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jBeditarLibro = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTFtituloEditar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTFgeneroEditar = new javax.swing.JTextField();
        jTFunidadesEditar = new javax.swing.JTextField();
        jTFidLibroEditar = new javax.swing.JTextField();
        jTFfiltrarPorNombre = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTFlibroEditarFiltrarNombre = new javax.swing.JTextField();
        jTFlibroEditarFiltrarID = new javax.swing.JTextField();
        jBactualizarLibro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTFtituloBorrar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTFgeneroBorrar = new javax.swing.JTextField();
        jTFunidadesBorrar = new javax.swing.JTextField();
        jTFidLibroBorrar = new javax.swing.JTextField();
        jTFfiltrarPorNombre1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTFlibroBorrarFiltrarNombre = new javax.swing.JTextField();
        jTFlibroBorrarFiltrarID = new javax.swing.JTextField();
        jBMostrar = new javax.swing.JButton();
        jBborrarEstudiante = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTdatosLibro = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTdatosAutor = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Libro:"));

        jLabel1.setText("Titulo del Libro:");

        jLabel2.setText("Genero:");

        jLabel3.setText("Unidades del Libro:");

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
                    .addComponent(jTFgeneroLibro)
                    .addComponent(jTFtituloLibro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFunidadesLibro))
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
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFunidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFidLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
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

        jBinsertarLib.setText("Insertar");
        jBinsertarLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarLibActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jBinsertarLib)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBinsertarLib)))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBeditarLibro.setText("Editar");
        jBeditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarLibroActionPerformed(evt);
            }
        });

        jLabel9.setText("Titulo del Libro:");

        jLabel12.setText("Genero:");

        jLabel13.setText("Unidades del Libro:");

        jLabel14.setText("Id Libro:");

        jTFidLibroEditar.setEditable(false);

        jTFfiltrarPorNombre.setText("Filtrar por Nombre:");

        jLabel16.setText("Filtrar por ID:");

        jTFlibroEditarFiltrarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFlibroEditarFiltrarNombreActionPerformed(evt);
            }
        });

        jBactualizarLibro.setText("Actualizar");
        jBactualizarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jTFfiltrarPorNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jTFlibroEditarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jTFlibroEditarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel14))
                                .addGap(78, 78, 78)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFidLibroEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(jTFunidadesEditar)
                                    .addComponent(jTFgeneroEditar)
                                    .addComponent(jTFtituloEditar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                                .addComponent(jBactualizarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBeditarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(252, 252, 252))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTFtituloEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFgeneroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTFunidadesEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBeditarLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFidLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBactualizarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFfiltrarPorNombre)
                    .addComponent(jLabel16)
                    .addComponent(jTFlibroEditarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFlibroEditarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel17.setText("Titulo del Libro:");

        jLabel18.setText("Genero:");

        jLabel19.setText("Unidades del Libro:");

        jLabel20.setText("Id Libro:");

        jTFidLibroBorrar.setEditable(false);

        jTFfiltrarPorNombre1.setText("Filtrar por Nombre:");

        jLabel22.setText("Filtrar por ID:");

        jTFlibroBorrarFiltrarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFlibroBorrarFiltrarNombreActionPerformed(evt);
            }
        });

        jBMostrar.setText("Mostrar Libro");
        jBMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarActionPerformed(evt);
            }
        });

        jBborrarEstudiante.setText("Borrar");
        jBborrarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jTFfiltrarPorNombre1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFlibroBorrarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jTFlibroBorrarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel20))
                                .addGap(78, 78, 78)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFidLibroBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(jTFunidadesBorrar)
                                    .addComponent(jTFgeneroBorrar)
                                    .addComponent(jTFtituloBorrar)))
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBborrarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(252, 252, 252))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTFtituloBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTFgeneroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTFunidadesBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBborrarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTFidLibroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFfiltrarPorNombre1)
                    .addComponent(jLabel22)
                    .addComponent(jTFlibroBorrarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFlibroBorrarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel10.setText("LIBRO");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Autores");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setText("Libros");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(168, 168, 168))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(442, 442, 442))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15))
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
    
    private void jBinsertarLibroActionPerformed(java.awt.event.ActionEvent evt) {                                                
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Libro(NombreLibro, Genero ,"
            + " IdLibro, UnidadesDisponibles, IdAutor) VALUES (?,?,?,?,?)");
            pps.setString(1, jTFtituloLibro.getText());
            pps.setString(2, jTFgeneroLibro.getText());
            pps.setLong(3, Long.parseLong(jTFidLibro.getText()));
            pps.setInt(4, Integer.parseInt(jTFunidadesLibro.getText()));
            pps.setLong(5,autor.getIdAutor());
            pps.executeUpdate();
             
            JOptionPane.showMessageDialog(null, "Datos guardados");
      
            libro = new Libro( Integer.parseInt(this.jTFidLibro.getText()),autor,
             Integer.parseInt(jTFunidadesLibro.getText()), jTFtituloLibro.getText(), 
            jTFtituloLibro.getText());
            
            jTAlibroActual.setText(libro.toString());
            mostrarTabla();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Libro ya registrado");
        }
    }                                               

    private void jTFunidadesLibroActionPerformed(java.awt.event.ActionEvent evt) {                                                 
 
    }                                                
    private void eliminarLibroEnBaseDeDatos(long idLibro) {
        String query = "DELETE FROM Libro WHERE IdLibro = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idLibro);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el libro de la base de datos: " + ex.toString());
        }
        
    }   
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
        TableModel model = jTdatosLibro.getModel();
        int filaEncontrada = -1;
        if(this.jTFlibroEditarFiltrarNombre.getText().length()==0 && this.jTFlibroEditarFiltrarID.getText().length()==0)
            JOptionPane.showMessageDialog(null, "Primero llene alguno de los campos","Error",JOptionPane.WARNING_MESSAGE);    
        else{
        if(this.jTFlibroEditarFiltrarNombre.getText().length()==0){
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 2).toString(); 
                if (idEnFila.equals(jTFlibroEditarFiltrarID.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
            if(filaEncontrada==-1){
            JOptionPane.showMessageDialog(null, "No existe ese ID","Error",JOptionPane.WARNING_MESSAGE);
            }else{
                jTFtituloEditar.setText(model.getValueAt(filaEncontrada, 0).toString());
                jTFgeneroEditar.setText(model.getValueAt(filaEncontrada, 1).toString());
                jTFunidadesEditar.setText(model.getValueAt(filaEncontrada, 2).toString());
                jTFidLibroEditar.setText(model.getValueAt(filaEncontrada, 3).toString());    
            }    
        }else{
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 0).toString(); 
                if (idEnFila.equals(jTFlibroEditarFiltrarNombre.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }if(filaEncontrada==-1){
                JOptionPane.showMessageDialog(null, "No existe ese nombre","Error",JOptionPane.WARNING_MESSAGE);
            }
            else{
                jTFtituloEditar.setText(model.getValueAt(filaEncontrada, 0).toString());
                jTFgeneroEditar.setText(model.getValueAt(filaEncontrada, 1).toString());
                jTFunidadesEditar.setText(model.getValueAt(filaEncontrada, 2).toString());
                jTFidLibroEditar.setText(model.getValueAt(filaEncontrada, 3).toString());    
                }    
            }
        }             
    }//GEN-LAST:event_jBeditarLibroActionPerformed

    private void jTFidAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidAutorActionPerformed
        
    }//GEN-LAST:event_jTFidAutorActionPerformed

    private void jTFlibroEditarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroEditarFiltrarNombreActionPerformed
        
        
    }//GEN-LAST:event_jTFlibroEditarFiltrarNombreActionPerformed

    private void jTFlibroBorrarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroBorrarFiltrarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFlibroBorrarFiltrarNombreActionPerformed

    private void jBactualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarLibroActionPerformed
        long idLibro = Long.parseLong(this.jTFidLibroEditar.getText());
                int nuevaCantidad = Integer.parseInt(jTFunidadesEditar.getText());
                String nuevoTitulo = jTFtituloEditar.getText();
                String nuevoGenero = jTFgeneroEditar.getText();
                // Aquí es donde actualizamos el autor en la base de datos
                actualizarLibroEnBaseDeDatos(idLibro, nuevaCantidad, nuevoTitulo, nuevoGenero);
                mostrarTabla();
    }//GEN-LAST:event_jBactualizarLibroActionPerformed

    private void jBMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarActionPerformed
        TableModel model = jTdatosLibro.getModel();
    int filaEncontrada = -1;
    if(this.jTFlibroBorrarFiltrarNombre.getText().length()==0 && this.jTFlibroBorrarFiltrarID.getText().length()==0)
        JOptionPane.showMessageDialog(null, "Primero llene alguno de los campos","Error",JOptionPane.WARNING_MESSAGE);    
    else{
    if(this.jTFlibroBorrarFiltrarNombre.getText().length()==0){
    for (int fila = 0; fila < model.getRowCount(); fila++) {
        String idEnFila = model.getValueAt(fila, 2).toString();
        if (idEnFila.equals(jTFlibroBorrarFiltrarID.getText())) {
            filaEncontrada = fila;
            break;
        }
    }
    if(filaEncontrada==-1){
    JOptionPane.showMessageDialog(null, "No existe ese ID","Error",JOptionPane.WARNING_MESSAGE);
    }
     else{
    jTFtituloBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
    jTFgeneroBorrar.setText(model.getValueAt(filaEncontrada, 1).toString());
    jTFunidadesBorrar.setText(model.getValueAt(filaEncontrada, 2).toString());
    jTFidLibroBorrar.setText(model.getValueAt(filaEncontrada, 3).toString());    
    }    
    }    
    else{
    for (int fila = 0; fila < model.getRowCount(); fila++) {
        String idEnFila = model.getValueAt(fila, 0).toString(); 
        if (idEnFila.equals(jTFlibroBorrarFiltrarNombre.getText())) {
            filaEncontrada = fila;
            break;
        }
    }    
    if(filaEncontrada==-1){
    JOptionPane.showMessageDialog(null, "No existe ese nombre","Error",JOptionPane.WARNING_MESSAGE);
    }
     else{
    jTFtituloBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
    jTFgeneroBorrar.setText(model.getValueAt(filaEncontrada, 1).toString());
    jTFunidadesBorrar.setText(model.getValueAt(filaEncontrada, 2).toString());
    jTFidLibroBorrar.setText(model.getValueAt(filaEncontrada, 3).toString());    
    }    
    }
    }    
    }//GEN-LAST:event_jBMostrarActionPerformed

    private void jBborrarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarEstudianteActionPerformed
    if(jTFtituloBorrar.getText().length()==0){
        JOptionPane.showMessageDialog(null, "Primero seleccione al libro a eliminar","Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
        try {
            long idLibro = Long.parseLong(this.jTFidLibroBorrar.getText());

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
    }       
    }//GEN-LAST:event_jBborrarEstudianteActionPerformed

    private void jBinsertarLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarLibActionPerformed
        TableModel model1 = jTdatosAutor.getModel();

        try {

            PreparedStatement pps = cn.prepareStatement("INSERT INTO Libro(NombreLibro, Genero , IdLibro, UnidadesDisponibles,IdAutor) VALUES (?,?,?,?,?)");
            pps.setString(1, jTFtituloLibro.getText());
            pps.setString(2, jTFgeneroLibro.getText());
            pps.setLong(3, Long.parseLong(jTFidLibro.getText()));
            pps.setInt(4, Integer.parseInt(jTFunidadesLibro.getText()));
            pps.setLong(5,Long.parseLong(jTFidAutor.getText()));

            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados");

            libro = new Libro( Integer.parseInt(jTFidLibro.getText()),autor,
                Integer.parseInt(jTFunidadesLibro.getText()), jTFtituloLibro.getText(),
                jTFgeneroLibro.getText());

            jTAlibroActual.setText(libro.toString());

           mostrarTabla();

            int filaEncontrada = -1;
            TableModel model2 = jTdatosAutor.getModel();
            if(this.jTFidAutor.getText().length()==0)
            JOptionPane.showMessageDialog(null, "El id esta vacio","Error",JOptionPane.WARNING_MESSAGE);
            else{
                for (int fila = 0; fila < model2.getRowCount(); fila++) {
                    String idEnFila = model2.getValueAt(fila, 2).toString();
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

    }//GEN-LAST:event_jBinsertarLibActionPerformed
    
    
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
    private javax.swing.JButton jBMostrar;
    private javax.swing.JButton jBactualizarLibro;
    private javax.swing.JButton jBborrarEstudiante;
    private javax.swing.JButton jBeditarLibro;
    private javax.swing.JButton jBinsertarLib;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTAlibroActual;
    private javax.swing.JTextField jTFfechaNacimientoAutor;
    private javax.swing.JLabel jTFfiltrarPorNombre;
    private javax.swing.JLabel jTFfiltrarPorNombre1;
    private javax.swing.JTextField jTFgeneroBorrar;
    private javax.swing.JTextField jTFgeneroEditar;
    private javax.swing.JTextField jTFgeneroLibro;
    private javax.swing.JTextField jTFidAutor;
    private javax.swing.JTextField jTFidLibro;
    private javax.swing.JTextField jTFidLibroBorrar;
    private javax.swing.JTextField jTFidLibroEditar;
    private javax.swing.JTextField jTFlibroBorrarFiltrarID;
    private javax.swing.JTextField jTFlibroBorrarFiltrarNombre;
    private javax.swing.JTextField jTFlibroEditarFiltrarID;
    private javax.swing.JTextField jTFlibroEditarFiltrarNombre;
    private javax.swing.JTextField jTFnombreAutor;
    private javax.swing.JTextField jTFtituloBorrar;
    private javax.swing.JTextField jTFtituloEditar;
    private javax.swing.JTextField jTFtituloLibro;
    private javax.swing.JTextField jTFunidadesBorrar;
    private javax.swing.JTextField jTFunidadesEditar;
    private javax.swing.JTextField jTFunidadesLibro;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTable jTdatosLibro;
    // End of variables declaration//GEN-END:variables
}
