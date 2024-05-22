package uvg.edu.gt;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Model model = new Model();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese el numero correspondiente a la opcion que desea ingresar");
            System.out.println("1. Encontrar el Centro del Grafo");
            System.out.println("2. Ingresar una nueva ruta");
            System.out.println("3. Reportar un bloqueo");
            System.out.println("4. Encontrar la ruta mas corta");
            System.out.println("5. Salir del programa");
            int mainSelect = scanner.nextInt();
            switch (mainSelect) {
                case 1:
                    System.out.println(model.getCenter());
                    break;
                case 2:
                    System.out.println("Ingrese la salida");
                    String salida = scanner.nextLine();
                    System.out.println("Ingrese el destino");
                    String destino = scanner.nextLine();
                    System.out.println("Ingrese la distancia");
                    int distancia = scanner.nextInt();
                    break;
                case 3:
                    System.out.println("Ingrese la salida del bloqueo");
                    String salidaBloqueo = scanner.nextLine();
                    System.out.println("Ingrese el destino del bloqueo");
                    String destinoBloqueo = scanner.nextLine();
                    model.blockRoute(salidaBloqueo, destinoBloqueo);
                    break;
                case 4:
                    System.out.println("Ingrese la salida");
                    String salidaRuta = scanner.nextLine();
                    System.out.println("Ingrese el destino");
                    String destinoRuta = scanner.nextLine();
                    //Implementar la salida
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Elija una opcino valida");
                    break;
            }
        }
    }
}