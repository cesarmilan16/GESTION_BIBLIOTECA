import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import Modelo.Biblioteca;
import Modelo.Conexion;
import Modelo.LibroDAO;


public class App {

    // Establece una conexión estática a la base de datos utilizando la clase Conexion
    public static Connection conexion = Conexion.establecerConexion();
    // Crea una instancia estática de LibroDAO para realizar operaciones de base de datos relacionadas con libros
    public static LibroDAO libroDAO = new LibroDAO(conexion);
    // Crea una instancia estática de Biblioteca, que utiliza el objeto LibroDAO y la conexión a la base de datos
    public static Biblioteca biblioteca = new Biblioteca(libroDAO, conexion);

    // Método principal que es un bucle que solo acaba cuando salir = true
    public static void main(String[] args) throws Exception {
        boolean salir = false;
        while (!salir) {
            salir = mostrarApp();
        }
    }

    // Menú de la App para realizar los métodos convenientes
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