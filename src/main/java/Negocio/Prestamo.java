package Negocio;
import PConexion.Conexion;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Prestamo {
    private int idPrestamo, idLibro,idEstudiante;
    Fecha fechaPrestamo, fechaDevolucion;

    public Prestamo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.fechaPrestamo = new Fecha(formatter.format(date));
    }

    public Prestamo(int idPrestamo, int idLibro, int idEstudiante, Fecha fechaPrestamo, Fecha fechaDevolucion) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Fecha getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Fecha fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Fecha getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Fecha fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    public void verificarTiempoPrestamo() {
        Fecha currentDate = new Fecha(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

        if (currentDate.getAnio() > fechaDevolucion.getAnio() ||
            (currentDate.getAnio() == fechaDevolucion.getAnio() && currentDate.getMes() > fechaDevolucion.getMes()) ||
            (currentDate.getAnio() == fechaDevolucion.getAnio() && currentDate.getMes() == fechaDevolucion.getMes() && currentDate.getDia() > fechaDevolucion.getDia())) {
            System.out.println("El préstamo ha excedido el tiempo límite.");
        } else {
            System.out.println("El préstamo está dentro del tiempo límite.");
        }
    }

    public void reducirUnidadesLibro(int idLibro) {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();

        try {
            java.lang.String sql = "UPDATE Libro SET UnidadesDisponibles = UnidadesDisponibles - 1 WHERE idLibro = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idLibro);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aumentarUnidadesLibro(int idLibro) {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();

        try {
            java.lang.String sql = "UPDATE Libro SET UnidadesDisponibles = UnidadesDisponibles + 1 WHERE idLibro = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idLibro); 
            int rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void añadirRegistro() {
        int idPrestamo = generarIdPrestamoUnico();
        setIdPrestamo(idPrestamo);

        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();
        PreparedStatement pstmt = null;

        try {
            java.lang.String sql = "INSERT INTO Prestamo (IdPrestamo, IdEstudiante, IdLibro, FechaPrestamo, FechaDevolucion) VALUES (?, ?, ?, ?, ?)";
            pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, this.idPrestamo);
            pstmt.setInt(2, this.idEstudiante);
            pstmt.setInt(3, this.idLibro);
            pstmt.setDate(4, java.sql.Date.valueOf(this.fechaPrestamo.toString())); 
            pstmt.setDate(5, java.sql.Date.valueOf(this.fechaDevolucion.toString()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
            if (cn != null) {
                try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
    
    public void eliminarRegistro(int idPrestamo) {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();

        try {
            java.lang.String sql = "DELETE FROM Prestamo WHERE IdPrestamo = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idPrestamo);
            int rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean obtenerUnidadesLibro() {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            java.lang.String sql = "SELECT unidades FROM Libro WHERE IdLibro = ?";
            pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, this.idLibro);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int unidades = rs.getInt("unidades");
                return unidades > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (cn != null) try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public int obtenerIdLibro() {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();

        try {
            java.lang.String sql = "SELECT IdLibro FROM Libro LIMIT 1";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("IdLibro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
    
    public boolean prestamoExiste(int idPrestamo) {
        return existe(idPrestamo);
    }
    public int generarIdPrestamoUnico() {
        Random rand = new Random();
        int idPrestamo = rand.nextInt(1000000);
        while (prestamoExiste(idPrestamo)) {
            idPrestamo = rand.nextInt(1000000);
        }
        return idPrestamo;
    }

    private boolean existe(int idPrestamo) {
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            java.lang.String sql = "SELECT * FROM Prestamo WHERE IdPrestamo = ?";
            pstmt = cn.prepareStatement(sql);
            pstmt.setInt(1, idPrestamo);
            rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (cn != null) try { cn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}