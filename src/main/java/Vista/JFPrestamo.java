package Vista;

import Negocio.Fecha;
import Negocio.Prestamo;
import PConexion.Conexion;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    Fecha fechaPrestamo;
    private int idEstudianteSeleccionado = 0;
    private int idLibroSeleccionado = 0;
    
    public JFPrestamo() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        File file = new File("C:\\Users\\DELL\\OneDrive - Escuela Politécnica Nacional\\DANIEL\\EPN\\SEGUNDO SEMESTRE\\P\\WORKSPACE 2023B\\PROYECTO-MASTER\\ProyectoBimestral\\src\\main\\java\\Imagenes\\Prestamo.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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

    this.jTFnombreEstudianteDevolver.addKeyListener(new KeyAdapter() {
    public void keyReleased(KeyEvent e) {
        JTextField textField = (JTextField) e.getSource();
        String text = textField.getText();
        filtrarTablaPrestamoPorNombreEstudiante(text);
    }
    });   

    this.jTFnombreLibroDevolver.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaPrestamoPorNombreLibro(text);
        }
        });   

    mostrarTablaEstudiante();
    mostrarTablaLibro();
    mostrarTablaPrestamo();
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
    public void filtrarTablaEstudiantePorNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTestudiante.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTestudiante.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 0));
        }
    }   
    public void filtrarTablaEstudiantePorID(String query) {
        DefaultTableModel model = (DefaultTableModel) jTestudiante.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTestudiante.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 3));
        }
    }
 
    //LIBRO
    public void mostrarTablaLibro(){
        String sql = "SELECT * FROM Libro WHERE UnidadesDisponibles > 0";
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
    public void filtrarTablaLibroPorNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTlibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTlibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 0));
        }
    }
    public void filtrarTablaLibroPorID(String query) {
        DefaultTableModel model = (DefaultTableModel) jTlibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTlibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
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
        catch(SQLException | ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Error "+e.toString());
        }
    }
    public void filtrarTablaPrestamoPorNombreEstudiante(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
        }
    }
    public void filtrarTablaPrestamoPorNombreLibro(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 1));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jBsolicitarPrestamo = new javax.swing.JButton();
        jBlimpiarFecha = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jDfechaDevolucion = new com.toedter.calendar.JDateChooser();
        jPanel18 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTFnombreEstudianteDevolver = new javax.swing.JTextField();
        jTFnombreLibroDevolver = new javax.swing.JTextField();
        jBdevolverPrestamo = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTprestamo = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel11.setText("PRÉSTAMO");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiante"));

        jLabel12.setText("Búsqueda por Nombre de Estudiante:");

        jLabel13.setText("Búsqueda por Código Único:");

        jTFnombreEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreEstudianteKeyTyped(evt);
            }
        });

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

        jBaceptarEstudiante.setText("Seleccionar");
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
                        .addGap(234, 234, 234)
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

        jLabel14.setText("Búsqueda por Nombre de Libro:");

        jLabel15.setText("Búsqueda por Codigo Libro:");

        jLabel16.setText("Búsqueda por Código del Autor:");

        jTFnombreLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreLibroKeyTyped(evt);
            }
        });

        jTFcodigoLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFcodigoLibroKeyTyped(evt);
            }
        });

        jTFcodigoAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFcodigoAutorKeyTyped(evt);
            }
        });

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
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFnombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jBseleccionarLibro)
                                .addGap(90, 90, 90)))
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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addGap(409, 409, 409)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Selección de Datos", jPanel11);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solicitar Préstamo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        jBsolicitarPrestamo.setText("Solicitar Préstamo");
        jBsolicitarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsolicitarPrestamoActionPerformed(evt);
            }
        });

        jBlimpiarFecha.setText("Limpiar Fecha");
        jBlimpiarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarFechaActionPerformed(evt);
            }
        });

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Establecer la Fecha de Devolución"));

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
                .addGap(15, 15, 15)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jBlimpiarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jBsolicitarPrestamo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBsolicitarPrestamo)
                            .addComponent(jBlimpiarFecha))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Devolver Préstamo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 24))); // NOI18N

        jLabel21.setText("Búsqueda por Nombre del Libro:");

        jTFnombreEstudianteDevolver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreEstudianteDevolverKeyTyped(evt);
            }
        });

        jTFnombreLibroDevolver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreLibroDevolverKeyTyped(evt);
            }
        });

        jBdevolverPrestamo.setText("Devolver");
        jBdevolverPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdevolverPrestamoActionPerformed(evt);
            }
        });

        jLabel22.setText("Búsqueda por Nombre del Estudiante:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFnombreEstudianteDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFnombreLibroDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jBdevolverPrestamo)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFnombreLibroDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFnombreEstudianteDevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBdevolverPrestamo)
                    .addComponent(jLabel22))
                .addContainerGap(23, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos de Préstamo", jPanel2);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1037, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBlimpiarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarLibroActionPerformed
        this.jTFnombreLibro.setText("");
        this.jTFcodigoLibro.setText("");
        this.jTFcodigoAutor.setText("");
        quitarFiltrado(jTlibro);
    }//GEN-LAST:event_jBlimpiarLibroActionPerformed

    private void jBseleccionarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBseleccionarLibroActionPerformed
       if (jTlibro.getRowCount() == 1) {
            int idLibro = Integer.parseInt(jTlibro.getValueAt(0, 2).toString());
            JOptionPane.showMessageDialog(null, "Libro seleccionado correctamente ");
            this.idLibroSeleccionado = idLibro;
        } else if(jTlibro.getSelectedRow() != -1){
            int idLibro = Integer.parseInt(jTlibro.getValueAt(jTlibro.getSelectedRow(), 2).toString());
            JOptionPane.showMessageDialog(null, "Libro seleccionado correctamente ");
            this.idLibroSeleccionado = idLibro;            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Libro.");   
        }
    }//GEN-LAST:event_jBseleccionarLibroActionPerformed

    private void jBlimpiarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarEstudianteActionPerformed
        this.jTFnombreEstudiante.setText(null);
        this.jTFcodigoEstudiante.setText(null);
        quitarFiltrado(jTestudiante);
    }//GEN-LAST:event_jBlimpiarEstudianteActionPerformed

    private void jBaceptarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaceptarEstudianteActionPerformed
    if (jTestudiante.getRowCount() == 1  ) { 
            int idEstudiante = Integer.parseInt(jTestudiante.getValueAt(0, 3).toString()); 
            JOptionPane.showMessageDialog(null, "Estudiante seleccionado correctamente ");
            this.idEstudianteSeleccionado = idEstudiante;
        } else if(jTestudiante.getSelectedRow()!=-1) {
            int idEstudiante = Integer.parseInt(jTestudiante.getValueAt(jTestudiante.getSelectedRow(), 3).toString()); 
            JOptionPane.showMessageDialog(null, "Estudiante seleccionado correctamente ");
            this.idEstudianteSeleccionado = idEstudiante;            
            
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Estudiante");     
        }
    }//GEN-LAST:event_jBaceptarEstudianteActionPerformed

    private void jTFcodigoEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteKeyTyped
    char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
            }
    }//GEN-LAST:event_jTFcodigoEstudianteKeyTyped

    private void jTFcodigoEstudianteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcodigoEstudianteKeyReleased

    private void jTFcodigoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFcodigoEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcodigoEstudianteActionPerformed

    private void jBlimpiarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarFechaActionPerformed
        this.jDfechaDevolucion.setDate(null);
    }//GEN-LAST:event_jBlimpiarFechaActionPerformed

    private void jBsolicitarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsolicitarPrestamoActionPerformed
        Date fechaActual = new Date();
        Date fechaSeleccionada = jDfechaDevolucion.getDate();
        try {
            if (this.idEstudianteSeleccionado == 0) {
                throw new Exception("No se ha seleccionado un estudiante.");
            }
            if (this.idLibroSeleccionado == 0) {
                throw new Exception("No se ha seleccionado un libro.");
            }
            if (fechaSeleccionada == null) {
                throw new Exception("No se ha seleccionado una fecha.");
            }
            if (fechaSeleccionada.before(fechaActual)) {
                throw new Exception("La fecha de devolución no puede ser anterior a la fecha actual.");
            }
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setIdEstudiante(this.idEstudianteSeleccionado);
            nuevoPrestamo.setIdLibro(this.idLibroSeleccionado);
            nuevoPrestamo.setFechaDevolucion(new Fecha(formatter.format(fechaSeleccionada)));

            if (nuevoPrestamo.obtenerUnidadesLibro()) {
                JOptionPane.showMessageDialog(null, "Lo sentimos, no hay unidades disponibles de este libro.");
                return;
            }

            int idPrestamo = nuevoPrestamo.generarIdPrestamoUnico();
            nuevoPrestamo.setIdPrestamo(idPrestamo);
            nuevoPrestamo.añadirRegistro();
            nuevoPrestamo.reducirUnidadesLibro(this.idLibroSeleccionado);
            mostrarTablaPrestamo(); 
            quitarFiltrado(jTlibro);
            mostrarTablaLibro();
            quitarFiltrado(jTestudiante);
            this.jTFcodigoAutor.setText(null);
            this.jTFcodigoEstudiante.setText(null);
            this.jTFcodigoLibro.setText(null);
            this.jTFnombreEstudiante.setText(null);
            this.jTFnombreLibro.setText(null);
            this.idEstudianteSeleccionado=-1;
            this.idLibroSeleccionado=-1;
            JOptionPane.showMessageDialog(null, "El préstamo se ha realizado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jBsolicitarPrestamoActionPerformed

    private void jBdevolverPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdevolverPrestamoActionPerformed
        int selectedRow = jTprestamo.getSelectedRow();
        try{
            if (selectedRow != -1) {       
                try {        
                    int modelRow = jTprestamo.convertRowIndexToModel(selectedRow);                 
                    int idPrestamo = Integer.parseInt(jTprestamo.getModel().getValueAt(modelRow, 0).toString());
                    Prestamo prestamo = new Prestamo();
                    int idLibro = prestamo.obtenerIdLibro(idPrestamo);
                    prestamo.aumentarUnidadesLibro(idLibro);
                    prestamo.eliminarRegistro(idPrestamo);
                    JOptionPane.showMessageDialog(null, "El préstamo se ha devuelto correctamente.");
                    quitarFiltrado(jTprestamo);
                    mostrarTablaPrestamo(); 
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }      
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un préstamo para devolver.");
            }
            quitarFiltrado(jTlibro);
            quitarFiltrado(jTestudiante);
            this.jTFnombreEstudianteDevolver.setText(null);
            this.jTFnombreLibroDevolver.setText(null);           
            filtrarTablaPrestamoPorNombreEstudiante("");
            filtrarTablaPrestamoPorNombreLibro("");
        }catch(ArrayIndexOutOfBoundsException | HeadlessException | NumberFormatException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jBdevolverPrestamoActionPerformed

    private void jTFnombreEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreEstudianteKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreEstudianteKeyTyped

    private void jTFnombreLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreLibroKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreLibroKeyTyped

    private void jTFcodigoLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoLibroKeyTyped
    char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
            }
    }//GEN-LAST:event_jTFcodigoLibroKeyTyped

    private void jTFcodigoAutorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcodigoAutorKeyTyped
    char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
            }
    }//GEN-LAST:event_jTFcodigoAutorKeyTyped

    private void jTFnombreEstudianteDevolverKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreEstudianteDevolverKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreEstudianteDevolverKeyTyped

    private void jTFnombreLibroDevolverKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreLibroDevolverKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreLibroDevolverKeyTyped
   
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
    private javax.swing.JButton jBdevolverPrestamo;
    private javax.swing.JButton jBlimpiarEstudiante;
    private javax.swing.JButton jBlimpiarFecha;
    private javax.swing.JButton jBlimpiarLibro;
    private javax.swing.JButton jBseleccionarLibro;
    private javax.swing.JButton jBsolicitarPrestamo;
    private com.toedter.calendar.JDateChooser jDfechaDevolucion;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTFcodigoAutor;
    private javax.swing.JTextField jTFcodigoEstudiante;
    private javax.swing.JTextField jTFcodigoLibro;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTextField jTFnombreEstudianteDevolver;
    private javax.swing.JTextField jTFnombreLibro;
    private javax.swing.JTextField jTFnombreLibroDevolver;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTestudiante;
    private javax.swing.JTable jTlibro;
    private javax.swing.JTable jTprestamo;
    // End of variables declaration//GEN-END:variables
}
