package PConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    Connection conectar;
    String usuario = "root";
    String contrasenia = "Alvarit@123";
    String baseDatos = "biblioteca";
    String ipServidor = "127.0.0.1";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + ipServidor + ":" + puerto + "/" + baseDatos;

    public Connection establecerConexion() {
        try {
            // Cargar el controlador de MySQL Connector/J
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la conexión: " + e.toString());
        }
        return conectar;
    }
}