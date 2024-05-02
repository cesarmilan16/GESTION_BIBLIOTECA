import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Modelo.Biblioteca;
import Modelo.Conexion;
import Modelo.LibroDAO;


public class App {

    public static Connection conexion = Conexion.establecerConexion();
    public static LibroDAO libroDAO = new LibroDAO(conexion);
    public static Biblioteca biblioteca = new Biblioteca(libroDAO, conexion);
    public static void main(String[] args) throws Exception {
        boolean salir = false;
        while (!salir) {
            salir = mostrarApp();
        }
    }

    private static boolean mostrarApp() throws SQLException {
        boolean salir = false;
        System.out.println("*****************************");
        System.out.println("*******Menu principal********");
        System.out.println("*****************************");
        System.out.println("1.- Insertar libro");
        System.out.println("2.- Eliminar libro");
        System.out.println("3.- Actualizar libro");
        System.out.println("4.- Buscar libro");
        System.out.println("9.- Salir");

        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                biblioteca.insertarLibro();
                break;
            case "2":
                biblioteca.eliminarLibro();
                break;
            case "3":
                biblioteca.actualizarLibro();
                break;
            case "4":
                biblioteca.buscarLibro();
                break;
            case "9":
                salir = true;
                scanner.close();
                Conexion.cerrarConexion();
                break;
            default:
                break;
        }
        return salir;
    }
}