package Negocio;

public class Libro {
    private long idLibro;
    private int unidadesLibro;
    private String tituloLibro,genero;
    

        public Libro(long idLibro, int unidadesLibro, String tituloLibro, String genero) {
        this.idLibro = idLibro;
        this.unidadesLibro = unidadesLibro;
        this.tituloLibro = tituloLibro;
        this.genero = genero;
      
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