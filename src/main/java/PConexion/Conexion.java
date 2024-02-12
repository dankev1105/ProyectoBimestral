package PConexion;
//ESTA CLASE SOLO MANEJA EL MENSAJE DE SI SE HA CONECTADO O NO A LA BASE DE DATOS
import com.mycompany.biblioteca.JFBiblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
//127.0.0.1
public class Conexion {
    Connection conectar;
    String usuario = "root";
    String contrasenia = "Faba2004.";
    String baseDatos = "proyecto";
    String ipServidor = "127.0.0.1";
    String puerto = "3306";
    String cadena = "jdbc:mysql://"+ipServidor+":"+puerto+"/"+baseDatos;
    com.mycompany.biblioteca.JFBiblioteca ventanaLogin = new JFBiblioteca();    
    
    public Connection establecerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            ventanaLogin.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la conexión: "+e.toString());
        }
        return conectar;
    }
}
