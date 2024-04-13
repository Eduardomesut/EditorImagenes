
import java.util.Scanner;

public class Ejecutable_Imagenes {

    public static void main(String[] args) {
        System.out.println("Bienvenido al editor de fotografías");
        Scanner sc = new Scanner(System.in);
        System.out.print("Dime la ruta de la imagen (sin comillas): ");
        String ruta = sc.nextLine();
        int[][] imagen = ProcesadorImagenes.lectorArchivo(ruta);

        if (imagen != null) {
            ejecutarMenu(ruta, sc, imagen);
        } else {
            System.out.println("No se pudo cargar la imagen.");
        }
    }

    private static void ejecutarMenu(String ruta, Scanner sc, int[][] imagen) {
        String respuesta;
        do {
            System.out.println("-------------Menu-----------");
            System.out.println("1.- Aplicar filtro girar izquierda");
            System.out.println("2.- Aplicar filtro girar derecha");
            System.out.println("3.- Aplicar filtro flip horizontal");
            System.out.println("4.- Aplicar filtro flip vertical");
            System.out.println("5.- Aplicar filtro obtener negativo");
            System.out.println("6.- Aplicar filtro obtener filtro caja");
            System.out.println("G. Guardar fichero");
            System.out.println("S. Salir");

            respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {

                if (respuesta.equalsIgnoreCase("1")) {
                    imagen = ProcesadorImagenes.girarIzquierda(imagen);

                } else if (respuesta.equalsIgnoreCase("2")) {
                    imagen = ProcesadorImagenes.girarDerecha(imagen);
                } else if (respuesta.equalsIgnoreCase("3")) {
                    ProcesadorImagenes.flipHorizontal(imagen);
                } else if (respuesta.equalsIgnoreCase("4")) {
                    ProcesadorImagenes.flipVertical(imagen);
                } else if (respuesta.equalsIgnoreCase("5")) {
                    ProcesadorImagenes.aplicarNegativo(imagen, Ejecutable_Imagenes.calcularMaximo(imagen));
                } else if (respuesta.equalsIgnoreCase("6")) {
                    ProcesadorImagenes.aplicarFiltroCaja(imagen);
                } else if (respuesta.equalsIgnoreCase("G")) {
                    ProcesadorImagenes.guardarImagen(imagen, Ejecutable_Imagenes.calcularMaximo(imagen), sc);
                } else {
                    System.out.println("Introduce un número correcto");
                }

            }

        } while (!respuesta.equalsIgnoreCase("S"));
        System.out.println("Cerrando el programa.....");
    }

    private static int calcularMaximo(int[][] imagen) {
        int maximo = 0;
        for (int[] fila : imagen) {
            for (int valor : fila) {
                if (valor > maximo) {
                    maximo = valor;
                }
            }
        }
        return maximo;
    }
}

