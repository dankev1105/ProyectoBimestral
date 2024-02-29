package Vista;

import Negocio.Autor;
import Negocio.Fecha;
import PConexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


public class JFAutor extends javax.swing.JFrame {
    Autor autor;//objeto de la clase autor
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    private int autorSeleccionado;
    private int tieneLibrosAsociados;
    public JFAutor() {
        initComponents();
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());//
        setIconImage(icon.getImage());
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        this.setResizable(false);
        
        this.jTFautorBorrarPorID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);   
        }}); 
        this.jTFnombreAutorFiltrarEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);   
        }}); 
        this.jTFcodigoAutorFiltrarEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);   
        }}); 
        this.jTFautorBorrarPorID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text); 
        }
        });
        this.jTFautorBorrarPorNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text); // Aquí está la corrección
        }
    });
    }

    public void filtrarTablaNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            List<RowFilter<Object, Object>> filters = new ArrayList<>();
            for(int columIndex=0;columIndex<model.getColumnCount();columIndex++){
              filters.add(RowFilter.regexFilter("(?i)" + query, 0)); // Ignore case  
            }
            tr.setRowFilter(RowFilter.regexFilter(query, 0)); // Cambia el índice a la columna del nombre
        }
    }

    public void filtrarTablaId(String query) {
        this.jTFnombreAutorEditar.setText("");
        this.jTFautorBorrarPorNombre.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            //en caso que este vacio el campo muestra toda la lista de datos
            tr.setRowFilter(null);
        } else {
            //uso de Filtrado en caso que este lleno el campo 
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
        }
    }

    public void mostrarTabla(){
        String sql = "SELECT * FROM Autor";//usamos la tabla autor de la base de datos
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre Autor");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Id Autor");

        jTdatosAutor.setModel(model);
        String [] datos = new String [3];//numero de columnas de la tabla instanciadas
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=String.valueOf(rs.getInt(3));//conversion de los datos de id autor
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
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jBmostrarAutorEditar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFnombreAutorFiltrarEditar = new javax.swing.JTextField();
        jBactualizarAutor = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTFnombreAutorEditar = new javax.swing.JTextField();
        jTFIDAutorEditar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFcodigoAutorFiltrarEditar = new javax.swing.JTextField();
        jDfechaNacimientoEditar = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jBborrarAutor = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFautorBorrarPorID = new javax.swing.JTextField();
        jBMostrarAutorBorrar = new javax.swing.JButton();
        jTFnombreAutorBorrar = new javax.swing.JTextField();
        jTFfechaAutorBorrar = new javax.swing.JTextField();
        jTFcodigoAutorBorrar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTFautorBorrarPorNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFnombreAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                        .addGap(109, 109, 109))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jBinsertarAutor))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(42, 42, 42))
                                    .addComponent(jTfIdAutor))))
                        .addGap(31, 31, 31)))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTfIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jBinsertarAutor)
                        .addGap(19, 19, 19)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jBmostrarAutorEditar.setText("Mostrar");
        jBmostrarAutorEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmostrarAutorEditarActionPerformed(evt);
            }
        });

        jLabel5.setText("Buscar el Código del Autor a Editar:");

        jBactualizarAutor.setText("Actualizar");
        jBactualizarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarAutorActionPerformed(evt);
            }
        });

        jLabel10.setText("Nombre:");

        jLabel11.setText("Fecha de Nacimiento:");

        jLabel12.setText("Código Autor:");

        jTFIDAutorEditar.setEditable(false);

        jLabel8.setText("Buscar el Nombre del Autor a Editar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jTFnombreAutorFiltrarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFnombreAutorEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jTFIDAutorEditar)
                            .addComponent(jDfechaNacimientoEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBmostrarAutorEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBactualizarAutor)
                    .addComponent(jTFcodigoAutorFiltrarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFnombreAutorFiltrarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTFcodigoAutorFiltrarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTFnombreAutorEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBmostrarAutorEditar)
                            .addComponent(jBactualizarAutor))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDfechaNacimientoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(28, 28, 28)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFIDAutorEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jBborrarAutor.setText("Borrar");
        jBborrarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarAutorActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar por ID:");

        jBMostrarAutorBorrar.setText("Mostrar Autor");
        jBMostrarAutorBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarAutorBorrarActionPerformed(evt);
            }
        });

        jTFnombreAutorBorrar.setEditable(false);

        jTFfechaAutorBorrar.setEditable(false);

        jTFcodigoAutorBorrar.setEditable(false);

        jLabel13.setText("Nombre:");

        jLabel14.setText("Fecha de Nacimiento:");

        jLabel15.setText("Código Autor:");

        jTFautorBorrarPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFautorBorrarPorNombreActionPerformed(evt);
            }
        });

        jLabel7.setText("Buscar por Nombre:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(95, 95, 95)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFnombreAutorBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(jTFfechaAutorBorrar)
                            .addComponent(jTFcodigoAutorBorrar)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFautorBorrarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBMostrarAutorBorrar)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jBborrarAutor))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFautorBorrarPorID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(89, 89, 89))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTFautorBorrarPorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jTFautorBorrarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 55, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFnombreAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jBMostrarAutorBorrar))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jBborrarAutor)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFfechaAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTFcodigoAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
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
        JFMenuPrincipal menu = new JFMenuPrincipal();//cuando se seleccione la opcion menu esta se hara visible 
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void jBborrarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarAutorActionPerformed
//original
//        if(jTFnombreAutorBorrar.getText().length()==0){
//            JOptionPane.showMessageDialog(null, "Primero seleccione al autor a eliminar","Error",JOptionPane.WARNING_MESSAGE);
//        }else{
//            try {
//                int idAutor = Integer.parseInt(this.jTFcodigoAutorBorrar.getText());
//
//                if (existeAutor(idAutor)) {
//                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar? Se eliminarán los libros que el autor tenga asociados", "Confirmación", JOptionPane.YES_NO_OPTION);
//                    if (confirm == JOptionPane.YES_OPTION) {
//                        eliminarAutorEnBaseDeDatos(idAutor);
//                        mostrarTabla();
//                        this.jTFautorBorrarPorNombre.setText("");
//                        this.jTFautorBorrarPorID.setText("");
//
//                        JOptionPane.showMessageDialog(null, "Autor y sus libros asociados eliminados correctamente");
//
//                         Limpia los campos de texto
//                        jTFnombreAutorBorrar.setText("");
//                        jTFfechaAutorBorrar.setText("");
//                        jTFcodigoAutorBorrar.setText("");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "El autor con el id ingresado no existe");
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
//            } catch (ArrayIndexOutOfBoundsException ex) {
//            }
//        }
//mejoras
    try{
        if(autorSeleccionado != -1){
            //verifica si todos los libros han sido devueltos
            if(FaltaLibros(autorSeleccionado))
            {
                JOptionPane.showMessageDialog(null,"No se puede borrar Autor, hay libros prestados");
                return;
            }
            //confirma el borrado
            int respuesta= JOptionPane.showConfirmDialog(null, 
                    "¿Estas seguro que quieres borrar este Autor y sus libros asociados?",
                    "Confirmar Borrado",JOptionPane.YES_NO_OPTION);
            if(respuesta ==JOptionPane.YES_OPTION)//si selecciono que si borrara el autor junto con  sus libros
            {
                eliminarAutorEnBaseDeDatos(autorSeleccionado);//uso el metodo para borrar el autor guardado en el MySQL
                autorSeleccionado=-1;//restablece el idAutor luego de borrar
                mostrarTabla();//refresca la tabla
            }
        }else {
                JOptionPane.showMessageDialog(null,"El autor no existe","Error",JOptionPane.WARNING_MESSAGE);
        }
        //borra el registro por el idAutor
        filtrarTablaId("");
        filtrarTablaNombre("");
    } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites");
        }
    }//GEN-LAST:event_jBborrarAutorActionPerformed
private boolean FaltaLibros(int UnidadesDisponibles) {
        String sql = "SELECT COUNT(*) FROM Prestamo WHERE IdLibro = ?";
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, UnidadesDisponibles);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        return false;
    }
    private void jBmostrarAutorEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmostrarAutorEditarActionPerformed
        TableModel model = jTdatosAutor.getModel();//uso de  la tabla de datos
        int filaEncontrada = -1;//
        if(jTdatosAutor.getSelectedRow() != -1) {//condicionante si autor al mometo de seleccionar la fia sea distinta a la que se esta buscando
            // Si se ha seleccionado una fila, usar esa fila
            filaEncontrada = jTdatosAutor.convertRowIndexToModel(jTdatosAutor.getSelectedRow());
        } else if(jTdatosAutor.getRowCount() == 1) {
            // Si solo hay una fila, seleccionar esa fila
            filaEncontrada = jTdatosAutor.convertRowIndexToModel(0);
        } else if(this.jTFnombreAutorEditar.getText().length() == 0) {
            // Buscar por ID
            //se encarga de solo mostrar una fila
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 2).toString();
                if (idEnFila.equals(jTFIDAutorEditar.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        } else {
            // Buscar por nombre mostrando las filas con los datos relacionados
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String nombreEnFila = model.getValueAt(fila, 0).toString(); 
                if (nombreEnFila.equals(jTFnombreAutorEditar.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        }
        
        if(filaEncontrada != -1){
            jTFnombreAutorEditar.setText(model.getValueAt(filaEncontrada, 0).toString());    
            //convierte la fecha de objeto date a datechooser
            String fechaNacimientoStr = model.getValueAt(filaEncontrada, 1).toString();//ediccion de la fecha
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = df.parse(fechaNacimientoStr);//
            } catch (ParseException ex) {
                Logger.getLogger(JFAutor.class.getName()).log(Level.SEVERE, null, ex);//
            }
            jDfechaNacimientoEditar.setDate(date);//fija la fecha

            jTFIDAutorEditar.setText(model.getValueAt(filaEncontrada, 2).toString());//fija y muestra la idAutor
            try {
                autorSeleccionado = Integer.parseInt(jTFIDAutorEditar.getText());//guarda la id del autor al momento de seleccionar
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El ID del autor no es válido");
                autorSeleccionado = -1;//se restablece
            }
        }
        filtrarTablaId("");
        filtrarTablaNombre("");
//        jTFnombreAutorFiltrarEditar.setText("");
//        jTFcodigoAutorFiltrarEditar.setText("");
    }//GEN-LAST:event_jBmostrarAutorEditarActionPerformed

    private void jBinsertarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarAutorActionPerformed
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Autor(NombreAutor, FechaNacimiento ,IdAutor) VALUES (?,?,?)");//se inserta los datos de la tabla en el msql
            pps.setString(1,jTFnombreAutor.getText());//obtenemos los datos ingresados 
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//fort fech.
            java.util.Date date = jDateChooser.getDate();
            //uso del objeto calendario desplegable
            String fechaNacimiento = dateFormat.format(date);
            //String fechaNacimiento= new SimpleDateFormat("yyyy/MM/dd").format(date); forma equivalente
            pps.setString(2, fechaNacimiento);
            pps.setInt(3,Integer.parseInt(jTfIdAutor.getText()));//conversion de string
            //actualiz tabla
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Datos guardados");//mensaje de confirmacion
            
            Fecha fechaNacimientoAutor = new Fecha(fechaNacimiento);//objeto de Fecha
                    autor = new Autor(Long.parseLong(jTfIdAutor.getText()),jTFnombreAutor.getText(),fechaNacimientoAutor);
                    jTAautorActual.setText(autor.toString());
                    mostrarTabla();
                    //muestra la tabla actualizada
            //jTAlistaAutor.setText(listaAutor.toString());
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Autor ya registrado");//mensaje informativo
        }
    }//GEN-LAST:event_jBinsertarAutorActionPerformed

    private void jBactualizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarAutorActionPerformed
        try {
            String nuevoNombre = jTFnombreAutorEditar.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = jDfechaNacimientoEditar.getDate();//obtrngo la nueva fehca
            String nuevaFechaNacimiento = null;
            if (date != null) {
                nuevaFechaNacimiento = dateFormat.format(date);//fijo la fecha
            }

            // Aquí es donde actualizamos el autor en la base de datos
            actualizarAutorEnBaseDeDatos(autorSeleccionado, nuevoNombre, nuevaFechaNacimiento);
            mostrarTabla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: el ID del autor no es un número válido");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
        }
        jTFnombreAutorEditar.setText("");
        jTFIDAutorEditar.setText("");
        jTFcodigoAutorFiltrarEditar.setText("");
        jTFnombreAutorFiltrarEditar.setText("");
        jDfechaNacimientoEditar.setDate(null);
    }//GEN-LAST:event_jBactualizarAutorActionPerformed
    private boolean existeAutor(int idAutor) {
        String query = "SELECT*FROM Autor WHERE IdAutor = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, idAutor);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del autor: " + ex.toString());
        }
    return false;
    }
    private void actualizarAutorEnBaseDeDatos(int idAutor, String nuevoNombre, String nuevaFechaNacimiento) {//uso de los atributos del constructor
        String queryNombre = "UPDATE Autor SET NombreAutor = ? WHERE IdAutor = ?";
        String queryFecha = "UPDATE Autor SET FechaNacimiento = ? WHERE IdAutor = ?";
        //uso de las tablas en el mySQL
        try (PreparedStatement stNombre = cn.prepareStatement(queryNombre);
             PreparedStatement stFecha = cn.prepareStatement(queryFecha)) {//parametros actulizar

            stNombre.setString(1, nuevoNombre);
            stNombre.setInt(2, idAutor);
            stNombre.executeUpdate();//actualizacion de nombre

            stFecha.setString(1, nuevaFechaNacimiento);
            stFecha.setInt(2, idAutor);
            stFecha.executeUpdate();//actualizacion de fecha

            JOptionPane.showMessageDialog(null, "Autor actualizado con éxito");
            mostrarTabla();//modificada

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar autor en la base de datos: " + ex.toString());
        }
    }

    private void jBMostrarAutorBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarAutorBorrarActionPerformed
        TableModel model = jTdatosAutor.getModel();
        int filaEncontrada = -1;
        try {
            if(jTdatosAutor.getSelectedRow() != -1) {
                // Si se ha seleccionado una fila, usar esa fila
                filaEncontrada = jTdatosAutor.convertRowIndexToModel(jTdatosAutor.getSelectedRow());
            } else if(jTdatosAutor.getRowCount() == 1) {
                // Si solo hay una fila, seleccionar esa fila
                filaEncontrada = jTdatosAutor.convertRowIndexToModel(0);
            } else if(this.jTFnombreAutorBorrar.getText().length() == 0) {
                // Buscar por ID
                for (int fila = 0; fila < model.getRowCount(); fila++) {
                    String idEnFila = model.getValueAt(fila, 2).toString();
                    if (idEnFila.equals(jTFcodigoAutorBorrar.getText())) {
                        filaEncontrada = fila;
                        break;
                    }
                }
            } else {
                // Buscar por nombre
                for (int fila = 0; fila < model.getRowCount(); fila++) {
                    String nombreEnFila = model.getValueAt(fila, 0).toString(); 
                    if (nombreEnFila.equals(jTFnombreAutorBorrar.getText())) {
                        filaEncontrada = fila;
                        break;
                    }
                }
            }

            if(filaEncontrada != -1 && filaEncontrada < model.getRowCount()){
                jTFnombreAutorBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
                jTFfechaAutorBorrar.setText(model.getValueAt(filaEncontrada, 1).toString());
                jTFcodigoAutorBorrar.setText(model.getValueAt(filaEncontrada, 2).toString());     
                autorSeleccionado = Integer.parseInt(jTFcodigoAutorBorrar.getText());//obtengo todos los datos para borrar el autor
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.toString());
        }
        filtrarTablaId("");
        filtrarTablaNombre("");
    }//GEN-LAST:event_jBMostrarAutorBorrarActionPerformed

    private void jTFautorBorrarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFautorBorrarPorNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFautorBorrarPorNombreActionPerformed
    
    private void eliminarAutorEnBaseDeDatos(long idAutor) {
    try {
        // Primero, eliminar los libros asociados al autor
        String queryLibros = "DELETE FROM Libro WHERE IdAutor = ?";//seleccionando los datos de 
        //la tabla del mySQL para borrarlos
        try (PreparedStatement st = cn.prepareStatement(queryLibros)) {
            st.setLong(1, idAutor);//fija el dato que fue buscado con la idautor delos libros asociados
            st.executeUpdate();//actualiza los datos
        }

        // Luego, eliminar al autor
        String queryAutor = "DELETE FROM Autor WHERE IdAutor = ?";
        try (PreparedStatement st = cn.prepareStatement(queryAutor)) {
            st.setLong(1, idAutor);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Autor y Libros eliminados correctamente.");//mensaje informativo
        }
    } catch (SQLException ex) {
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
    private javax.swing.JButton jBMostrarAutorBorrar;
    private javax.swing.JButton jBactualizarAutor;
    private javax.swing.JButton jBborrarAutor;
    private javax.swing.JButton jBinsertarAutor;
    private javax.swing.JButton jBmostrarAutorEditar;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private com.toedter.calendar.JDateChooser jDfechaNacimientoEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTAautorActual;
    private javax.swing.JTextField jTFIDAutorEditar;
    private javax.swing.JTextField jTFautorBorrarPorID;
    private javax.swing.JTextField jTFautorBorrarPorNombre;
    private javax.swing.JTextField jTFcodigoAutorBorrar;
    private javax.swing.JTextField jTFcodigoAutorFiltrarEditar;
    private javax.swing.JTextField jTFfechaAutorBorrar;
    private javax.swing.JTextField jTFnombreAutor;
    private javax.swing.JTextField jTFnombreAutorBorrar;
    private javax.swing.JTextField jTFnombreAutorEditar;
    private javax.swing.JTextField jTFnombreAutorFiltrarEditar;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTextField jTfIdAutor;
    // End of variables declaration//GEN-END:variables
}
