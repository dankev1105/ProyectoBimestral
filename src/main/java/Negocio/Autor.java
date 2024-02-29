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
    
    @Override
    public String toString() {
        return super.toString()+"\nidAutor = " + this.idAutor;
    }
}
