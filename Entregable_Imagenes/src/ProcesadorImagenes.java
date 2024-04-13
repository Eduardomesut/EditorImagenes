



import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ProcesadorImagenes {

    public static int[][] lectorArchivo(String ruta) {
        int columna;
        int fila;
        int[][] imagen;
        try (Scanner scFile = new Scanner(new File(ruta))) {
            if (scFile.next().equalsIgnoreCase("P2")) {

                System.out.println("Bien");
                scFile.nextLine();
                String[] datos;
                scFile.nextLine();
                columna = scFile.nextInt();
                fila = scFile.nextInt();
                imagen = new int[fila][columna];
                System.out.println(columna);
                System.out.println(fila);

                scFile.nextLine();
                scFile.nextLine();

                for (int i = 0; i < fila; i++) {
                    for (int j = 0; j < columna; j++) {
                        if (scFile.hasNextInt()) {
                            imagen[i][j] = scFile.nextInt();
                        }
                    }
                }
                return imagen;

            } else {
                System.out.println("Mal");
                System.out.println(scFile.next());
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }



    public static int[][] girarIzquierda(int[][] imagen) {
        System.out.println("Girando imagen a la izquierda......");
        int filasNuevas = imagen[0].length; // Las filas nuevas serán las columnas originales
        int columnasNuevas = imagen.length; // Las columnas nuevas serán las filas originales
        int[][] imagenGirada = new int[filasNuevas][columnasNuevas];

        for (int fila = 0; fila < imagen.length; fila++) {
            for (int columna = 0; columna < imagen[fila].length; columna++) {
                // Ajuste en la asignación de índices para el giro hacia la izquierda
                imagenGirada[imagen[0].length - 1 - columna][fila] = imagen[fila][columna];
            }
        }

        return imagenGirada;
    }

    public static int[][] girarDerecha(int[][] imagen) {
        // Implementación similar a la original
        System.out.println("Girando imagen a la derecha......");
        int filasNuevas = imagen[0].length;
        int columnasNuevas = imagen.length;
        int[][] imagenGirada = new int[filasNuevas][columnasNuevas];

        for (int fila = 0; fila < imagen.length; fila++) {
            for (int columna = 0; columna < imagen[fila].length; columna++) {
                imagenGirada[columna][imagen.length - 1 - fila] = imagen[fila][columna];
            }
        }

        return imagenGirada;

    }

    public static void flipHorizontal(int[][] imagen) {
        // Implementación similar a la original

        System.out.println("Has elegido aplicar el filtro flip horizontal");
        System.out.println("Aplicando filtro........");

        int temp;
        for (int i = 0; i < imagen.length; i++) {
            for (int j = 0; j < imagen[i].length / 2; j++) {
                temp = imagen[i][j];
                imagen[i][j] = imagen[i][imagen[i].length - 1 - j];
                imagen[i][imagen[i].length - 1 - j] = temp;
            }
        }
    }

    public static void flipVertical(int[][] imagen) {
        // Implementación similar a la original
        System.out.println("Has elegido aplicar el filtro flip vertical");
        System.out.println("Aplicando filtro........");

        int temp;
        for (int i = 0; i < imagen.length / 2; i++) {
            for (int j = 0; j < imagen[i].length; j++) {
                temp = imagen[i][j];
                imagen[i][j] = imagen[imagen.length - 1 - i][j];
                imagen[imagen.length - 1 - i][j] = temp;
            }
        }
    }

    public static void aplicarNegativo(int[][] imagen, int maximo) {
        // Implementación similar a la original
        System.out.println("Has elegido aplicar el filtro negativo a la imagen");
        System.out.println("Aplicando filtro........");

        for (int filas = 0; filas < imagen.length; filas++) {
            for (int columnas = 0; columnas < imagen[filas].length; columnas++) {

                imagen[filas][columnas] = maximo - imagen[filas][columnas];

            }

        }
    }

    public static void aplicarFiltroCaja(int[][] imagen) {
        // Implementación similar a la original
        System.out.println("Has elegido aplicar el filtro a la imagen");
        System.out.println("Aplicando filtro........");

        int media;
        int sumaVecinos;

        for (int filas = 0; filas < imagen.length; filas++) {
            for (int columnas = 0; columnas < imagen[filas].length; columnas++) {

                imagen[0][0] = (imagen[0][0] + imagen[0][1] + imagen[1][0] + imagen[1][1]) / 4;
                imagen[0][imagen[filas].length - 1] = (imagen[0][imagen[filas].length - 1] + imagen[0][imagen[filas].length - 2] + imagen[1][imagen[filas].length - 1]
                        + imagen[1][imagen[filas].length - 2]) / 4;
                imagen[imagen.length - 1][0] = (imagen[imagen.length - 1][0] + imagen[imagen.length - 2][0] + imagen[imagen.length - 1][1] + imagen[imagen.length - 2][1]) / 4;
                imagen[imagen.length - 1][imagen[filas].length - 1] = (imagen[imagen.length - 1][imagen[filas].length - 1] + imagen[imagen.length - 1][imagen[filas].length - 2] + imagen[imagen.length - 2][imagen[filas].length - 1] + imagen[imagen.length - 2][imagen[filas].length - 2]) / 4;


//                imagen [imagen.length - 1][columnas] = 255;
//                imagen [filas][0] =255;
//                imagen[filas][imagen[filas].length - 1] = 255;
//
            }

        }

        for (int filas = 1; filas < imagen.length - 1; filas++) {
            for (int columnas = 1; columnas < imagen[filas].length -1 ; columnas++) {

                imagen[filas][columnas] = (imagen[filas][columnas] + imagen[filas][columnas -1] + imagen[filas][columnas + 1] + imagen[filas + 1][columnas]
                        + imagen[filas - 1][columnas] + imagen[filas +1][columnas - 1] + imagen[filas - 1][columnas - 1] + imagen[filas - 1][columnas + 1]
                        + imagen[filas + 1][columnas + 1])/9;

            }
        }

        for (int filas = 0; filas < imagen.length; filas++) {
            for (int columnas = 1; columnas < imagen[filas].length - 1; columnas++) {

                imagen[0][columnas] = (imagen[0][columnas] + imagen[0][columnas - 1] + imagen[0][columnas +1] + imagen[1][columnas] + imagen[1][columnas -1]
                        + imagen[1][columnas + 1])/6;

            }

        }
    }

    public static void guardarImagen(int[][] editado, int maximo, Scanner sc) {
        // Implementación similar a la original

        String nombre;

        int columna = editado[0].length;
        int fila = editado.length;

        System.out.println("Dame el nombre para guardar tu imagen editada (acabada en .pgm)");
        nombre = sc.nextLine();

        File f = new File("./FotosEditadas/" + nombre);
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write("P2" + "\n");
            fw.write("# " + nombre + "\n");
            fw.write(columna + " " + fila + "\n");
            fw.write(maximo + "\n");

            for (int filas = 0; filas < editado.length; filas++) {
                for (int columnas = 0; columnas < editado[filas].length; columnas++) {

                    fw.write(editado[filas][columnas] + " ");

                }
                fw.write("\n");
            }

        } catch (Exception e) {
            System.out.println("Archivo existente, quieres sobreescribirlo o añadir datos al final?");
        }

    }

}

