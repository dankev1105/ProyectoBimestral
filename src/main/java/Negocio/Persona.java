package Negocio;

public abstract class Persona {
    private String nombre;
    private Fecha fechaNacimiento;

    public Persona(String nombre, Fecha fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "\nNombre: " + this.nombre + "\nFecha de Nacimiento: " + this.fechaNacimiento;
    } 
}