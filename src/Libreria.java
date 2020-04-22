public class Libreria {
    Libro[] libros;
    int tiempoRegistro;
    int librosPorDia;
    int idLibreria;

    public Libreria(int idLibreria, int tiempoRegistro, int totalLibros, int librosPorDia){
        this.tiempoRegistro = tiempoRegistro;
        this.libros = new Libro[totalLibros];
        this.librosPorDia = librosPorDia;
        this.idLibreria = idLibreria;
    }

    public void addLibro(Libro libro){
        boolean encontrado = false;
        for(int i = 0; i < libros.length; i++){
            if(libros[i]==null && encontrado != true){
                libros[i] = libro;
                encontrado = true;
            }
        }
    }

    public int getLibrosPorDia(){
        return librosPorDia;
    }

    public int getTotalLibros(){
        return libros.length;
    }

    public int getIdLibreria(){
        return idLibreria;
    }

    public Libro[] getLibros(){
        return libros;
    }

}
