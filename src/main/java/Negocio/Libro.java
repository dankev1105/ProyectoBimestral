package Negocio;

public class Libro {
    private long idLibro;
    private int unidadesLibro;
    private String tituloLibro,genero;
    Autor autor;

        public Libro(long idLibro, int unidadesLibro, String tituloLibro, String genero, Autor autor) {
        this.idLibro = idLibro;
        this.unidadesLibro = unidadesLibro;
        this.tituloLibro = tituloLibro;
        this.genero = genero;
        this.autor = autor;
    }

    public long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(long idLibro) {
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

   @Override
    public String toString() {
        return "Libro:\n" + 
                "id del Libro:" + idLibro + 
                "\nUnidades del Libro: " + unidadesLibro +
                "\nTitulo del Libro: " + tituloLibro + 
                "\nGenero:" + genero +
                "\nAutor:" + autor ;
}
}