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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero correspondiente a la opcion que desea ingresar");
        System.out.println("1. Encontrar el Centro del Grafo");
        System.out.println("2. Ingresar una nueva ruta");
        System.out.println("3. Reportar un bloqueo");
        System.out.println("4. Salir del programa");
        int mainSelect = scanner.nextInt();
        switch (mainSelect){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Elija una opcino valida");
                break;
        }
    }
}
