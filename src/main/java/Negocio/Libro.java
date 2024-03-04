package Negocio;

import PConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

public class Libro {
    private int idLibro;
    Autor autor;
    Conexion con=new Conexion();
    Connection cn=con.establecerConexion();
    private int unidadesLibro;
    private String tituloLibro,genero;

    public Libro(int idLibro, Autor autor, int unidadesLibro, String tituloLibro, String genero) {
        this.idLibro = idLibro;
        this.autor = autor;
        this.unidadesLibro = unidadesLibro;
        this.tituloLibro = tituloLibro;
        this.genero = genero;
    }

    public Autor getIdAutor() {
        return autor;
    }

    public void setIdAutor(Autor idAutor) {
        this.autor = idAutor;
    }

    public long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getUnidadesLibro() {
        return unidadesLibro;
    }

    public void setUnidadesLibro(int unidadesLibro) {
        this.unidadesLibro = unidadesLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public boolean existeLibro(int idLibro) {
        String query = "SELECT * FROM Libro WHERE IdLibro = ?";

        try (PreparedStatement st = cn.prepareStatement(query)) {
        st.setLong(1, idLibro);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
        return true; 
        } else {
        return false;
        }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del libro: " + ex.toString());
        }
        return false;
        }
           
        public void actualizarLibroEnBaseDeDatos(int idLibro, int nuevaCantidad, String nuevoTitulo, String nuevoGenero){     
        try {
        // Utilizar INNER JOIN para actualizar el libro
        String query = "UPDATE Libro "
                     + "INNER JOIN Autor ON Libro.IdAutor = Autor.IdAutor "
                     + "SET Libro.UnidadesDisponibles = ?, Libro.NombreLibro = ?, Libro.Genero = ? "
                     + "WHERE Libro.IdLibro = ?";

            try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, nuevaCantidad);
            st.setString(2, nuevoTitulo);
            st.setString(3, nuevoGenero);
            st.setInt(4, idLibro);

            int filasActualizadas = st.executeUpdate();

            if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Libro actualizado correctamente");
            } else {
            JOptionPane.showMessageDialog(null, 
            "No se pudo actualizar el libro. Verifica la existencia del libro en la base de datos.");
            }
            }
            } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Error de integridad: " + ex.getMessage());
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el libro en la base de datos: " 
            + ex.toString());
            }
            }
        
            public void eliminarLibroEnBaseDeDatos(long idLibro) {
            String query = "DELETE FROM Libro WHERE IdLibro = ?";
            try (PreparedStatement st = cn.prepareStatement(query)) {
            st.setLong(1, idLibro);
            st.executeUpdate();                     
            JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
            }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante de la base de datos: " + ex.toString());
            }catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Error: intento de acceder a un índice fuera de los límites.");
            }
            }
    
        public boolean tienePrestamosPendientes(long idEstudiante) {
            String sql = "SELECT COUNT(*) FROM Prestamo WHERE IdLibro = ?";
            Conexion cn = new Conexion();
            Connection conexion = cn.establecerConexion();
            try {
                PreparedStatement preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setLong(1, idEstudiante);
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
        return "Libro:\n" + 
                "id del Libro:" + idLibro + 
                "\nUnidades del Libro: " + unidadesLibro +
                "\nAutor: " + autor.getNombre() +
                "\nTitulo del Libro: " + tituloLibro + 
                "\nGénero:" + genero 
               ;
}
}