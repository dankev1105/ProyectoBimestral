package Negocio;

public class Libro {
    private int idLibro;
    Autor idAutor;
    private int unidadesLibro;
    private String tituloLibro,genero;

    public Libro(int idLibro, Autor idAutor, int unidadesLibro, String tituloLibro, String genero) {
        this.idLibro = idLibro;
        this.idAutor = idAutor;
        this.unidadesLibro = unidadesLibro;
        this.tituloLibro = tituloLibro;
        this.genero = genero;
    }

    public Autor getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Autor idAutor) {
        this.idAutor = idAutor;
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

   @Override
    public String toString() {
        return "Libro:\n" + 
                "id del Libro:" + idLibro + 
                "\nUnidades del Libro: " + unidadesLibro +
                "\nTitulo del Libro: " + tituloLibro + 
                "\nGenero:" + genero 
               ;
}
}