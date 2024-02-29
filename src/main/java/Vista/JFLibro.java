package Vista;

import Negocio.Autor;
import Negocio.Fecha;
import Negocio.Libro;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        File file = new File("C:\\Users\\DELL\\OneDrive - Escuela Politécnica Nacional\\DANIEL\\EPN\\SEGUNDO SEMESTRE\\P\\WORKSPACE 2023B\\PROYECTO-MASTER\\ProyectoBimestral\\src\\main\\java\\Imagenes\\Libro.png");
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        setIconImage(icon.getImage());
        
    this.jTFlibroEditarFiltrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);     
        }}); 
        this.jTFlibroEditarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);
 
        }}); 
        this.jTFlibroBorrarFiltrarNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombre(text);
           
        }});
        this.jTFlibroBorrarFiltrarID.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaId(text);

        }});
        
        this.jTFfiltradoAutorNombre.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaNombreAutor(text);
                
        }});
        
        this.jTFfiltradoCodigoAutor.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            filtrarTablaIdAutor(text);
            
        }}); 
        
        jTBlibroGeneral.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            limpiarCampos();
            filtrarTablaId("");
            filtrarTablaNombre("");
        }
        });
        
        jTdatosAutor.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int filaSeleccionadaVista = jTdatosAutor.getSelectedRow();
        if(filaSeleccionadaVista != -1){
        // Convierte el índice de fila de la vista al índice de fila en el modelo
        int filaSeleccionadaModelo = jTdatosAutor.convertRowIndexToModel(filaSeleccionadaVista);
        
        // Obtiene el modelo de datos de la tabla
        TableModel model = jTdatosAutor.getModel();
            String nombre = model.getValueAt(filaSeleccionadaModelo, 0).toString();
            String fecha = model.getValueAt(filaSeleccionadaModelo, 1).toString();
            String codigo = (model.getValueAt(filaSeleccionadaModelo, 2).toString());
            
            jTFnombreAutor.setText(nombre);
            jTFfechaNacimientoAutor.setText(fecha);
            jTFidAutor.setText(codigo);
            filtrarTablaIdAutor("");
            filtrarTablaNombreAutor("");
        }}});   
                      
        
        jTdatosLibro.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int filaSeleccionadaVista = jTdatosLibro.getSelectedRow();
        if(filaSeleccionadaVista != -1){
        // Convierte el índice de fila de la vista al índice de fila en el modelo
        int filaSeleccionadaModelo = jTdatosLibro.convertRowIndexToModel(filaSeleccionadaVista);
        
        // Obtiene el modelo de datos de la tabla
        TableModel model = jTdatosLibro.getModel();
            String tituloLibro = model.getValueAt(filaSeleccionadaModelo, 0).toString();
            String genero = model.getValueAt(filaSeleccionadaModelo, 1).toString();
            String idLibro = (model.getValueAt(filaSeleccionadaModelo, 2).toString());
            String idAutor = model.getValueAt(filaSeleccionadaModelo, 3).toString();
            String unidadesLibro = model.getValueAt(filaSeleccionadaModelo,4).toString();
            
            int indexSelect = jTBlibroGeneral.getSelectedIndex();
            switch (indexSelect)
            {
                case 0:
                    
                case 1:
                    jTFtituloBorrar.setText(tituloLibro);
                    jTFgeneroBorrar.setText(genero);
                    jTFunidadesBorrar.setText(unidadesLibro);  
                    jTFidLibroBorrar.setText(idLibro);
                    jTFidAutorBorrar.setText(idAutor);
                    filtrarTablaId("");
                    filtrarTablaNombre("");
                    break;
                case 2:
                    jTFtituloEditar.setText(tituloLibro);
                    jTFgeneroEditar.setText(genero);
                    jTFunidadesEditar.setText(unidadesLibro);  
                    jTFidLibroEditar.setText(idLibro);
                    jTFidAutorEditar.setText(idAutor);
                    filtrarTablaId("");
                    filtrarTablaNombre("");
//                    jTFnombreEstudianteEditar.setEditable(true);
//                    jDateChooserEditar.setEnabled(true);
//                    jTFcorreoEstudianteEditar.setEditable(true);
                    break;     
            }}}});
    mostrarTabla();
    }
    
    public void limpiarCampos(){
                    jTFtituloLibro.setText("");
                    jTFunidadesLibro.setText("");
                    jTFidLibro.setText("");
                    jTFnombreAutor.setText("");
                    jTFfechaNacimientoAutor.setText("");
                    jTFidAutor.setText("");
        
                    jTFtituloEditar.setText("");
                    jTFgeneroEditar.setText("");
                    jTFunidadesEditar.setText("");  
                    jTFidLibroEditar.setText("");
                    jTFidAutorEditar.setText("");
                    
                    jTFtituloBorrar.setText("");
                    jTFgeneroBorrar.setText("");
                    jTFunidadesBorrar.setText("");  
                    jTFidLibroBorrar.setText("");
                    jTFidAutorBorrar.setText("");
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
        tr.setRowFilter(null);
        } else {
        tr.setRowFilter(RowFilter.regexFilter(query, 2));
    }
    }  
    
        public void filtrarTablaNombreAutor(String query){
        jTFfiltradoCodigoAutor.setText("");  
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        jTdatosAutor.setRowSorter(tr);
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
        public void filtrarTablaIdAutor(String query) {
        jTFfiltradoAutorNombre.setText("");   
        DefaultTableModel model = (DefaultTableModel) jTdatosAutor.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jTdatosAutor.setRowSorter(tr);

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

        jTBlibroGeneral = new javax.swing.JTabbedPane();
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
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTFidAutor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFnombreAutor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTFfechaNacimientoAutor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTFfiltradoAutorNombre = new javax.swing.JTextField();
        jTFfiltradoCodigoAutor = new javax.swing.JTextField();
        jBinsertarLibro = new javax.swing.JButton();
        jBlimpiarInsertarLibro = new javax.swing.JButton();
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
        jBborrarLibro = new javax.swing.JButton();
        jBlimpiarLibrosBorrar = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jTFidAutorBorrar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
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
        jLabel24 = new javax.swing.JLabel();
        jTFidAutorEditar = new javax.swing.JTextField();
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

        jTBlibroGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        jLabel4.setText("Código del Libro:");

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

        jCBgeneroLibros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ficción", "No ficción", "Misterio", "Ciencia ficción", "Fantasía", "Romance", "Aventura", "Drama", "Terror", "Poesía", "Biografía", "Historia", "Ciencia", "Viajes", "Autoayuda", "Misterio", "Humor", "Suspenso", "Crimen" }));
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

        jLabel6.setText("Seleccione un género para su libro:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFidLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(jTFtituloLibro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFunidadesLibro)
                            .addComponent(jCBgeneroLibros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFtituloLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(1, 1, 1)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBgeneroLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTFunidadesLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFidLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Autor:"));

        jTFidAutor.setEditable(false);
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

        jLabel7.setText("Código de Autor:");

        jLabel8.setText("Nombre:");

        jTFnombreAutor.setEditable(false);

        jLabel11.setText("Fecha de Nacimiento:");

        jTFfechaNacimientoAutor.setEditable(false);

        jLabel21.setText("Filtrar por Nombre:");

        jLabel23.setText("Filtrar por Código:");

        jTFfiltradoAutorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFfiltradoAutorNombreActionPerformed(evt);
            }
        });
        jTFfiltradoAutorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFfiltradoAutorNombreKeyTyped(evt);
            }
        });

        jTFfiltradoCodigoAutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFfiltradoCodigoAutorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTFfiltradoAutorNombre))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel23))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFidAutor, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(jTFnombreAutor)
                            .addComponent(jTFfiltradoCodigoAutor)
                            .addComponent(jTFfechaNacimientoAutor))))
                .addGap(27, 27, 27))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTFfiltradoAutorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTFfiltradoCodigoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFnombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFfechaNacimientoAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFidAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBinsertarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBlimpiarInsertarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jBinsertarLibro)
                        .addGap(18, 18, 18)
                        .addComponent(jBlimpiarInsertarLibro)))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTBlibroGeneral.addTab("Insertar", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel17.setText("Titulo del Libro:");

        jTFtituloBorrar.setEditable(false);

        jLabel18.setText("Género:");

        jLabel19.setText("Unidades del Libro:");

        jLabel20.setText("Código del Libro:");

        jTFgeneroBorrar.setEditable(false);

        jTFunidadesBorrar.setEditable(false);

        jTFidLibroBorrar.setEditable(false);

        jTFfiltrarPorNombre1.setText("Filtrar por Nombre del Libro:");

        jLabel22.setText("Filtrar por ID del Libro:");

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

        jBborrarLibro.setText("Borrar");
        jBborrarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBborrarLibroActionPerformed(evt);
            }
        });

        jBlimpiarLibrosBorrar.setText("Limpiar los campos");
        jBlimpiarLibrosBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlimpiarLibrosBorrarActionPerformed(evt);
            }
        });

        jLabel25.setText("Código del Autor:");

        jTFidAutorBorrar.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTFfiltrarPorNombre1)
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFunidadesBorrar)
                    .addComponent(jTFtituloBorrar)
                    .addComponent(jTFlibroBorrarFiltrarNombre)
                    .addComponent(jTFidLibroBorrar)
                    .addComponent(jTFgeneroBorrar)
                    .addComponent(jTFidAutorBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFlibroBorrarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBborrarLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBlimpiarLibrosBorrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(105, 105, 105))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFtituloBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBborrarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTFgeneroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBlimpiarLibrosBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jTFunidadesBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTFidLibroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTFidAutorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jTBlibroGeneral.addTab("Borrar", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setText("Titulo del Libro:");

        jTFtituloEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFtituloEditarKeyTyped(evt);
            }
        });

        jLabel12.setText("Género:");

        jLabel13.setText("Unidades del Libro:");

        jLabel14.setText("Código del Libro:");

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

        jTFfiltrarPorNombre.setText("Filtrar por Nombre del Libro:");

        jLabel16.setText("Filtrar por ID del Libro:");

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

        jLabel24.setText("Código del Autor:");

        jTFidAutorEditar.setEditable(false);

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
                    .addComponent(jTFfiltrarPorNombre)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFtituloEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFlibroEditarFiltrarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFgeneroEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFunidadesEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFidLibroEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jTFidAutorEditar))
                .addGap(62, 62, 62)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFlibroEditarFiltrarID, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBactualizarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBlimpiarLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jBactualizarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTFtituloEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jTFgeneroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFunidadesEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBlimpiarLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFidLibroEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTFidAutorEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTBlibroGeneral.addTab("Editar", jPanel1);

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

        jTdatosAutor = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
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
                .addGap(220, 220, 220)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(205, 205, 205))
            .addGroup(layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTBlibroGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 982, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jTBlibroGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel5))
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

    private void jTFidAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFidAutorActionPerformed
        
    }//GEN-LAST:event_jTFidAutorActionPerformed

    private void jTFlibroEditarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroEditarFiltrarNombreActionPerformed
        
        
    }//GEN-LAST:event_jTFlibroEditarFiltrarNombreActionPerformed

    private void jTFlibroBorrarFiltrarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFlibroBorrarFiltrarNombreActionPerformed
    
    }//GEN-LAST:event_jTFlibroBorrarFiltrarNombreActionPerformed

    private void jBactualizarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBactualizarLibroActionPerformed
        TableModel model = jTdatosAutor.getModel();  
        int filaEncontrada=-1;
        for (int fila = 0; fila < model.getRowCount(); fila++) {
                    String idEnFila = model.getValueAt(fila, 2).toString();
                    if (idEnFila.equals(jTFidAutorEditar.getText())) {
                        filaEncontrada = fila;
                        break;
                    }
                }
        if(filaEncontrada!=-1){
        String nombre = model.getValueAt(filaEncontrada, 0).toString();
        fecha = new Fecha(model.getValueAt(filaEncontrada, 1).toString());
        int idAutor = Integer.parseInt(model.getValueAt(filaEncontrada, 2).toString());
        
                
        autor = new Autor(idAutor,nombre,fecha);
                        
        libro= new Libro(Integer.parseInt(this.jTFidLibroEditar.getText()),autor, Integer.parseInt(jTFunidadesEditar.getText()),jTFtituloEditar.getText(),jTFgeneroEditar.getText());
        

        int idLibro = Integer.parseInt(this.jTFidLibroEditar.getText());
        int nuevaCantidad = Integer.parseInt(jTFunidadesEditar.getText());
        String nuevoTitulo = jTFtituloEditar.getText();
        String nuevoGenero = jTFgeneroEditar.getText(); 
        libro.actualizarLibroEnBaseDeDatos(idLibro, nuevaCantidad, nuevoTitulo, nuevoGenero);
        mostrarTabla();    
        }
        limpiarCampos();
        filtrarTablaId("");
        filtrarTablaIdAutor("");
        filtrarTablaNombre("");
        filtrarTablaNombreAutor("");
    }//GEN-LAST:event_jBactualizarLibroActionPerformed

    private void jBborrarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBborrarLibroActionPerformed
        TableModel model = jTdatosAutor.getModel();
    int filaEncontrada=Integer.parseInt(jTFidAutorBorrar.getText());
    for (int fila = 0; fila < model.getRowCount(); fila++) {
                    String idEnFila = model.getValueAt(fila, 2).toString();
                    if (idEnFila.equals(jTFidAutorBorrar.getText())) {
                        filaEncontrada = fila;
                        break;
                    }
                }
    int idAutor =Integer.parseInt(model.getValueAt(filaEncontrada, 2).toString());
    String nombre = model.getValueAt(filaEncontrada, 0).toString();    
    fecha = new Fecha(model.getValueAt(filaEncontrada, 1).toString());           
    autor = new Autor(idAutor,nombre,fecha);         
    libro= new Libro(Integer.parseInt(jTFidLibroBorrar.getText()),autor,
    Integer.parseInt(jTFunidadesBorrar.getText()),jTFtituloBorrar.getText(),jTFgeneroBorrar.getText());
    int idLibroSeleccionado = Integer.parseInt(jTFidLibroBorrar.getText());
    try {
    if(idLibroSeleccionado != -1){
    // Verifica si el estudiante tiene préstamos pendientes
    if (libro.tienePrestamosPendientes(idLibroSeleccionado)) {
    JOptionPane.showMessageDialog(null, "El libro se encuentra en un préstamo pendientes y no puede ser eliminado.");
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
        filtrarTablaId("");
        filtrarTablaIdAutor("");
        filtrarTablaNombre("");
        filtrarTablaNombreAutor("");
        
    }//GEN-LAST:event_jBborrarLibroActionPerformed

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
            pps.setString(2,  (String)jCBgeneroLibros.getSelectedItem());
            pps.setInt(3, Integer.parseInt(jTFidLibro.getText()));
            pps.setInt(4, Integer.parseInt(jTFunidadesLibro.getText()));
            pps.setLong(5,Long.parseLong(jTFidAutor.getText()));
            
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados.");

            libro = new Libro( Integer.parseInt(jTFidLibro.getText()),autor,
            Integer.parseInt(jTFunidadesLibro.getText()), jTFtituloLibro.getText(),
            (String) jCBgeneroLibros.getSelectedItem());

            jTAlibroActual.setText(libro.toString());

            mostrarTabla();
           
        }
        catch (SQLException ex){
        JOptionPane.showMessageDialog(null, "Libro ya registrado.");
        }    
        }
        filtrarTablaId("");
        filtrarTablaIdAutor("");
        filtrarTablaNombre("");
        filtrarTablaNombreAutor("");
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
        limpiarCampos();
    }//GEN-LAST:event_jBlimpiarLibroEditarActionPerformed

    private void jBlimpiarLibrosBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimpiarLibrosBorrarActionPerformed
        limpiarCampos();
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

    private void jCBgeneroLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBgeneroLibrosActionPerformed

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

    private void jTFfiltradoAutorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFfiltradoAutorNombreActionPerformed
        
    }//GEN-LAST:event_jTFfiltradoAutorNombreActionPerformed

    private void jTFfiltradoAutorNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFfiltradoAutorNombreKeyTyped
         char caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter)&& caracter!=' '&&caracter!='\b') {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un autor de manera correcta.");
        }
    }//GEN-LAST:event_jTFfiltradoAutorNombreKeyTyped

    private void jTFfiltradoCodigoAutorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFfiltradoCodigoAutorKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Por favor ingrese un codigo de autor de manera correcta.");
        }
    }//GEN-LAST:event_jTFfiltradoCodigoAutorKeyTyped
    
    
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
    private javax.swing.JButton jBactualizarLibro;
    private javax.swing.JButton jBborrarLibro;
    private javax.swing.JButton jBinsertarLibro;
    private javax.swing.JButton jBlimpiarInsertarLibro;
    private javax.swing.JButton jBlimpiarLibroEditar;
    private javax.swing.JButton jBlimpiarLibrosBorrar;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTabbedPane jTBlibroGeneral;
    private javax.swing.JTextField jTFfechaNacimientoAutor;
    private javax.swing.JTextField jTFfiltradoAutorNombre;
    private javax.swing.JTextField jTFfiltradoCodigoAutor;
    private javax.swing.JLabel jTFfiltrarPorNombre;
    private javax.swing.JLabel jTFfiltrarPorNombre1;
    private javax.swing.JTextField jTFgeneroBorrar;
    private javax.swing.JTextField jTFgeneroEditar;
    private javax.swing.JTextField jTFidAutor;
    private javax.swing.JTextField jTFidAutorBorrar;
    private javax.swing.JTextField jTFidAutorEditar;
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
    private javax.swing.JTable jTdatosAutor;
    private javax.swing.JTable jTdatosLibro;
    // End of variables declaration//GEN-END:variables
}
