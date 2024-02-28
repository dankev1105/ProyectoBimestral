package Vista;

import Negocio.Autor;
import Negocio.Fecha;
import Negocio.Libro;
import PConexion.Conexion;
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
import javax.swing.DefaultComboBoxModel;
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
    Fecha fecha;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    private int idLibroSeleccionado;
    
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
            filtrarTablaNombre(text);  
            vaciarCamposNombreE();    
        }}); 
        this.jTFlibroEditarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            vaciarCamposIdE();
            
        }}); 
        this.jTFlibroBorrarFiltrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);
            vaciarCamposNombreB();
        }});
        this.jTFlibroBorrarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            vaciarCamposIdB();
        }});
        
        this.jTFidAutor.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaIdAutor(text);
            
        }});
        
    mostrarTabla();
    }
    
     public void vaciarCamposNombreE(){
        this.jTFlibroBorrarFiltrarNombre.setText("");
    }
    public void vaciarCamposNombreB(){
        this.jTFlibroEditarFiltrarNombre.setText("");   
    }  
    public void vaciarCamposIdE(){
        this.jTFlibroBorrarFiltrarID.setText("");    
    }  
    public void vaciarCamposIdB(){
        this.jTFlibroEditarFiltrarID.setText("");    
    }  
     public void filtrarTablaNombre(String query){
        this.jTFlibroBorrarFiltrarID.setText("");
        this.jTFlibroEditarFiltrarID.setText("");
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
    public void filtrarTablaId(String query) {
        this.jTFlibroEditarFiltrarNombre.setText("");
        this.jTFlibroBorrarFiltrarNombre.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosLibro.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jTdatosLibro.setRowSorter(tr);

        if (query.trim().length() == 0) {
        // Si el texto de búsqueda está vacío, elimina el filtro y muestra todos los datos
        tr.setRowFilter(null);
        } else {
        // Aplica el filtro solo si el texto de búsqueda no está vacío
        tr.setRowFilter(RowFilter.regexFilter(query, 2));
    }
    }  
        public void filtrarTablaIdAutor(String query) {
      
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jTdatosAutor.setRowSorter(tr);

        if (query.trim().length() == 0) {
        // Si el texto de búsqueda está vacío, elimina el filtro y muestra todos los datos
        tr.setRowFilter(null);
        } else {
        // Aplica el filtro solo si el texto de búsqueda no está vacío
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
                datos[2]=String.valueOf(rs.getInt(3));
                datos[3]=String.valueOf(rs.getInt(4));
                datos[4]=String.valueOf(rs.getInt(5));
                model1.addRow(datos);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error."+e.toString());
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
            JOptionPane.showMessageDialog(null, "Error."+e.toString());
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFidLibro = new javax.swing.JTextField();
        jTFunidadesLibro = new javax.swing.JTextField();
        jCBgeneroLibros = new javax.swing.JComboBox<>();
        jTFgeneroLibro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTFidAutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFnombreAutor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFfechaNacimientoAutor = new javax.swing.JTextField();
        jBinsertarLibro = new javax.swing.JButton();
        jBlimpiarInsertarLibro = new javax.swing.JButton();
        jBMostrarDatosAutor = new javax.swing.JButton();
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
        jBmostrarLibro = new javax.swing.JButton();
        jBborrarEstudiante = new javax.swing.JButton();
        jBlimpiarLibrosBorrar = new javax.swing.JButton();
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
        jBlimpiarLibroEditar = new javax.swing.JButton();
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

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jTAlibroActual.setEditable(false);
        jTAlibroActual.setColumns(20);
        jTAlibroActual.setRows(5);
        jTAlibroActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Libro Actual"));
        jScrollPane2.setViewportView(jTAlibroActual);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Libro:"));

        jLabel1.setText("Titulo del Libro:");

        jTFtituloLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFtituloLibroKeyTyped(evt);
            }
        });

        jLabel2.setText("Género:");

        jLabel3.setText("Unidades del Libro:");

        jLabel4.setText("Id Libro:");

        jTFidLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFidLibroKeyTyped(evt);
            }
        });

        jTFunidadesLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFunidadesLibroKeyTyped(evt);
            }
        });

        jCBgeneroLibros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ficción", "No ficción", "Misterio", "Ciencia ficción", "Fantasía", "Romance", "Aventura", "Drama", "Terror", "Poesía", "Biografía", "Historia", "Ciencia", "Viajes", "Autoayuda", "Misterio", "Humor", "Suspenso", "Crimen", "Ensayo" }));
        jCBgeneroLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCBgeneroLibrosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCBgeneroLibrosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCBgeneroLibrosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCBgeneroLibrosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCBgeneroLibrosMouseReleased(evt);
            }
        });
        jCBgeneroLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBgeneroLibrosActionPerformed(evt);
            }
        });

        jTFgeneroLibro.setEditable(false);

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
                    .addComponent(jTFidLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jTFtituloLibro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFunidadesLibro)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jCBgeneroLibros, 0, 1, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jTFgeneroLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jCBgeneroLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jTFidAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFidAutorKeyTyped(evt);
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel8))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFnombreAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                    .addComponent(jTFfechaNacimientoAutor)
                    .addComponent(jTFidAutor))
                .addGap(27, 27, 27))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFnombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFfechaNacimientoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFidAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(34, 34, 34))
        );

        jBinsertarLibro.setText("Insertar Libro");
        jBinsertarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarLibroActionPerformed(evt);
            }
        });

        jBlimpiarInsertarLibro.setText("Limpiar los campos");
        jBlimpiarInsertarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarInsertarLibroActionPerformed(evt);
            }
        });

        jBMostrarDatosAutor.setText("Mostrar Datos Autor");
        jBMostrarDatosAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarDatosAutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBinsertarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jBlimpiarInsertarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jBMostrarDatosAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBinsertarLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBMostrarDatosAutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBlimpiarInsertarLibro))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel17.setText("Titulo del Libro:");

        jTFtituloBorrar.setEditable(false);

        jLabel18.setText("Genero:");

        jLabel19.setText("Unidades del Libro:");

        jLabel20.setText("Id Libro:");

        jTFgeneroBorrar.setEditable(false);

        jTFunidadesBorrar.setEditable(false);

        jTFidLibroBorrar.setEditable(false);

        jTFfiltrarPorNombre1.setText("Filtrar por Nombre:");

        jLabel22.setText("Filtrar por ID:");

        jTFlibroBorrarFiltrarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFlibroBorrarFiltrarNombreActionPerformed(evt);
            }
        });
        jTFlibroBorrarFiltrarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFlibroBorrarFiltrarNombreKeyTyped(evt);
            }
        });

        jTFlibroBorrarFiltrarID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFlibroBorrarFiltrarIDKeyTyped(evt);
            }
        });

        jBmostrarLibro.setText("Mostrar Libro");
        jBmostrarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmostrarLibroActionPerformed(evt);
            }
        });

        jBborrarEstudiante.setText("Borrar");
        jBborrarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarEstudianteActionPerformed(evt);
            }
        });

        jBlimpiarLibrosBorrar.setText("Limpiar los campos");
        jBlimpiarLibrosBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarLibrosBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBmostrarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                        .addGap(78, 78, 78))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFfiltrarPorNombre1)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTFunidadesBorrar)
                                    .addComponent(jTFtituloBorrar)
                                    .addComponent(jTFlibroBorrarFiltrarNombre)
                                    .addComponent(jTFidLibroBorrar)
                                    .addComponent(jTFgeneroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel20))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel22)
                                .addGap(35, 35, 35)
                                .addComponent(jTFlibroBorrarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBborrarEstudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBlimpiarLibrosBorrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFlibroBorrarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFfiltrarPorNombre1)
                    .addComponent(jLabel22)
                    .addComponent(jTFlibroBorrarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFtituloBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTFgeneroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFunidadesBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTFidLibroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jBmostrarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBborrarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBlimpiarLibrosBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Borrar", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jBeditarLibro.setText("Editar");
        jBeditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarLibroActionPerformed(evt);
            }
        });

        jLabel9.setText("Titulo del Libro:");

        jTFtituloEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFtituloEditarKeyTyped(evt);
            }
        });

        jLabel12.setText("Género:");

        jLabel13.setText("Unidades del Libro:");

        jLabel14.setText("Id Libro:");

        jTFgeneroEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFgeneroEditarKeyTyped(evt);
            }
        });

        jTFunidadesEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFunidadesEditarKeyTyped(evt);
            }
        });

        jTFidLibroEditar.setEditable(false);

        jTFfiltrarPorNombre.setText("Filtrar por Nombre:");

        jLabel16.setText("Filtrar por ID:");

        jTFlibroEditarFiltrarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFlibroEditarFiltrarNombreActionPerformed(evt);
            }
        });
        jTFlibroEditarFiltrarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFlibroEditarFiltrarNombreKeyTyped(evt);
            }
        });

        jTFlibroEditarFiltrarID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFlibroEditarFiltrarIDKeyTyped(evt);
            }
        });

        jBactualizarLibro.setText("Actualizar");
        jBactualizarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarLibroActionPerformed(evt);
            }
        });

        jBlimpiarLibroEditar.setText("Limpiar los campos");
        jBlimpiarLibroEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarLibroEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jTFfiltrarPorNombre))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTFunidadesEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFlibroEditarFiltrarNombre)
                    .addComponent(jTFtituloEditar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFidLibroEditar)
                    .addComponent(jTFgeneroEditar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel16)
                        .addGap(26, 26, 26)
                        .addComponent(jTFlibroEditarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBlimpiarLibroEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBeditarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jBactualizarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFfiltrarPorNombre)
                    .addComponent(jTFlibroEditarFiltrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTFlibroEditarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBeditarLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTFtituloEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFgeneroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBactualizarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTFunidadesEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBlimpiarLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTFidLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", jPanel1);

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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(177, 177, 177))
            .addGroup(layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15))
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        JFMenuPrincipal menu = new JFMenuPrincipal();
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jBeditarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarLibroActionPerformed
        TableModel model = jTdatosLibro.getModel();
        int filaEncontrada = -1;
        if(this.jTFlibroEditarFiltrarNombre.getText().length()==0 && this.jTFlibroEditarFiltrarID.getText().length()==0)
        JOptionPane.showMessageDialog(null, 
            "Primero llene alguno de los campos.","Error.",JOptionPane.WARNING_MESSAGE);    
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
        JOptionPane.showMessageDialog(null, 
        "No existe ese ID","Error.",JOptionPane.WARNING_MESSAGE);
        }else{
        jTFtituloEditar.setText(model.getValueAt(filaEncontrada, 0).toString());
        jTFgeneroEditar.setText(model.getValueAt(filaEncontrada, 1).toString());
        jTFidLibroEditar.setText(model.getValueAt(filaEncontrada, 2).toString());
        jTFunidadesEditar.setText(model.getValueAt(filaEncontrada, 4).toString());
              
            }    
        }else{
        for (int fila = 0; fila < model.getRowCount(); fila++) {
        String idEnFila = model.getValueAt(fila, 0).toString(); 
        if (idEnFila.equals(jTFlibroEditarFiltrarNombre.getText())) {
        filaEncontrada = fila;
        break;
                }
        }if(filaEncontrada==-1){
        JOptionPane.showMessageDialog(null, 
        "No existe ese nombre.","Error",JOptionPane.WARNING_MESSAGE);
            }
        else{
        jTFtituloEditar.setText(model.getValueAt(filaEncontrada, 0).toString());
        jTFgeneroEditar.setText(model.getValueAt(filaEncontrada, 1).toString());
        jTFidLibroEditar.setText(model.getValueAt(filaEncontrada, 2).toString());
        jTFunidadesEditar.setText(model.getValueAt(filaEncontrada, 4).toString());   
  }    
  }
            
   }
      filtrarTablaId("");  
      filtrarTablaNombre("");
                 
    }//GEN-LAST:event_jBeditarLibroActionPerformed

    private void jTFidAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidAutorActionPerformed
        
    }//GEN-LAST:event_jTFidAutorActionPerformed

    private void jTFlibroEditarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroEditarFiltrarNombreActionPerformed
        
        
    }//GEN-LAST:event_jTFlibroEditarFiltrarNombreActionPerformed

    private void jTFlibroBorrarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroBorrarFiltrarNombreActionPerformed
    
    }//GEN-LAST:event_jTFlibroBorrarFiltrarNombreActionPerformed

    private void jBactualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarLibroActionPerformed
        int idLibro = Integer.parseInt(this.jTFidLibroEditar.getText());
        int nuevaCantidad = Integer.parseInt(jTFunidadesEditar.getText());
        String nuevoTitulo = jTFtituloEditar.getText();
        String nuevoGenero = jTFgeneroEditar.getText();
                // Aquí es donde actualizamos el autor en la base de datos
        fecha = new Fecha(jTFfechaNacimientoAutor.getText());
                
        autor = new Autor(Long.parseLong(jTFidAutor.getText()),jTFnombreAutor.getText(),fecha);
                
        libro= new Libro(Integer.parseInt(jTFidLibroEditar.getText()),autor,
        Integer.parseInt(jTFunidadesEditar.getText()),jTFtituloEditar.getText(),jTFgeneroEditar.getText());
                
        libro.actualizarLibroEnBaseDeDatos(idLibro, nuevaCantidad, nuevoTitulo, nuevoGenero);
        mostrarTabla();
    }//GEN-LAST:event_jBactualizarLibroActionPerformed

    private void jBmostrarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmostrarLibroActionPerformed
        TableModel model = jTdatosLibro.getModel();
        int filaEncontrada = -1;
        if(this.jTFlibroBorrarFiltrarNombre.getText().length()==0 
           && this.jTFlibroBorrarFiltrarID.getText().length()==0)
            JOptionPane.showMessageDialog(null, 
        "Primero llene alguno de los campos.","Error",JOptionPane.WARNING_MESSAGE);    
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
        JOptionPane.showMessageDialog(null, "No existe ese ID.",
         "Error.",JOptionPane.WARNING_MESSAGE);
        }
         else{
        jTFtituloBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
        jTFgeneroBorrar.setText(model.getValueAt(filaEncontrada, 1).toString());
        jTFidLibroBorrar.setText(model.getValueAt(filaEncontrada, 2).toString()); 
        jTFunidadesBorrar.setText(model.getValueAt(filaEncontrada, 4).toString());
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
        JOptionPane.showMessageDialog(null, 
         "No existe ese nombre.","Error",JOptionPane.WARNING_MESSAGE);
        }
         else{
        jTFtituloBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
        jTFgeneroBorrar.setText(model.getValueAt(filaEncontrada, 1).toString());
        jTFidLibroBorrar.setText(model.getValueAt(filaEncontrada, 2).toString()); 
        jTFunidadesBorrar.setText(model.getValueAt(filaEncontrada, 4).toString());    
        }    
        }
        }  
        filtrarTablaId("");
        filtrarTablaNombre("");
        mostrarTabla();
    }//GEN-LAST:event_jBmostrarLibroActionPerformed

    private void jBborrarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarEstudianteActionPerformed
    fecha = new Fecha(jTFfechaNacimientoAutor.getText());            
    autor = new Autor(Long.parseLong(jTFidAutor.getText()),jTFnombreAutor.getText(),fecha);         
    libro= new Libro(Integer.parseInt(jTFidLibroBorrar.getText()),autor,
    Integer.parseInt(jTFunidadesBorrar.getText()),jTFtituloBorrar.getText(),jTFgeneroBorrar.getText());
                 
    try {
    if(idLibroSeleccionado != -1){
    // Verifica si el estudiante tiene préstamos pendientes
    if (libro.tienePrestamosPendientes(idLibroSeleccionado)) {
    JOptionPane.showMessageDialog(null, "El libro se encuentra en un préstamo pendientes y no puede ser eliminado");
    return;
    }

    // Pregunta al usuario si está seguro de borrar
    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar este estudiante?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {
    libro.eliminarLibroEnBaseDeDatos(idLibroSeleccionado);
    
    idLibroSeleccionado = -1; // Restablece el ID después de la eliminación
    mostrarTabla();
    }
    } else {
    JOptionPane.showMessageDialog(null, "No se encontró el estudiante","Error",JOptionPane.WARNING_MESSAGE);
    }
 
    } catch (ArrayIndexOutOfBoundsException ex) {
    JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites");
    }
    
        
    }//GEN-LAST:event_jBborrarEstudianteActionPerformed

    private void jBinsertarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBinsertarLibroActionPerformed
        TableModel model = jTdatosAutor.getModel();
        if(jTFidAutor.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, 
            "Antes de ingresar un libro primero debe seleccionar un autor.");
        }
        else{
        try {
            PreparedStatement pps = cn.prepareStatement(
         "INSERT INTO Libro(NombreLibro, Genero , IdLibro, UnidadesDisponibles,IdAutor) VALUES (?,?,?,?,?)");
            pps.setString(1, jTFtituloLibro.getText());
            pps.setString(2, jTFgeneroLibro.getText());
            int index = jCBgeneroLibros.getSelectedIndex();
            String genero = (String) jCBgeneroLibros.getItemAt(index);
            jTFgeneroLibro.setText(genero);
            pps.setInt(3, Integer.parseInt(jTFidLibro.getText()));
            pps.setInt(4, Integer.parseInt(jTFunidadesLibro.getText()));
            pps.setLong(5,Long.parseLong(jTFidAutor.getText()));
            
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados.");

            libro = new Libro( Integer.parseInt(jTFidLibro.getText()),autor,
            Integer.parseInt(jTFunidadesLibro.getText()), jTFtituloLibro.getText(),
            jTFgeneroLibro.getText());

            jTAlibroActual.setText(libro.toString());

            mostrarTabla();
           
           jTFidAutor.setEnabled(true);
        }
        catch (SQLException ex){
        JOptionPane.showMessageDialog(null, "Libro ya registrado.");
        }    
        }
        
    }//GEN-LAST:event_jBinsertarLibroActionPerformed

    private void jBlimpiarInsertarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarInsertarLibroActionPerformed
        jTFidLibro.setText("");
        jTFidAutor.setText(""); 
        jTFtituloLibro.setText("");
        jTFunidadesLibro.setText("");
        jTFnombreAutor.setText("");
        jTFfechaNacimientoAutor.setText("");
        jTAlibroActual.setText("");
    }//GEN-LAST:event_jBlimpiarInsertarLibroActionPerformed

    private void jBlimpiarLibroEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarLibroEditarActionPerformed
        jTFtituloEditar.setText("");
        jTFgeneroEditar.setText("");
        jTFunidadesEditar.setText("");
        jTFlibroEditarFiltrarNombre.setText("");
        jTFlibroEditarFiltrarID.setText("");
        jTFidLibroEditar.setText("");
        
    }//GEN-LAST:event_jBlimpiarLibroEditarActionPerformed

    private void jBlimpiarLibrosBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarLibrosBorrarActionPerformed
        jTFtituloBorrar.setText("");
        jTFgeneroBorrar.setText("");
        jTFunidadesBorrar.setText("");
        jTFlibroBorrarFiltrarNombre.setText("");
        jTFlibroBorrarFiltrarID.setText("");
        jTFidLibroBorrar.setText("");
    }//GEN-LAST:event_jBlimpiarLibrosBorrarActionPerformed

    private void jTFtituloLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFtituloLibroKeyTyped
    char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!='\b' && caracter!=' ' ) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un titulo de manera correcta.");
        }
    }//GEN-LAST:event_jTFtituloLibroKeyTyped

    private void jTFunidadesLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFunidadesLibroKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese unidades de manera correcta.");
        }
    }//GEN-LAST:event_jTFunidadesLibroKeyTyped

    private void jTFidLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidLibroKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un id de manera correcta.");
        }
    }//GEN-LAST:event_jTFidLibroKeyTyped

    private void jTFidAutorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidAutorKeyTyped
         char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un id de manera correcta.");
        }
    }//GEN-LAST:event_jTFidAutorKeyTyped

    private void jTFtituloEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFtituloEditarKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!=' ' && caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un titulo de manera correcta.");
        }
    }//GEN-LAST:event_jTFtituloEditarKeyTyped

    private void jTFgeneroEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFgeneroEditarKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un genero de manera correcta.");
        }
    }//GEN-LAST:event_jTFgeneroEditarKeyTyped

    private void jTFunidadesEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFunidadesEditarKeyTyped
         char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese unidades de manera correcta.");
        }
    }//GEN-LAST:event_jTFunidadesEditarKeyTyped

    private void jTFlibroEditarFiltrarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlibroEditarFiltrarNombreKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!=' ' &&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un titulo de manera correcta.");
        }
    }//GEN-LAST:event_jTFlibroEditarFiltrarNombreKeyTyped

    private void jTFlibroEditarFiltrarIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlibroEditarFiltrarIDKeyTyped
    char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese unidades de manera correcta.");
        }
    }//GEN-LAST:event_jTFlibroEditarFiltrarIDKeyTyped

    private void jTFlibroBorrarFiltrarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlibroBorrarFiltrarNombreKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un titulo de manera correcta.");
        }
    }//GEN-LAST:event_jTFlibroBorrarFiltrarNombreKeyTyped

    private void jTFlibroBorrarFiltrarIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlibroBorrarFiltrarIDKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese unidades de manera correcta.");
        }
    }//GEN-LAST:event_jTFlibroBorrarFiltrarIDKeyTyped

    private void jBMostrarDatosAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarDatosAutorActionPerformed
        TableModel model = jTdatosAutor.getModel();
        int filaEncontrada = -1;
          
        for (int fila = 0; fila < model.getRowCount(); fila++) {
        String idEnFila = model.getValueAt(fila, 2).toString();
        if (idEnFila.equals(jTFidAutor.getText())) {
        filaEncontrada = fila;
        break;
            }
        }
        if(filaEncontrada==-1){
        JOptionPane.showMessageDialog(null, "No existe ese ID.","Error",JOptionPane.WARNING_MESSAGE);
        }

        jTFnombreAutor.setText(model.getValueAt(filaEncontrada, 0).toString());
        jTFfechaNacimientoAutor.setText(model.getValueAt(filaEncontrada, 1).toString());
        jTFidAutor.setText(model.getValueAt(filaEncontrada, 2).toString()); 
        jTFidAutor.setEnabled(false);
        filtrarTablaIdAutor("");
        mostrarTabla();
        
    }//GEN-LAST:event_jBMostrarDatosAutorActionPerformed

    private void jCBgeneroLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosActionPerformed
    String generoSeleccionado = (String) jCBgeneroLibros.getSelectedItem();

    switch (generoSeleccionado) {
        case "Ficción":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "No ficción":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Misterio":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Ciencia Ficción":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Fantasía":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Romance":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Aventura":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Drama":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Terror":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Poesía":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Biografía":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Historia":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
          case "Ciencia":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Viajes":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Autoayuda":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Humor":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Suspenso":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
        case "Crimen":
            jTFgeneroLibro.setText(generoSeleccionado);
            break;
     
        default:
            break;
    }
    }//GEN-LAST:event_jCBgeneroLibrosActionPerformed

    private void jCBgeneroLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosMouseClicked
        
    }//GEN-LAST:event_jCBgeneroLibrosMouseClicked

    private void jCBgeneroLibrosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosMouseEntered
        
    }//GEN-LAST:event_jCBgeneroLibrosMouseEntered

    private void jCBgeneroLibrosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosMouseExited
        
    }//GEN-LAST:event_jCBgeneroLibrosMouseExited

    private void jCBgeneroLibrosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosMouseReleased
    
    }//GEN-LAST:event_jCBgeneroLibrosMouseReleased

    private void jCBgeneroLibrosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosMousePressed
       
    }//GEN-LAST:event_jCBgeneroLibrosMousePressed
    
    
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
    private javax.swing.JButton jBMostrarDatosAutor;
    private javax.swing.JButton jBactualizarLibro;
    private javax.swing.JButton jBborrarEstudiante;
    private javax.swing.JButton jBeditarLibro;
    private javax.swing.JButton jBinsertarLibro;
    private javax.swing.JButton jBlimpiarInsertarLibro;
    private javax.swing.JButton jBlimpiarLibroEditar;
    private javax.swing.JButton jBlimpiarLibrosBorrar;
    private javax.swing.JButton jBmostrarLibro;
    private javax.swing.JComboBox<String> jCBgeneroLibros;
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
