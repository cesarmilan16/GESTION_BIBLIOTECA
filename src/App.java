import java.sql.SQLException;
import java.util.Scanner;
import Modelo.Query;

public class App {

    public static Query query = new Query();
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

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                query.insertarLibro();
                break;
            case "2":
                query.eliminarLibro();
                break;/*
            case "3":
                query.actualizarLibro();
            case "4":
                query.buscarLibro();*/
            case "9":
                salir = true;
                break;
            default:
                break;
        }
        return salir;
    }
}