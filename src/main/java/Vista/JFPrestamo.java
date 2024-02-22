package Vista;

import Negocio.Fecha;
import Negocio.Prestamo;
import PConexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JFPrestamo extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    Fecha fechaPrestamo = new Fecha(formatter.format(date));
    private int idEstudianteSeleccionado;
    private int idLibroSeleccionado;
    public JFPrestamo() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());

        // Inicializa fechaPrestamo con la fecha actual
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.fechaPrestamo = new Fecha(formatter.format(date));   
        
    this.jTFnombreEstudiante.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaEstudiantePorNombre(text);
        }
    });   
    this.jTFcodigoEstudiante.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaEstudiantePorID(text);
        }
    });
    
        
    this.jTFnombreLibro.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaLibroPorNombre(text);
        }
    });
    this.jTFcodigoLibro.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaLibroPorID(text);
        }
    });       
    this.jTFcodigoAutor.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaLibroPorAutor(text);
        }
    });

    mostrarTablaEstudiante();
    mostrarTablaLibro();
    mostrarTablaPrestamo();
    }
   
    public void filtrarTablaEstudiantePorNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTestudiante.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTestudiante.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 0)); // Assuming 'Nombre' is at column 0
        }
    }
    
    public void filtrarTablaEstudiantePorID(String query) {
        DefaultTableModel model = (DefaultTableModel) jTestudiante.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTestudiante.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3)); // Assuming 'ID' is at column 3
        }
    }
    
    public void mostrarTablaEstudiante(){
        String sql = "SELECT*FROM Estudiante";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Fecha de Nacimiento");
        model.addColumn("Correo institucional");
        model.addColumn("ID");
        jTestudiante.setModel(model);
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
    
    //LIBRO
    public void filtrarTablaLibroPorNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTlibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTlibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 0)); // Assuming 'Nombre' is at column 0
        }
    }
    
    public void filtrarTablaLibroPorID(String query) {
        DefaultTableModel model = (DefaultTableModel) jTlibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTlibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2)); // Assuming 'ID' is at column 3
        }
    }
    
    public void mostrarTablaLibro(){
        String sql = "SELECT * FROM Libro WHERE UnidadesDisponibles > 0"; // Modifica la consulta SQL para que solo seleccione los libros con unidades mayores a cero
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre de Libro");
        model.addColumn("Género");
        model.addColumn("ID Libro");
        model.addColumn("ID Autor");
        model.addColumn("Unidades Disponibles");
        jTlibro.setModel(model);
        String [] datos = new String [5];
        try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=String.valueOf(rs.getInt(3));
                datos[3]=String.valueOf(rs.getInt(4));
                datos[4]=String.valueOf(rs.getInt(5));
                model.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }

    public void filtrarTablaLibroPorAutor(String query) {
        DefaultTableModel model = (DefaultTableModel) jTlibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTlibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3));
        }
    }

    //PRESTAMO
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTFnombreEstudiante = new javax.swing.JTextField();
        jTFcodigoEstudiante = new javax.swing.JTextField();
        jBaceptarEstudiante = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTestudiante = new javax.swing.JTable();
        jBlimpiarEstudiante = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTFnombreLibro = new javax.swing.JTextField();
        jTFcodigoLibro = new javax.swing.JTextField();
        jTFcodigoAutor = new javax.swing.JTextField();
        jBseleccionarLibro = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTlibro = new javax.swing.JTable();
        jBlimpiarLibro = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTprestamo = new javax.swing.JTable();
        jBsolicitarPrestamo = new javax.swing.JButton();
        jBnuevoPrestamo = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jDfechaDevolucion = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jBdevolverRegistro = new javax.swing.JButton();
        jBeliminarRegistro = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel11.setText("PRÉSTAMO");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiante"));

        jLabel12.setText("Nombre de Estudiante:");

        jLabel13.setText("Código Único:");

        jTFcodigoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFcodigoEstudianteActionPerformed(evt);
            }
        });
        jTFcodigoEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFcodigoEstudianteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFcodigoEstudianteKeyTyped(evt);
            }
        });

        jBaceptarEstudiante.setText("Aceptar");
        jBaceptarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaceptarEstudianteActionPerformed(evt);
            }
        });

        jTestudiante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTestudiante);

        jBlimpiarEstudiante.setText("Limpiar");
        jBlimpiarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFnombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTFcodigoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBaceptarEstudiante)
                        .addGap(138, 138, 138)
                        .addComponent(jBlimpiarEstudiante)
                        .addGap(283, 283, 283))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFnombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTFcodigoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBaceptarEstudiante)
                    .addComponent(jBlimpiarEstudiante))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Libro"));

        jLabel14.setText("Nombre de Libro:");

        jLabel15.setText("Codigo Libro:");

        jLabel16.setText("Código del Autor:");

        jBseleccionarLibro.setText("Seleccionar");
        jBseleccionarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBseleccionarLibroActionPerformed(evt);
            }
        });

        jTlibro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTlibro);

        jBlimpiarLibro.setText("Limpiar");
        jBlimpiarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jBseleccionarLibro)
                                .addGap(22, 22, 22))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFnombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jBlimpiarLibro))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jTFcodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFcodigoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFnombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTFcodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTFcodigoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBseleccionarLibro)
                    .addComponent(jBlimpiarLibro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Préstamo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

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

        jBsolicitarPrestamo.setText("Solicitar Préstamo");
        jBsolicitarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsolicitarPrestamoActionPerformed(evt);
            }
        });

        jBnuevoPrestamo.setText("Nuevo Préstamo");
        jBnuevoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnuevoPrestamoActionPerformed(evt);
            }
        });

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Devolución"));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDfechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jDfechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBsolicitarPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBnuevoPrestamo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jBsolicitarPrestamo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBnuevoPrestamo))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Otras Opciones"));
        jPanel1.setToolTipText("");

        jBdevolverRegistro.setText("Devolver");
        jBdevolverRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdevolverRegistroActionPerformed(evt);
            }
        });

        jBeliminarRegistro.setText("Eliminar Préstamo");
        jBeliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeliminarRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jBdevolverRegistro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBeliminarRegistro)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jBdevolverRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jBeliminarRegistro)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(384, 384, 384)
                        .addComponent(jLabel11)))
                .addGap(38, 86, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(653, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel11);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBeliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeliminarRegistroActionPerformed
        JFEliminacionPrestamo eliminacionPrestamoVentana = new JFEliminacionPrestamo();
        eliminacionPrestamoVentana.setVisible(true);
    }//GEN-LAST:event_jBeliminarRegistroActionPerformed

    private void jBlimpiarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarLibroActionPerformed
        this.jTFnombreLibro.setText("");
        this.jTFcodigoLibro.setText("");
        this.jTFcodigoAutor.setText("");
    }//GEN-LAST:event_jBlimpiarLibroActionPerformed

    private void jBseleccionarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBseleccionarLibroActionPerformed
       if (jTlibro.getRowCount() == 1) {
            int idLibro = Integer.parseInt(jTlibro.getValueAt(0, 2).toString());
            JOptionPane.showMessageDialog(null, "Libro seleccionado correctamente. ID: " + idLibro);
            this.idLibroSeleccionado = idLibro;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, filtra la tabla hasta que quede un solo libro."); 
        }
    }//GEN-LAST:event_jBseleccionarLibroActionPerformed

    private void jBlimpiarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarEstudianteActionPerformed
        this.jTFnombreEstudiante.setText("");
        this.jTFcodigoEstudiante.setText("");
    }//GEN-LAST:event_jBlimpiarEstudianteActionPerformed

    private void jBaceptarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaceptarEstudianteActionPerformed
    if (jTestudiante.getRowCount() == 1) { 
            int idEstudiante = Integer.parseInt(jTestudiante.getValueAt(0, 3).toString()); 
            JOptionPane.showMessageDialog(null, "Estudiante seleccionado correctamente. ID: " + idEstudiante);
            this.idEstudianteSeleccionado = idEstudiante;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, filtra la tabla hasta que quede un solo estudiante."); 
        }
    }//GEN-LAST:event_jBaceptarEstudianteActionPerformed

    private void jTFcodigoEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcodigoEstudianteKeyTyped

    private void jTFcodigoEstudianteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcodigoEstudianteKeyReleased

    private void jTFcodigoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcodigoEstudianteActionPerformed

    private void jBdevolverRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdevolverRegistroActionPerformed
        JFDevolucionPrestamo devolucionVentana = new JFDevolucionPrestamo();
        devolucionVentana.setVisible(true);
    }//GEN-LAST:event_jBdevolverRegistroActionPerformed

    private void jBnuevoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnuevoPrestamoActionPerformed
        this.jTFnombreEstudiante.setText("");
        this.jTFcodigoEstudiante.setText("");
        this.jTFnombreLibro.setText("");
        this.jTFcodigoLibro.setText("");
        this.jTFcodigoAutor.setText("");

        quitarFiltrado(jTlibro);
        quitarFiltrado(jTprestamo);
        quitarFiltrado(jTestudiante);
        this.jDfechaDevolucion.setDate(null);
        limpiarTablaPrestamo();
    }//GEN-LAST:event_jBnuevoPrestamoActionPerformed

    private void jBsolicitarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsolicitarPrestamoActionPerformed
        if (tablaVacia()) {
            JOptionPane.showMessageDialog(null, "La tabla está vacía. Por favor, selecciona un libro y un estudiante.");
            return;
        }
        if (this.idEstudianteSeleccionado == 0 || this.idLibroSeleccionado == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un estudiante y un libro."); 
            return;
        }
        Date fechaActual = new Date();
        jDfechaDevolucion.setMinSelectableDate(fechaActual);
        Date fechaSeleccionada = jDfechaDevolucion.getDate();
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha de devolución."); 
            return;
        }
        try {
            if (fechaSeleccionada.before(fechaActual)) {
                throw new Exception("La fecha de devolución no puede ser anterior a la fecha actual.");
            }
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setIdEstudiante(this.idEstudianteSeleccionado);
            nuevoPrestamo.setIdLibro(this.idLibroSeleccionado);
            nuevoPrestamo.setFechaDevolucion(new Fecha(formatter.format(fechaSeleccionada)));

            int idPrestamo = nuevoPrestamo.generarIdPrestamoUnico();
            nuevoPrestamo.setIdPrestamo(idPrestamo);
            nuevoPrestamo.añadirRegistro();
            nuevoPrestamo.reducirUnidadesLibro(this.idLibroSeleccionado);
            mostrarTablaPrestamo(); 
            JOptionPane.showMessageDialog(null, "El préstamo se ha realizado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jBsolicitarPrestamoActionPerformed

    private boolean tablaVacia() {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        return model.getRowCount() == 0;
    }

    private void limpiarTablaPrestamo() {
        DefaultTableModel modelo = (DefaultTableModel) jTprestamo.getModel();
        int filas = modelo.getRowCount();
        if (filas > 0) {
            for (int i = filas - 1; i >= 0; i--) {
                modelo.removeRow(i);
            }
        }
    }
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
            java.util.logging.Logger.getLogger(JFPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBaceptarEstudiante;
    private javax.swing.JButton jBdevolverRegistro;
    private javax.swing.JButton jBeliminarRegistro;
    private javax.swing.JButton jBlimpiarEstudiante;
    private javax.swing.JButton jBlimpiarLibro;
    private javax.swing.JButton jBnuevoPrestamo;
    private javax.swing.JButton jBseleccionarLibro;
    private javax.swing.JButton jBsolicitarPrestamo;
    private com.toedter.calendar.JDateChooser jDfechaDevolucion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTFcodigoAutor;
    private javax.swing.JTextField jTFcodigoEstudiante;
    private javax.swing.JTextField jTFcodigoLibro;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTextField jTFnombreLibro;
    private javax.swing.JTable jTestudiante;
    private javax.swing.JTable jTlibro;
    private javax.swing.JTable jTprestamo;
    // End of variables declaration//GEN-END:variables
}
