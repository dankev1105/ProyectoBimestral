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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JFPrestamo extends javax.swing.JFrame {
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    private int idEstudianteSeleccionado;
    private int idLibroSeleccionado;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    Fecha fechaPrestamo = new Fecha(formatter.format(date));
    Fecha fechaDevolucion;

    public JFPrestamo() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());

        // Inicializa fechaPrestamo con la fecha actual
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.fechaPrestamo = new Fecha(formatter.format(date));

        // Añade 30 días a la fecha actual para la fecha de devolución
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 30);
        date = c.getTime();
        this.fechaDevolucion = new Fecha(formatter.format(date));    
        
    this.jTFnombreEstudiante.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaEstudiantePorNombre(text);
        }
    });   
    this.jTFidEstudiante.addKeyListener(new KeyAdapter() {
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
    

    this.jTFcodigoLibroEliminar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaPrestamo(text);
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
        String sql = "SELECT*FROM Prestamo";
        Statement st;
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Prestamo");
        model.addColumn("ID Libro");
        model.addColumn("ID Estudiante");
        model.addColumn("Fecha Prestamo");
        model.addColumn("Fecha Devolucion");
        jTprestamo.setModel(model);
        String [] datos = new String [5];
            try{
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=String.valueOf(rs.getInt(1));
                datos[1]=String.valueOf(rs.getInt(2));
                datos[2]=String.valueOf(rs.getInt(3));
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                model.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    }
    public void filtrarTablaPrestamo(String query) {
        DefaultTableModel model = (DefaultTableModel) jTprestamo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTprestamo.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query,1 )); 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFnombreEstudiante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFidEstudiante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTestudiante = new javax.swing.JTable();
        jBaceptarEstudiante = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFnombreLibro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFcodigoLibro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTlibro = new javax.swing.JTable();
        jBseleccionarLibro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFcodigoAutor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTprestamo = new javax.swing.JTable();
        jBsolicitarPrestamo = new javax.swing.JButton();
        jTFcodigoLibro1 = new javax.swing.JTextField();
        jTFcodigoLibro2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTFcodigoLibroEliminar = new javax.swing.JTextField();
        jBeliminarRegistro = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre de Estudiante:");

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
        jScrollPane1.setViewportView(jTestudiante);

        jBaceptarEstudiante.setText("Aceptar");
        jBaceptarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaceptarEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFnombreEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jTFidEstudiante))
                .addGap(115, 115, 115)
                .addComponent(jBaceptarEstudiante)
                .addGap(106, 106, 106))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFnombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jBaceptarEstudiante)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFidEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Estudiante", jPanel2);

        jLabel2.setText("Nombre de Libro:");

        jLabel4.setText("Codigo Libro:");

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
        jScrollPane2.setViewportView(jTlibro);

        jBseleccionarLibro.setText("Seleccionar");
        jBseleccionarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBseleccionarLibroActionPerformed(evt);
            }
        });

        jLabel5.setText("Código del Autor:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFcodigoLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jTFnombreLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jTFcodigoAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addGap(145, 145, 145)
                .addComponent(jBseleccionarLibro)
                .addGap(107, 107, 107))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFnombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFcodigoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBseleccionarLibro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFcodigoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Libro", jPanel3);

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
        jScrollPane3.setViewportView(jTprestamo);

        jBsolicitarPrestamo.setText("Solicitar");
        jBsolicitarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsolicitarPrestamoActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Eliminar Préstamo"));

        jLabel6.setText("Código del Libro:");

        jBeliminarRegistro.setText("Eliminar");
        jBeliminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeliminarRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTFcodigoLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jBeliminarRegistro)
                        .addGap(32, 32, 32))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jBeliminarRegistro))
                .addGap(18, 18, 18)
                .addComponent(jTFcodigoLibroEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jBsolicitarPrestamo)
                        .addGap(0, 98, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(jTFcodigoLibro1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addGap(238, 238, 238)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(248, 248, 248)
                    .addComponent(jTFcodigoLibro2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addGap(228, 228, 228)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jBsolicitarPrestamo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(261, 261, 261)
                    .addComponent(jTFcodigoLibro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(302, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(311, Short.MAX_VALUE)
                    .addComponent(jTFcodigoLibro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(252, 252, 252)))
        );

        jTabbedPane1.addTab("Carrito", jPanel1);

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel10.setText("PRÉSTAMO");

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
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(12, 12, 12)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTFidEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidEstudianteActionPerformed

    }//GEN-LAST:event_jTFidEstudianteActionPerformed

    private void jTFidEstudianteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyReleased

    }//GEN-LAST:event_jTFidEstudianteKeyReleased

    private void jTFidEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyTyped

    }//GEN-LAST:event_jTFidEstudianteKeyTyped

    private void jBaceptarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaceptarEstudianteActionPerformed
        if (jTestudiante.getRowCount() == 1) { 
            int idEstudiante = Integer.parseInt(jTestudiante.getValueAt(0, 3).toString()); 
            JOptionPane.showMessageDialog(null, "Estudiante seleccionado correctamente. ID: " + idEstudiante);
            this.idEstudianteSeleccionado = idEstudiante;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, filtra la tabla hasta que quede un solo estudiante."); 
        }
    }//GEN-LAST:event_jBaceptarEstudianteActionPerformed

    private void jBseleccionarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBseleccionarLibroActionPerformed
        if (jTlibro.getRowCount() == 1) { 
            int idLibro = Integer.parseInt(jTlibro.getValueAt(0, 2).toString());
            JOptionPane.showMessageDialog(null, "Libro seleccionado correctamente. ID: " + idLibro);
            this.idLibroSeleccionado = idLibro;

            if (this.idEstudianteSeleccionado != 0 && this.idLibroSeleccionado != 0) {
                Prestamo nuevoPrestamo = new Prestamo();
                nuevoPrestamo.setIdEstudiante(this.idEstudianteSeleccionado);
                nuevoPrestamo.setIdLibro(this.idLibroSeleccionado);

                // Calcula la fecha de devolución (30 días después de la fecha actual)
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 30);
                Date date = c.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                nuevoPrestamo.setFechaDevolucion(new Fecha(formatter.format(date)));

                int idPrestamo = generarIdPrestamoUnico();
                nuevoPrestamo.setIdPrestamo(idPrestamo);
                nuevoPrestamo.añadirRegistro();
                mostrarTablaPrestamo(); 
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un estudiante."); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, filtra la tabla hasta que quede un solo libro."); 
        }
    }//GEN-LAST:event_jBseleccionarLibroActionPerformed
    private int generarIdPrestamoUnico() {
        Random rand = new Random();
        Prestamo prs = new Prestamo();
        int idPrestamo = rand.nextInt(1000000);
        while (prs.prestamoExiste(idPrestamo)) {
            idPrestamo = rand.nextInt(1000000);
        }
        return idPrestamo;
    }
    private void jBsolicitarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsolicitarPrestamoActionPerformed
      if (isTablaPrestamoEmpty()) {
            JOptionPane.showMessageDialog(null, "La tabla está vacía.");
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo añadido exitosamente.");
            limpiarTablaPrestamo();
            mostrarTablaPrestamo();
        }
    }//GEN-LAST:event_jBsolicitarPrestamoActionPerformed

    private boolean isTablaPrestamoEmpty() {
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

    private void jBeliminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeliminarRegistroActionPerformed
        String idLibroIngresado = jTFcodigoLibroEliminar.getText();
        if (idLibroIngresado != null && !idLibroIngresado.isEmpty()) {
            int idLibro = Integer.parseInt(idLibroIngresado); 
            Prestamo prestamoSeleccionado = new Prestamo();
            prestamoSeleccionado.setIdLibro(idLibro);
            if(!isTablaPrestamoEmpty()){
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar todos los registros de préstamos para el libro con ID: " + idLibro + "?", "Confirmación", JOptionPane.YES_NO_OPTION); // Pregunta al usuario si está seguro de eliminar
                if (confirmacion == JOptionPane.YES_OPTION) {
                    prestamoSeleccionado.eliminarRegistroDuplicado();

                    JOptionPane.showMessageDialog(null, "Registros de préstamos eliminados exitosamente.");
                    mostrarTablaPrestamo(); 
                }    
            }           
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID de libro válido.");
        }
    }//GEN-LAST:event_jBeliminarRegistroActionPerformed


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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBaceptarEstudiante;
    private javax.swing.JButton jBeliminarRegistro;
    private javax.swing.JButton jBseleccionarLibro;
    private javax.swing.JButton jBsolicitarPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFcodigoAutor;
    private javax.swing.JTextField jTFcodigoLibro;
    private javax.swing.JTextField jTFcodigoLibro1;
    private javax.swing.JTextField jTFcodigoLibro2;
    private javax.swing.JTextField jTFcodigoLibroEliminar;
    private javax.swing.JTextField jTFidEstudiante;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTextField jTFnombreLibro;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTestudiante;
    private javax.swing.JTable jTlibro;
    private javax.swing.JTable jTprestamo;
    // End of variables declaration//GEN-END:variables
}
