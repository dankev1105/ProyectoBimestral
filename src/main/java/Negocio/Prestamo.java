package Negocio;

import PConexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prestamo {
    private int idPrestamo, idLibro;
    Estudiante  idEstudiante;
    Fecha fechaPrestamo, fechaDevolucion;

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, int idLibro, Estudiante idEstudiante, Fecha fechaPrestamo, Fecha fechaDevolucion) {
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

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
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

    public void reducirUnidadesLibro() {//VERIFICAR EN LA BASE DE DATOS LAS UNIDADES
        Conexion con = new Conexion();
        Connection cn = con.establecerConexion();

        try {
            String sql = "UPDATE Libro SET UnidadesDisponibles = UnidadesDisponibles - 1 WHERE idLibro = ?";

            // Prepara la sentencia
            PreparedStatement ps = cn.prepareStatement(sql);

            // Establece el ID del libro en la sentencia
            ps.setInt(1, idLibro);

            // Ejecuta la sentencia
            ps.executeUpdate();

            System.out.println("Las unidades del libro han sido reducidas.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
