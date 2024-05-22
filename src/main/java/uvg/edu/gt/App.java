package uvg.edu.gt;
import java.util.Scanner;

/**
 * Este programa se utiliza para encontrar las rutas mas cortas al igual que la ubicacion central de un grafo
 * que representa ciudades de Guatemala. Estas se leen de un archivo .txt llamado "guategrafo", se cargan a un
 * DirectedGraph y se utiliza el algoritmo de Floyd para encontrar las rutas mas cortas entre cualesquiera dos
 * ciudades al igual que el centro.
 * @author Jose Merida
 * @since 21-05-2024
 * @version 1.0
 */
public class App
{
    public static void main( String[] args )
    {
        //Se crea un nuevo Model
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);
        //Menu principal
        while (true) {
            System.out.println("Ingrese el numero correspondiente a la opcion que desea ingresar");
            System.out.println("1. Encontrar el Centro del Grafo");
            System.out.println("2. Ingresar una nueva ruta");
            System.out.println("3. Reportar un bloqueo");
            System.out.println("4. Encontrar la ruta mas corta");
            System.out.println("5. Salir del programa");
            int mainSelect = scanner.nextInt();
            scanner.nextLine();
            switch (mainSelect) {
                case 1:
                    //Imprime el centro del grafo
                    System.out.println(model.getCenter());
                    break;
                case 2:
                    //Lee input del usuario e ingresa una nueva ruta al grafo
                    System.out.println("Ingrese la salida");
                    String salida = scanner.nextLine();
                    System.out.println("Ingrese el destino");
                    String destino = scanner.nextLine();
                    System.out.println("Ingrese la distancia");
                    int distancia = scanner.nextInt();
                    scanner.nextLine();
                    model.addPath(salida, destino, distancia);
                    break;
                case 3:
                    //Lee input del usuario y remueve una ruta
                    System.out.println("Ingrese la salida del bloqueo");
                    String salidaBloqueo = scanner.nextLine();
                    System.out.println("Ingrese el destino del bloqueo");
                    String destinoBloqueo = scanner.nextLine();
                    model.blockRoute(salidaBloqueo, destinoBloqueo);
                    break;
                case 4:
                    //Lee input del usuario y agrega una ruta
                    System.out.println("Ingrese la salida");
                    String salidaRuta = scanner.nextLine();
                    System.out.println("Ingrese el destino");
                    String destinoRuta = scanner.nextLine();
                    System.out.println(model.getPath(salidaRuta, destinoRuta));
                    break;
                case 5:
                    //Sale del programa
                    System.exit(0);
                    break;
                default:
                    System.out.println("Elija una opcino valida");
                    break;
            }
        }
    }
}