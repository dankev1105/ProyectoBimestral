package Negocio;

public class Autor extends Persona {
    private long idAutor;

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
        return super.toString()+"\nidAutor = " + idAutor+"HOLAMUNDO";
    }
}
