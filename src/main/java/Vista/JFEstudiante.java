package Vista;

import Negocio.Estudiante;
import Negocio.Fecha;
import PConexion.Conexion;
import Vista.JFMenuPrincipal;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
       
    public JFEstudiante() {
        initComponents();
        this.setVisible(false);
        this.setLocationRelativeTo(this);
        mostrarTabla();
        File file = new File("C:/Users/DELL/OneDrive - Escuela Politécnica Nacional/DANIEL/EPN/SEGUNDO SEMESTRE/P/WORKSPACE 2023B/New Folder/ProyectoBimestral/src/main/java/Imagenes/BibliotecaImagen.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        this.setResizable(false);
        
        this.jTFestudianteEditar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            filtrarTablaNombre(text);    
        }}); 
        this.jTFestudianteBorrar.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
            filtrarTablaNombre(text);    
        }});
        }
      
    public void filtrarTablaNombre(String query) {
    DefaultTableModel model = (DefaultTableModel) jTdatosEstudiantes.getModel();
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
    jTdatosEstudiantes.setRowSorter(tr);

    if (query.trim().length() == 0) {
        tr.setRowFilter(null);
    } else {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < model.getColumnCount(); columnIndex++) {
            filters.add(RowFilter.regexFilter("(?i)" + query, columnIndex)); // Ignore case
        }
        tr.setRowFilter(RowFilter.orFilter(filters));
        }
    }
    public void filtrarTablaId(String query) {
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
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jBeditarEstudiante = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTFestudianteEditar = new javax.swing.JTextField();
        jBactualizarEstudiante = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTFnombreEstudianteEditar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTFfechaEstudianteEditar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFIDEstudianteEditar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFcorreoEstudianteEditar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBborrarEstudiante = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTFestudianteBorrar = new javax.swing.JTextField();
        jBMostrar = new javax.swing.JButton();
        jTFnombreEstudianteBorrar = new javax.swing.JTextField();
        jTFfechaEstudianteBorrar = new javax.swing.JTextField();
        jTFIDEstudianteBorrar = new javax.swing.JTextField();
        jTFcorreoEstudianteBorrar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
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
                    .addComponent(jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFidEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(jTFcorreoInstitucionalEstudiante)
                    .addComponent(jTFnombreEstudiante))
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBinsertarEstudiante)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jBeditarEstudiante.setText("Editar");
        jBeditarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBeditarEstudianteActionPerformed(evt);
            }
        });

        jLabel5.setText("Ingrese el Código del Estudiante a Editar:");

        jTFestudianteEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFestudianteEditarActionPerformed(evt);
            }
        });
        jTFestudianteEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFestudianteEditarKeyTyped(evt);
            }
        });

        jBactualizarEstudiante.setText("Actualizar");
        jBactualizarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBactualizarEstudianteActionPerformed(evt);
            }
        });

        jLabel11.setText("Nombre:");

        jLabel12.setText("Fecha de Nacimiento:");

        jLabel13.setText("IdEstudiante:");

        jTFIDEstudianteEditar.setEditable(false);

        jLabel14.setText("Correo Institucional:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTFestudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jBeditarEstudiante)
                        .addGap(18, 18, 18)
                        .addComponent(jBactualizarEstudiante))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTFfechaEstudianteEditar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTFnombreEstudianteEditar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTFIDEstudianteEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addComponent(jTFcorreoEstudianteEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFnombreEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFfechaEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFIDEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFcorreoEstudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFestudianteEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBeditarEstudiante)
                    .addComponent(jBactualizarEstudiante))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Editar", jPanel1);

        jBborrarEstudiante.setText("Borrar");
        jBborrarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarEstudianteActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtrar");

        jBMostrar.setText("Mostrar Estudiante");
        jBMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarActionPerformed(evt);
            }
        });

        jTFnombreEstudianteBorrar.setEditable(false);

        jTFfechaEstudianteBorrar.setEditable(false);

        jTFIDEstudianteBorrar.setEditable(false);

        jTFcorreoEstudianteBorrar.setEditable(false);

        jLabel15.setText("Nombre:");

        jLabel16.setText("Fecha de Nacimiento:");

        jLabel17.setText("IdEstudiante:");

        jLabel18.setText("Correo Institucional:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFIDEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTFestudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTFnombreEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFcorreoEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFfechaEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jBMostrar)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jBborrarEstudiante)
                        .addGap(101, 101, 101))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFestudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFnombreEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBMostrar)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTFfechaEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIDEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jBborrarEstudiante))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTFcorreoEstudianteBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = jDateChooser.getDate();
            String fechaNacimiento = dateFormat.format(date);

            // Combina los elementos seleccionados en el formato de fecha deseado (asumo que es 'YYYY-MM-DD')
         

            pps.setDate(2, new java.sql.Date(date.getTime()));
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
        if(jTFnombreEstudianteBorrar.getText().length()==0){
        JOptionPane.showMessageDialog(null, "Primero seleccione al estudiante a eliminar","Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
        try {
            int idEstudiante = Integer.parseInt(this.jTFIDEstudianteBorrar.getText());

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
    }
    }//GEN-LAST:event_jBborrarEstudianteActionPerformed

    private void jBeditarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarEstudianteActionPerformed
        TableModel model = jTdatosEstudiantes.getModel();
        int selectedRow = -1;

        // Si solo hay una fila en la tabla después de haberla filtrado, seleccionar esa fila
        if(jTdatosEstudiantes.getRowCount() == 1){
            selectedRow = 0;
            JOptionPane.showMessageDialog(null, "Estudiante seleccionado correctamente. ID: " + model.getValueAt(selectedRow, 3).toString());
            jTFnombreEstudianteEditar.setText(model.getValueAt(selectedRow, 0).toString());
            jTFfechaEstudianteEditar.setText(model.getValueAt(selectedRow, 1).toString());
            jTFcorreoEstudianteEditar.setText(model.getValueAt(selectedRow, 2).toString());
            jTFIDEstudianteEditar.setText(model.getValueAt(selectedRow, 3).toString());   
            mostrarTabla();
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, filtra la tabla hasta que quede un solo estudiante.","Error",JOptionPane.WARNING_MESSAGE);
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
    private void actualizarEstudianteEnBaseDeDatosNombre(int idEstudiante, String nuevoNombre, String nuevaFechaNacimiento, String nuevoCorreoInstitucional) {
        String queryNombre = "UPDATE Estudiante SET NombreEstudiante = ? WHERE nombreEstudiante = ?";
        String queryFecha = "UPDATE Estudiante SET FechaNacimiento = ? WHERE nombreEstudiante = ?";
        String queryCorreo = "UPDATE Estudiante SET CorreoInstitucional = ? WHERE nombreEstudiante = ?";

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

    private void jTFestudianteEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFestudianteEditarActionPerformed

    }//GEN-LAST:event_jTFestudianteEditarActionPerformed

    private void jBactualizarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarEstudianteActionPerformed
        int idEstudiante = Integer.parseInt(this.jTFIDEstudianteEditar.getText());
        String nuevoNombre = jTFnombreEstudianteEditar.getText();
        String nuevaFechaNacimiento = jTFfechaEstudianteEditar.getText();
        String nuevoCorreoInstitucional = jTFcorreoEstudianteEditar.getText();
        // Aquí es donde actualizamos el autor en la base de datos
        actualizarEstudianteEnBaseDeDatos(idEstudiante, nuevoNombre, nuevaFechaNacimiento, nuevoCorreoInstitucional);
        mostrarTabla();
    }//GEN-LAST:event_jBactualizarEstudianteActionPerformed

    private void jTFestudianteEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFestudianteEditarKeyTyped

    private void jTFestudianteEditarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestudianteEditarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFestudianteEditarKeyReleased

    private void jBMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarActionPerformed
    TableModel model = jTdatosEstudiantes.getModel();
    if(jTdatosEstudiantes.getSelectedRow()==-1){
    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun registro","Error",JOptionPane.WARNING_MESSAGE);
    }
    else{
    jTFnombreEstudianteBorrar.setText(model.getValueAt(jTdatosEstudiantes.getSelectedRow(), 0).toString());
    jTFfechaEstudianteBorrar.setText(model.getValueAt(jTdatosEstudiantes.getSelectedRow(), 1).toString());
    jTFcorreoEstudianteBorrar.setText(model.getValueAt(jTdatosEstudiantes.getSelectedRow(), 2).toString());
    jTFIDEstudianteBorrar.setText(model.getValueAt(jTdatosEstudiantes.getSelectedRow(), 3).toString());
    }
    }//GEN-LAST:event_jBMostrarActionPerformed

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
    private javax.swing.JButton jBactualizarEstudiante;
    private javax.swing.JButton jBborrarEstudiante;
    private javax.swing.JButton jBeditarEstudiante;
    private javax.swing.JButton jBinsertarEstudiante;
    private com.toedter.calendar.JDateChooser jDateChooser;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTAestudianteActual;
    private javax.swing.JTextField jTFIDEstudianteBorrar;
    private javax.swing.JTextField jTFIDEstudianteEditar;
    private javax.swing.JTextField jTFcorreoEstudianteBorrar;
    private javax.swing.JTextField jTFcorreoEstudianteEditar;
    private javax.swing.JTextField jTFcorreoInstitucionalEstudiante;
    private javax.swing.JTextField jTFestudianteBorrar;
    private javax.swing.JTextField jTFestudianteEditar;
    private javax.swing.JTextField jTFfechaEstudianteBorrar;
    private javax.swing.JTextField jTFfechaEstudianteEditar;
    private javax.swing.JTextField jTFidEstudiante;
    private javax.swing.JTextField jTFnombreEstudiante;
    private javax.swing.JTextField jTFnombreEstudianteBorrar;
    private javax.swing.JTextField jTFnombreEstudianteEditar;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTdatosEstudiantes;
    // End of variables declaration//GEN-END:variables
}
