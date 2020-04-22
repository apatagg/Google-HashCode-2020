import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        longanizaCodigo("a_example");
        longanizaCodigo("b_read_on");
        longanizaCodigo("c_incunabula");
        longanizaCodigo("d_tough_choices");
        longanizaCodigo("e_so_many_books");
        longanizaCodigo("f_libraries_of_the_world");
    }

    public static void longanizaCodigo(String filename){
//Leer archivo
        try {
            File myObj = new File("archivos/" + filename + ".txt");
            Scanner myReader = new Scanner(myObj);

            //Leemos libros
            int totalLibros, totalLibrerias, totalDias;
            String data = myReader.nextLine();
            String[] partes = data.split(" ");
            totalLibros = Integer.parseInt(partes[0]);
            totalLibrerias = Integer.parseInt(partes[1]);
            totalDias = Integer.parseInt(partes[1]);

            data = myReader.nextLine();
            partes = data.split(" ");
            Libro[] todosLibros = new Libro[totalLibros];
            for (int i = 0; i < totalLibros; i++) {
                Libro libro = new Libro(i, Integer.parseInt(partes[i]));
                todosLibros[i] = libro;
            }

            Libreria[] todasLibrerias = new Libreria[totalLibrerias];
            for (int i = 0; i < totalLibrerias; i++) {
                data = myReader.nextLine();
                partes = data.split(" ");
                totalLibros = Integer.parseInt(partes[0]);
                int tiempoRegistro = Integer.parseInt(partes[1]);
                Libreria libreria = new Libreria(i,tiempoRegistro, totalLibros, tiempoRegistro);

                data = myReader.nextLine();
                partes = data.split(" ");

                for (int j = 0; j < totalLibros; j++) {
                    for (int x = 0; x < todosLibros.length; x++) {
                        if (todosLibros[x].getIdLibro() == Integer.parseInt(partes[j])) {
                            libreria.addLibro(todosLibros[x]);
                        }
                    }
                }
                todasLibrerias[i] = libreria;
            }


            Libreria mejorLibreria;
            Hashtable<Integer, Libreria> ordenLibrerias = new Hashtable<Integer, Libreria>();
            for (int i = 0; i < todasLibrerias.length; i++) {
                int peso = 0;
                Libreria libreria = todasLibrerias[i];
                peso = libreria.getTotalLibros() / libreria.getLibrosPorDia();
                ordenLibrerias.put(peso, todasLibrerias[i]);
            }

            List<Integer> tmp = Collections.list(ordenLibrerias.keys());
            Collections.sort(tmp);
            Iterator<Integer> llaves = ordenLibrerias.keySet().iterator();

            int i = 0;
            int totalTiempo = 0;
            Libreria[] resultadoLibrerias = new Libreria[totalLibrerias];
            while(llaves.hasNext()){
                Integer element = llaves.next();
                Libreria libTemp = ordenLibrerias.get(element);
                int factor = (libTemp.getTotalLibros()/libTemp.getLibrosPorDia());
                if(factor < totalDias ) {
                    resultadoLibrerias[i] = ordenLibrerias.get(element);
                    i++;
                    totalTiempo += factor;
                }

                //here you can get ordered things: 'your_hashtable'.get(element);
            }


            //Escribimos el archivo
            FileWriter myWriter = new FileWriter("archivos/" + filename + "_out.txt");

            int k = 0;
            for (Libreria libreria: resultadoLibrerias) {
                if (libreria != null) {
                    k++;
                }
            }

            myWriter.write(k + "\n");

            for (Libreria libreria: resultadoLibrerias) {
                if(libreria != null) {
                    myWriter.write(libreria.getIdLibreria() + " " + libreria.getTotalLibros() + "\n");
                    Libro[] libros = libreria.getLibros();
                    for (Libro libro : libros) {
                        myWriter.write(libro.getIdLibro() + " ");
                    }
                    myWriter.write("\n");
                }
            }
            System.out.println("Acabado " + filename);
            myWriter.close();
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
