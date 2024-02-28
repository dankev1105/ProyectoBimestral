package Negocio;

public class Estudiante extends Persona{
    private int idEstudiante;
    private String correoInstitucionalEstudiante;

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

    @Override
    public String toString() {
        return super.toString()+"\nID Estudiante = " + idEstudiante;
    }  
}
