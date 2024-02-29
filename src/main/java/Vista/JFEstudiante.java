package Vista;

import Negocio.Estudiante;
import Negocio.Fecha;
import PConexion.Conexion;
import Vista.JFMenuPrincipal;
import java.util.List;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class JFEstudiante extends javax.swing.JFrame {
    Estudiante estudiante;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();
    private int estudianteSeleccionado;
    Calendar cal = Calendar.getInstance();
    Date fechaActual = cal.getTime();
        
    public JFEstudiante() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        jDateChooser.setMaxSelectableDate(fechaActual);
        jDateChooserEditar.setMaxSelectableDate(fechaActual);
        this.setResizable(false);        
        this.jTFestudianteEditarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);  
            vaciarCamposNombreE();    
        }}); 
        this.jTFestudianteEditarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            vaciarCamposIdE();
            
        }}); 
        this.jTFestudianteBorrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);
            vaciarCamposNombreB();
        }});
        this.jTFestudianteBorrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            vaciarCamposIdB();
        }});
        jTdatosEstudiantes.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int filaSeleccionada = jTdatosEstudiantes.getSelectedRow();
        TableModel model = jTdatosEstudiantes.getModel();

        // Verifica si se ha seleccionado una fila válida
        if (filaSeleccionada != -1) {
            String nombre = model.getValueAt(filaSeleccionada, 0).toString();
            String fechaNacimiento = model.getValueAt(filaSeleccionada, 1).toString();
            String correo = model.getValueAt(filaSeleccionada, 2).toString();
            String idEstudiante = model.getValueAt(filaSeleccionada, 3).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // Actualiza los campos de texto con los datos de la fila seleccionada
            
            int indexSelect = jTpEstudiante.getSelectedIndex();
            switch (indexSelect)
            {
                case 0:
                case 1:
                    jTFnombreEstudianteEditar.setText(nombre);
                    jTFcorreoEstudianteEditar.setText(correo);
                    jTFIDEstudianteEditar.setText(idEstudiante);                  
                    try {
                        Date fecha = sdf.parse(fechaNacimiento);
                        jDateChooserEditar.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(JFEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jTFnombreEstudianteEditar.setEnabled(true);
                    jDateChooserEditar.setEnabled(true);
                    jTFcorreoEstudianteEditar.setEditable(true);
                    jTFnombreEstudianteBorrar.setText("");
                    jTFcorreoEstudianteBorrar.setText("");
                    jTFIDEstudianteBorrar.setText("");
                    jTFfechaEstudianteBorrar.setText("");
                    break;
                case 2:
                    jTFnombreEstudianteBorrar.setText(nombre);
                    jTFcorreoEstudianteBorrar.setText(correo);
                    jTFIDEstudianteBorrar.setText(idEstudiante);
                    jTFfechaEstudianteBorrar.setText(fechaNacimiento);
                    jTFnombreEstudianteEditar.setText("");
                    jTFcorreoEstudianteEditar.setText("");
                    jTFIDEstudianteEditar.setText("");  
                    jDateChooserEditar.setDate(null);
                    break;
            }            

            
        }
    }
        
});

        }
        
    public void vaciarCamposNombreE(){
        this.jTFestudianteBorrarNombre.setText("");
    }
    public void vaciarCamposNombreB(){
        this.jTFestudianteEditarNombre.setText("");   
    }  
    public void vaciarCamposIdE(){
        this.jTFestudianteBorrarID.setText("");    
    }  
    public void vaciarCamposIdB(){
        this.jTFestudianteEditarID.setText("");    
    }  
    
    public void filtrarTablaNombre(String query){
        this.jTFestudianteBorrarID.setText("");
        this.jTFestudianteEditarID.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosEstudiantes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosEstudiantes.setRowSorter(tr);
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
        this.jTFestudianteEditarNombre.setText("");
        this.jTFestudianteBorrarNombre.setText("");
        DefaultTableModel model = (DefaultTableModel) jTdatosEstudiantes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jTdatosEstudiantes.setRowSorter(tr);

        if (query.trim().length() == 0) {
        // Si el texto de búsqueda está vacío, elimina el filtro y muestra todos los datos
        tr.setRowFilter(null);
        } else {
        // Aplica el filtro solo si el texto de búsqueda no está vacío
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

        jTpEstudiante = new javax.swing.JTabbedPane();
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
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jTbVaciar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jBmostrarEstudianteEditar = new javax.swing.JButton();
        jBactualizarEstudiante = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTFnombreEstudianteEditar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTFIDEstudianteEditar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFcorreoEstudianteEditar = new javax.swing.JTextField();
        jTFestudianteEditarNombre = new javax.swing.JTextField();
        jBvaciarBorrar1 = new javax.swing.JButton();
        jTFestudianteEditarID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooserEditar = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jBborrarEstudiante = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFestudianteBorrarNombre = new javax.swing.JTextField();
        jBMostrarEstudianteBorrar = new javax.swing.JButton();
        jTFnombreEstudianteBorrar = new javax.swing.JTextField();
        jTFfechaEstudianteBorrar = new javax.swing.JTextField();
        jTFIDEstudianteBorrar = new javax.swing.JTextField();
        jTFcorreoEstudianteBorrar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTFestudianteBorrarID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jBvaciarBorrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTdatosEstudiantes = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTpEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTpEstudianteKeyTyped(evt);
            }
        });

        jTAestudianteActual.setEditable(false);
        jTAestudianteActual.setColumns(20);
        jTAestudianteActual.setRows(5);
        jTAestudianteActual.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiante Actual"));
        jScrollPane2.setViewportView(jTAestudianteActual);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Fecha de Nacimiento:");

        jLabel3.setText("Código Único:");

        jTFnombreEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreEstudianteKeyTyped(evt);
            }
        });

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

        jTFcorreoInstitucionalEstudiante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFcorreoInstitucionalEstudianteKeyTyped(evt);
            }
        });

        jBinsertarEstudiante.setText("Insertar");
        jBinsertarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBinsertarEstudianteActionPerformed(evt);
            }
        });

        jTbVaciar.setText("Vaciar");
        jTbVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTbVaciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBinsertarEstudiante)
                        .addGap(100, 100, 100)
                        .addComponent(jTbVaciar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFidEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(jTFcorreoInstitucionalEstudiante)
                            .addComponent(jTFnombreEstudiante))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFidEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFcorreoInstitucionalEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBinsertarEstudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTbVaciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTpEstudiante.addTab("Insertar", jPanel2);

        jBmostrarEstudianteEditar.setText("Mostrar");
        jBmostrarEstudianteEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmostrarEstudianteEditarActionPerformed(evt);
            }
        });

        jBactualizarEstudiante.setText("Actualizar");
        jBactualizarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarEstudianteActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre:");

        jTFnombreEstudianteEditar.setEditable(false);
        jTFnombreEstudianteEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFnombreEstudianteEditarKeyTyped(evt);
            }
        });

        jLabel12.setText("Fecha de Nacimiento:");

        jLabel13.setText("IdEstudiante:");

        jTFIDEstudianteEditar.setEditable(false);

        jLabel14.setText("Correo Institucional:");

        jTFcorreoEstudianteEditar.setEditable(false);
        jTFcorreoEstudianteEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFcorreoEstudianteEditarKeyTyped(evt);
            }
        });

        jTFestudianteEditarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFestudianteEditarNombreActionPerformed(evt);
            }
        });
        jTFestudianteEditarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarNombreKeyTyped(evt);
            }
        });

        jBvaciarBorrar1.setText("Vaciar");
        jBvaciarBorrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBvaciarBorrar1ActionPerformed(evt);
            }
        });

        jTFestudianteEditarID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFestudianteEditarIDActionPerformed(evt);
            }
        });
        jTFestudianteEditarID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarIDKeyTyped(evt);
            }
        });

        jLabel5.setText("Filtrar por nombre:");

        jLabel19.setText("Filtrar por ID:");

        jDateChooserEditar.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFestudianteEditarID, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTFcorreoEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBvaciarBorrar1)
                        .addGap(129, 129, 129))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDateChooserEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addComponent(jBactualizarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFnombreEstudianteEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(jTFIDEstudianteEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBmostrarEstudianteEditar)
                        .addGap(131, 131, 131))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFestudianteEditarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFestudianteEditarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFestudianteEditarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBactualizarEstudiante)
                        .addGap(30, 30, 30)
                        .addComponent(jBvaciarBorrar1)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFnombreEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jBmostrarEstudianteEditar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFIDEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFcorreoEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(25, 25, 25))))
        );

        jTpEstudiante.addTab("Editar", jPanel1);

        jBborrarEstudiante.setText("Borrar");
        jBborrarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarEstudianteActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtrar por ID:");

        jTFestudianteBorrarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFestudianteBorrarNombreKeyTyped(evt);
            }
        });

        jBMostrarEstudianteBorrar.setText("Mostrar Estudiante");
        jBMostrarEstudianteBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarEstudianteBorrarActionPerformed(evt);
            }
        });

        jTFnombreEstudianteBorrar.setEditable(false);

        jTFfechaEstudianteBorrar.setEditable(false);

        jTFIDEstudianteBorrar.setEditable(false);

        jTFcorreoEstudianteBorrar.setEditable(false);
        jTFcorreoEstudianteBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFcorreoEstudianteBorrarActionPerformed(evt);
            }
        });

        jLabel15.setText("Nombre:");

        jLabel16.setText("Fecha de Nacimiento:");

        jLabel17.setText("Id Estudiante:");

        jLabel18.setText("Correo Institucional:");

        jTFestudianteBorrarID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFestudianteBorrarIDKeyTyped(evt);
            }
        });

        jLabel7.setText("Filtrar por Nombre:");

        jBvaciarBorrar.setText("Vaciar");
        jBvaciarBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBvaciarBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFIDEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFnombreEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFcorreoEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFfechaEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jBMostrarEstudianteBorrar))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBborrarEstudiante)
                                    .addComponent(jBvaciarBorrar)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFestudianteBorrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFestudianteBorrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFestudianteBorrarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTFestudianteBorrarID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFnombreEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBMostrarEstudianteBorrar)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel16)))
                        .addGap(18, 18, 18)
                        .addComponent(jBborrarEstudiante))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jTFfechaEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFIDEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTFcorreoEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jBvaciarBorrar)))
                .addGap(19, 19, 19))
        );

        jTpEstudiante.addTab("Borrar", jPanel3);

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
        jTdatosEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTdatosEstudiantesMouseClicked(evt);
            }
        });
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
                    .addComponent(jTpEstudiante)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTpEstudiante)
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
         if(jTFnombreEstudiante.getText().length()==0 || jDateChooser.getDate()==null || jTFcorreoInstitucionalEstudiante.getText().length()==0 || jTFidEstudiante.getText().length()==0){
        JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacios, llenelos por favor");
        }else{
        try {
            String correo = jTFcorreoInstitucionalEstudiante.getText();
            if (!correo.endsWith("@epn.edu.ec")) {
                JOptionPane.showMessageDialog(null, "El correo debe terminar en @epn.edu.ec");
                return;
            }

            PreparedStatement pps = cn.prepareStatement("INSERT INTO Estudiante(NombreEstudiante, FechaNacimiento, CorreoInstitucional, IdEstudiante) VALUES (?,?,?,?)");
            pps.setString(1, jTFnombreEstudiante.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = jDateChooser.getDate();
            String fechaNacimiento = dateFormat.format(date);

            pps.setDate(2, new java.sql.Date(date.getTime()));
            pps.setString(3, correo);
            pps.setInt(4, Integer.parseInt(jTFidEstudiante.getText()));

            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados");

            Fecha fechaNacimientoEstudiante = new Fecha(fechaNacimiento);
            estudiante = new Estudiante(Integer.parseInt(jTFidEstudiante.getText()),correo, jTFnombreEstudiante.getText(), fechaNacimientoEstudiante);
            jTAestudianteActual.setText(estudiante.toString());
            mostrarTabla();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Estudiante ya registrado");
        }    
        }
        
    }//GEN-LAST:event_jBinsertarEstudianteActionPerformed

    private void jTFidEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFidEstudianteActionPerformed
    
        
    private void jBborrarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarEstudianteActionPerformed
        Fecha fecha1 = new Fecha(jTFfechaEstudianteBorrar.getText());
        this.estudiante = new Estudiante(Integer.parseInt(this.jTFIDEstudianteBorrar.getText()),jTFcorreoEstudianteBorrar.getText(),jTFnombreEstudianteBorrar.getText(),fecha1);
        try {
            if(estudianteSeleccionado != -1){
                // Verifica si el estudiante tiene préstamos pendientes
                if (estudiante.tienePrestamosPendientes(estudianteSeleccionado)) {
                    JOptionPane.showMessageDialog(null, "El estudiante tiene préstamos pendientes y no puede ser eliminado");
                    return;
                }

                // Pregunta al usuario si está seguro de borrar
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres borrar este estudiante?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    estudiante.eliminarEstudianteEnBaseDeDatos(estudianteSeleccionado);
                    estudianteSeleccionado = -1; // Restablece el ID después de la eliminación
                    mostrarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el estudiante","Error",JOptionPane.WARNING_MESSAGE);
            }

            filtrarTablaId("");
            filtrarTablaNombre("");
            jTFnombreEstudianteBorrar.setText("");
            jTFfechaEstudianteBorrar.setText("");
            jTFcorreoEstudianteBorrar.setText("");
            jTFIDEstudianteBorrar.setText("");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites");
        }
    }//GEN-LAST:event_jBborrarEstudianteActionPerformed

    private void jBmostrarEstudianteEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmostrarEstudianteEditarActionPerformed
        TableModel model = jTdatosEstudiantes.getModel();
        if(jTFestudianteEditarNombre.getText().length()==0 && jTFestudianteEditarID.getText().length()==0 && jTdatosEstudiantes.getSelectedRow() == -1){
        JOptionPane.showMessageDialog(null, "Primero filtre o selecione un estudiante a modificar");
        }else{
        int filaEncontrada = -1;
        if(jTdatosEstudiantes.getSelectedRow() != -1) {
            // Si se ha seleccionado una fila, usar esa fila
            filaEncontrada = jTdatosEstudiantes.convertRowIndexToModel(jTdatosEstudiantes.getSelectedRow());
        } else if(jTdatosEstudiantes.getRowCount() == 1) {
            // Si solo hay una fila, seleccionar esa fila
            filaEncontrada = jTdatosEstudiantes.convertRowIndexToModel(0);
        } else if(this.jTFestudianteBorrarNombre.getText().length() == 0) {
            // Buscar por ID
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 3).toString();
                if (idEnFila.equals(jTFestudianteBorrarID.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        } else {
            // Buscar por nombre
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 0).toString(); 
                if (idEnFila.equals(jTFestudianteBorrarNombre.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        }

            if(filaEncontrada != -1){
        jTFnombreEstudianteEditar.setText(model.getValueAt(filaEncontrada, 0).toString());

        // Convertir la fecha de nacimiento a un objeto Date y establecerla en JDateChooser
        String fechaNacimiento = model.getValueAt(filaEncontrada, 1).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(fechaNacimiento);
        } catch (ParseException ex) {
            Logger.getLogger(JFEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        jDateChooserEditar.setDate(date);

        jTFcorreoEstudianteEditar.setText(model.getValueAt(filaEncontrada, 2).toString()); // Muestra el correo del estudiante
        jTFnombreEstudianteEditar.setEnabled(true);
        jDateChooserEditar.setEnabled(true);
        jTFcorreoEstudianteEditar.setEditable(true);
        String idEstudiante = model.getValueAt(filaEncontrada, 3).toString(); // Obtiene el ID del estudiante de la tabla
        jTFIDEstudianteEditar.setText(idEstudiante); // Muestra el ID del estudiante
        try {
            estudianteSeleccionado = Integer.parseInt(idEstudiante); // Almacena el ID del estudiante cuando se selecciona un estudiante
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del estudiante no es válido");
            estudianteSeleccionado = -1;
        }
        
    }
    jTFnombreEstudianteEditar.setEditable(true);
    jDateChooserEditar.setEnabled(true);
    jTFcorreoEstudianteEditar.setEditable(true);
    filtrarTablaId("");
    filtrarTablaNombre("");
    }
    }//GEN-LAST:event_jBmostrarEstudianteEditarActionPerformed
    
    
    
    private void jTFidEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyTyped
    char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
            }
    }//GEN-LAST:event_jTFidEstudianteKeyTyped

    private void jTFidEstudianteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidEstudianteKeyReleased
    }//GEN-LAST:event_jTFidEstudianteKeyReleased

    private void jBactualizarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarEstudianteActionPerformed
        if(jTFIDEstudianteEditar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Primero seleccione un estudiante a modificar");
        }else{
        jTFestudianteEditarNombre.setText(null);
        jTFestudianteEditarID.setText(null);
        int idEstudiante = Integer.parseInt(this.jTFIDEstudianteEditar.getText());
        String nuevoNombre = jTFnombreEstudianteEditar.getText();
        String nuevoCorreoInstitucional = jTFcorreoEstudianteEditar.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = jDateChooserEditar.getDate();
        String nuevaFechaNacimiento = dateFormat.format(date);
        // Aquí es donde actualizamos el autor en la base de datos
        Date date1 = jDateChooserEditar.getDate();
        String fechaNacimiento = dateFormat.format(date1);
        Fecha fecha = new Fecha(fechaNacimiento);
        this.estudiante = new Estudiante(Integer.parseInt(this.jTFIDEstudianteEditar.getText()),jTFcorreoEstudianteEditar.getText(),jTFnombreEstudianteEditar.getText(),fecha);
        estudiante.actualizarEstudianteEnBaseDeDatos(idEstudiante, nuevoNombre, nuevaFechaNacimiento, nuevoCorreoInstitucional);
        jTFnombreEstudianteEditar.setText("");
        jDateChooserEditar.setCalendar(null);
        jTFcorreoEstudianteEditar.setText("");
        jTFIDEstudianteEditar.setText("");
        jTFnombreEstudianteEditar.setEditable(false);
        jDateChooserEditar.setEnabled(false);
        jTFcorreoEstudianteEditar.setEditable(false);
        mostrarTabla();    
        }
    }//GEN-LAST:event_jBactualizarEstudianteActionPerformed

    private void jBMostrarEstudianteBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarEstudianteBorrarActionPerformed
        TableModel model = jTdatosEstudiantes.getModel();
        if(jTFestudianteBorrarNombre.getText().length()==0 && jTFestudianteBorrarID.getText().length()==0 && jTdatosEstudiantes.getSelectedRow() == -1){
        JOptionPane.showMessageDialog(null, "Primero filtre o selecione un estudiante a modificar");
        }else{
        int filaEncontrada = -1;
        if(jTdatosEstudiantes.getSelectedRow() != -1) {
            // Si se ha seleccionado una fila, usar esa fila
            filaEncontrada = jTdatosEstudiantes.convertRowIndexToModel(jTdatosEstudiantes.getSelectedRow());
        } else if(jTdatosEstudiantes.getRowCount() == 1) {
            // Si solo hay una fila, seleccionar esa fila
            filaEncontrada = jTdatosEstudiantes.convertRowIndexToModel(0);
        } else if(this.jTFestudianteBorrarNombre.getText().length() == 0) {
            // Buscar por ID
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 3).toString();
                if (idEnFila.equals(jTFestudianteBorrarID.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        } else {
            // Buscar por nombre
            for (int fila = 0; fila < model.getRowCount(); fila++) {
                String idEnFila = model.getValueAt(fila, 0).toString(); 
                if (idEnFila.equals(jTFestudianteBorrarNombre.getText())) {
                    filaEncontrada = fila;
                    break;
                }
            }
        }

        if(filaEncontrada != -1){
            jTFnombreEstudianteBorrar.setText(model.getValueAt(filaEncontrada, 0).toString());
            String fechaNacimiento = model.getValueAt(filaEncontrada, 1).toString();
            jTFfechaEstudianteBorrar.setText(fechaNacimiento);
            jTFcorreoEstudianteBorrar.setText(model.getValueAt(filaEncontrada, 2).toString());
            jTFIDEstudianteBorrar.setText(model.getValueAt(filaEncontrada, 3).toString());     
            estudianteSeleccionado = Integer.parseInt(jTFIDEstudianteBorrar.getText());
        }
        filtrarTablaId("");
        filtrarTablaNombre("");
        }
    }//GEN-LAST:event_jBMostrarEstudianteBorrarActionPerformed

    private void jBvaciarBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBvaciarBorrarActionPerformed
        jTFnombreEstudianteBorrar.setText("");
        jTFfechaEstudianteBorrar.setText("");
        jTFcorreoEstudianteBorrar.setText("");
        jTFIDEstudianteBorrar.setText("");
        jTFestudianteBorrarNombre.setText("");
        jTFestudianteBorrarID.setText("");
    }//GEN-LAST:event_jBvaciarBorrarActionPerformed

    private void jTFestudianteEditarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFestudianteEditarNombreActionPerformed

    }//GEN-LAST:event_jTFestudianteEditarNombreActionPerformed

    private void jTFestudianteEditarNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFestudianteEditarNombreKeyReleased

    private void jTFestudianteEditarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarNombreKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFestudianteEditarNombreKeyTyped

    private void jBvaciarBorrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBvaciarBorrar1ActionPerformed
        jTFnombreEstudianteEditar.setText("");
        jDateChooserEditar.setCalendar(null);
        jTFcorreoEstudianteEditar.setText("");
        jTFIDEstudianteEditar.setText("");
        jTFestudianteEditarNombre.setText("");
        jTFestudianteEditarID.setText("");
        jTFnombreEstudianteEditar.setEditable(false);
        jDateChooserEditar.setEnabled(false);
        jTFcorreoEstudianteEditar.setEditable(false);
    }//GEN-LAST:event_jBvaciarBorrar1ActionPerformed

    private void jTFestudianteEditarIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFestudianteEditarIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFestudianteEditarIDActionPerformed

    private void jTFestudianteEditarIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFestudianteEditarIDKeyReleased

    private void jTFestudianteEditarIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarIDKeyTyped
        char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
        }
    }//GEN-LAST:event_jTFestudianteEditarIDKeyTyped

    private void jTpEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTpEstudianteKeyTyped

    }//GEN-LAST:event_jTpEstudianteKeyTyped

    private void jTFnombreEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreEstudianteKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreEstudianteKeyTyped

    private void jTFestudianteBorrarIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteBorrarIDKeyTyped
        char caracter= evt.getKeyChar();
        if(Character.isLetter(caracter)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa números");
            }
    }//GEN-LAST:event_jTFestudianteBorrarIDKeyTyped

    private void jTbVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTbVaciarActionPerformed
        jTAestudianteActual.setText("");
        jTFnombreEstudiante.setText("");
        jDateChooser.setCalendar(null);
        jTFcorreoInstitucionalEstudiante.setText("");
        jTFidEstudiante.setText("");
    }//GEN-LAST:event_jTbVaciarActionPerformed

    private void jTFcorreoInstitucionalEstudianteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcorreoInstitucionalEstudianteKeyTyped

    }//GEN-LAST:event_jTFcorreoInstitucionalEstudianteKeyTyped

    private void jTFestudianteBorrarNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteBorrarNombreKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFestudianteBorrarNombreKeyTyped

    private void jTFnombreEstudianteEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombreEstudianteEditarKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&&caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el nombre de manera correcta");
        }
    }//GEN-LAST:event_jTFnombreEstudianteEditarKeyTyped

    private void jTFcorreoEstudianteEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFcorreoEstudianteEditarKeyTyped
        char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != '@' && caracter != '\b' && caracter != '.') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingresa el correo de manera correcta");
        }
    }//GEN-LAST:event_jTFcorreoEstudianteEditarKeyTyped

    private void jTFcorreoEstudianteBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFcorreoEstudianteBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFcorreoEstudianteBorrarActionPerformed

    private void jTdatosEstudiantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTdatosEstudiantesMouseClicked
//        TableModel model = jTdatosEstudiantes.getModel();
////        int index = jTpEstudiante.getSelectedIndex();
////        switch(index){
////            case 0:
////            case 1:
//        String fechaNacimiento = model.getValueAt(model.getRowCount(), 1).toString();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = sdf.parse(fechaNacimiento);
//        } catch (ParseException ex) {
//            Logger.getLogger(JFEstudiante.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        jDateChooserEditar.setDate(date);
//
//        jTFcorreoEstudianteEditar.setText(model.getValueAt(model.getRowCount() , 2).toString()); // Muestra el correo del estudiante
//        jTFnombreEstudianteEditar.setEnabled(true);
//        jDateChooserEditar.setEnabled(true);
//        jTFcorreoEstudianteEditar.setEditable(true);
//        String idEstudiante = model.getValueAt(model.getRowCount(), 3).toString(); // Obtiene el ID del estudiante de la tabla
//        jTFIDEstudianteEditar.setText(idEstudiante); // Muestra el ID del estudiante
////            case 2:
////        }
    }//GEN-LAST:event_jTdatosEstudiantesMouseClicked

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton jBMostrarEstudianteBorrar;
    private javax.swing.JButton jBactualizarEstudiante;
    private javax.swing.JButton jBborrarEstudiante;
    private javax.swing.JButton jBinsertarEstudiante;
    private javax.swing.JButton jBmostrarEstudianteEditar;
    private javax.swing.JButton jBvaciarBorrar;
    private javax.swing.JButton jBvaciarBorrar1;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private com.toedter.calendar.JDateChooser jDateChooserEditar;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JTextField jTFIDEstudianteBorrar;
    private javax.swing.JTextField jTFIDEstudianteEditar;
    private javax.swing.JTextField jTFcorreoEstudianteBorrar;
    private javax.swing.JTextField jTFcorreoEstudianteEditar;
    private javax.swing.JTextField jTFcorreoInstitucionalEstudiante;
    private javax.swing.JTextField jTFestudianteBorrarID;
    private javax.swing.JTextField jTFestudianteBorrarNombre;
    private javax.swing.JTextField jTFestudianteEditarID;
    private javax.swing.JTextField jTFestudianteEditarNombre;
    private javax.swing.JTextField jTFfechaEstudianteBorrar;
    private javax.swing.JTextField jTFidEstudiante;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTextField jTFnombreEstudianteBorrar;
    private javax.swing.JTextField jTFnombreEstudianteEditar;
    private javax.swing.JButton jTbVaciar;
    private javax.swing.JTable jTdatosEstudiantes;
    private javax.swing.JTabbedPane jTpEstudiante;
    // End of variables declaration//GEN-END:variables
}
