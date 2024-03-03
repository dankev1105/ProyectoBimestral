package Vista;
import Negocio.Autor;
import Negocio.Fecha;
import PConexion.Conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    Autor autor;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    private int autorSeleccionado;
    public JFAutor() {
        initComponents();
        File file = new File("C:\\Users\\DELL\\OneDrive - Escuela Politécnica Nacional\\DANIEL\\EPN\\SEGUNDO SEMESTRE\\P\\WORKSPACE 2023B\\PROYECTO-MASTER\\ProyectoBimestral\\src\\main\\java\\Imagenes\\Autor.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());//
        setIconImage(icon.getImage());
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        this.setResizable(false);
        this.autorSeleccionado = -1;
        
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
        
        jTdatosAutor.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int filaSeleccionadaVista = jTdatosAutor.getSelectedRow();
        
        // Convierte el índice de fila de la vista al índice de fila en el modelo
        int filaSeleccionadaModelo = jTdatosAutor.convertRowIndexToModel(filaSeleccionadaVista);
        
        // Obtiene el modelo de datos de la tabla
        TableModel model = jTdatosAutor.getModel();
            String nombre = model.getValueAt(filaSeleccionadaModelo, 0).toString();
            String fechaNacimiento = model.getValueAt(filaSeleccionadaModelo, 1).toString();
            String idAutor = model.getValueAt(filaSeleccionadaModelo, 2).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Actualiza los campos de texto con los datos de la fila seleccionada
            int indexSelect = jTPautor.getSelectedIndex();
            switch (indexSelect)
            {
                case 0:
                case 1:
                    jTFnombreAutorEditar.setText(nombre);
                    try {
                        Date fecha = sdf.parse(fechaNacimiento);
                        jDfechaNacimientoEditar.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(JFEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jTFIDAutorEditar.setText(idAutor);
                    filtrarTablaId("");
                    filtrarTablaNombre("");
                    jTFnombreAutorEditar.setEditable(true);
                    jDfechaNacimientoEditar.setEnabled(true);
                    jTFIDAutorEditar.setEditable(true);
                    break;
                case 2:
                    jTFnombreAutorBorrar.setText(nombre);
                    jTFfechaAutorBorrar.setText(fechaNacimiento);
                    jTFcodigoAutorBorrar.setText(idAutor);
                    filtrarTablaId("");
                    filtrarTablaNombre("");
                    break;
            }}});
    }

    public void filtrarTablaNombre(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 0)); // Cambia el índice a la columna del nombre
        }
    }

    public void filtrarTablaId(String query) {
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
            tr.setRowFilter(null);
        } else {
            tr.setRowFilter(RowFilter.regexFilter(query, 2));
        }
    }

    public void mostrarTabla(){
        String sql = "SELECT * FROM Autor";
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
                datos[2]=String.valueOf(rs.getInt(3));
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
        jTPautor = new javax.swing.JTabbedPane();
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

        jLabel1.setText("Nombre del Autor:");

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
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfIdAutor)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jTFnombreAutor))
                        .addGap(58, 58, 58))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBinsertarAutor)
                        .addGap(146, 146, 146)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFnombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTfIdAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jBinsertarAutor)
                        .addGap(17, 17, 17)))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTPautor.addTab("Insertar", jPanel2);

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
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFIDAutorEditar)
                    .addComponent(jDfechaNacimientoEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jTFnombreAutorFiltrarEditar)
                    .addComponent(jTFnombreAutorEditar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(13, 13, 13)
                        .addComponent(jTFcodigoAutorFiltrarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBactualizarAutor)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addComponent(jBactualizarAutor)
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

        jTPautor.addTab("Editar", jPanel1);

        jBborrarAutor.setText("Borrar");
        jBborrarAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarAutorActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar por ID:");

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
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFnombreAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFfechaAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFcodigoAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFautorBorrarPorNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel6)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jBborrarAutor))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFautorBorrarPorID, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFautorBorrarPorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTFautorBorrarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFnombreAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFfechaAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTFcodigoAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jBborrarAutor)))
                .addGap(43, 43, 43))
        );

        jTPautor.addTab("Borrar", jPanel3);

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
        jTdatosAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTdatosAutorMouseClicked(evt);
            }
        });
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
                        .addComponent(jTPautor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(303, 303, 303))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTPautor, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        try{
            this.autorSeleccionado=Integer.parseInt(this.jTFcodigoAutorBorrar.getText());
            if(this.autorSeleccionado != -1){
                //verifica si todos los libros han sido devueltos
                if(verificaPrestamoLibro(this.autorSeleccionado)){
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
        } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites");
            }
            filtrarTablaId("");
            filtrarTablaNombre("");
    }//GEN-LAST:event_jBborrarAutorActionPerformed
    public void eliminarAutorEnBaseDeDatos(int idAutor) {
        try {
            String queryLibros = "DELETE FROM Libro WHERE IdAutor = ?";
            try (PreparedStatement st = cn.prepareStatement(queryLibros)) {
                st.setInt(1, idAutor);
                st.executeUpdate();
            }
            String queryAutor = "DELETE FROM Autor WHERE IdAutor = ?";
            try (PreparedStatement st = cn.prepareStatement(queryAutor)) {
                st.setInt(1, idAutor);
                st.executeUpdate();
                JOptionPane.showMessageDialog(null, "Autor y Libros eliminados correctamente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Libro que contiene el autor está prestado.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "El Libro que contiene el autor está prestado.");
        }
    }
    
    public boolean verificaPrestamoLibro(int UnidadesDisponibles) {
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
            JOptionPane.showMessageDialog(null, "El Libro que contiene el autor está prestado." );
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error de índice de array: " + e.toString());
        }
        return false;
    }
    private void jBinsertarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarAutorActionPerformed
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO Autor(NombreAutor, FechaNacimiento ,IdAutor) VALUES (?,?,?)");
            pps.setString(1,jTFnombreAutor.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = jDateChooser.getDate();
            String fechaNacimiento = dateFormat.format(date);
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

    private void jBactualizarAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarAutorActionPerformed
        try {
            String nuevoNombre = jTFnombreAutorEditar.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = jDfechaNacimientoEditar.getDate();
            String nuevaFechaNacimiento = null;
            if (date != null) {
                nuevaFechaNacimiento = dateFormat.format(date);
            }

            // Aquí es donde actualizamos el autor en la base de datos
            actualizarAutorEnBaseDeDatos(autorSeleccionado, nuevoNombre, nuevaFechaNacimiento);
            filtrarTablaId("");
            filtrarTablaNombre("");
            mostrarTabla();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: el ID del autor no es un número válido");
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        jTFnombreAutorEditar.setText("");
        jTFIDAutorEditar.setText("");
        jTFcodigoAutorFiltrarEditar.setText("");
        jTFnombreAutorFiltrarEditar.setText("");
        jDfechaNacimientoEditar.setDate(null);
    }//GEN-LAST:event_jBactualizarAutorActionPerformed

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

            JOptionPane.showMessageDialog(null, "Autor actualizado con éxito");
            mostrarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar autor en la base de datos: " + ex.toString());
        }
    }

    private void jTFautorBorrarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFautorBorrarPorNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFautorBorrarPorNombreActionPerformed

    private void jTdatosAutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTdatosAutorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTdatosAutorMouseClicked

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
    private javax.swing.JButton jBactualizarAutor;
    private javax.swing.JButton jBborrarAutor;
    private javax.swing.JButton jBinsertarAutor;
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
    private javax.swing.JTabbedPane jTPautor;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTextField jTfIdAutor;
    // End of variables declaration//GEN-END:variables
}
