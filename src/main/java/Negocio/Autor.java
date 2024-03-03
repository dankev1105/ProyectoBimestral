package Negocio;

import PConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Autor extends Persona {
    private long idAutor;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();

    public Autor(long idAutor, String nombre, Fecha fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.idAutor = idAutor;
    }    

    public long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(long idAutor) {
        this.idAutor = idAutor;
    }
    
    @Override
    public String toString() {
        return super.toString()+"\nidAutor = " + this.idAutor;
    }
}
