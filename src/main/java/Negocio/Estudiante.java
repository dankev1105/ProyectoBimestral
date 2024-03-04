package Negocio;

import PConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Estudiante extends Persona{
    private int idEstudiante;
    private String correoInstitucionalEstudiante;
    Conexion con = new Conexion();
    Connection cn = con.establecerConexion();      
    
    public Estudiante(int idEstudiante, String correoInstitucionalEstudiante, String nombre, Fecha fechaNacimiento) {
        super(nombre, fechaNacimiento);
        this.idEstudiante = idEstudiante;
        this.correoInstitucionalEstudiante = correoInstitucionalEstudiante;
    }

    public long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getCorreoInstitucionalEstudiante() {
        return correoInstitucionalEstudiante;
    }

    public void setCorreoInstitucionalEstudiante(String correoInstitucionalEstudiante) {
        this.correoInstitucionalEstudiante = correoInstitucionalEstudiante;
    }
    
    public void actualizarEstudianteEnBaseDeDatos(int idEstudiante, String nuevoNombre, String nuevaFechaNacimiento, String nuevoCorreoInstitucional) {
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
            
            JOptionPane.showMessageDialog(null, "Estudiate actualizado con exito ");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estudiante en la base de datos: " + ex.toString());
        }
    }
    
    public void eliminarEstudianteEnBaseDeDatos(int idEstudiante) {
        String query = "DELETE FROM Estudiante WHERE IdEstudiante = ?";
        try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idEstudiante);
            st.executeUpdate();                     
            JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante de la base de datos: " + ex.toString());
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites");
        }
    }
    
    public boolean tienePrestamosPendientes(int idEstudiante) {
        String sql = "SELECT COUNT(*) FROM Prestamo WHERE IdEstudiante = ?";
        Conexion cn = new Conexion();
        Connection conexion = cn.establecerConexion();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idEstudiante);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString()+"\nCódigo de Estudiante: " + idEstudiante;
    }  
}
