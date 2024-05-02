/*package Modelo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private LibroDAO libroDAO;
    private Scanner scanner;

    public BibliotecaApp(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    insertarLibro();
                    break;
                case 2:
                    eliminarLibro();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    buscarLibro();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("***************************");
        System.out.println("********* Biblioteca ******");
        System.out.println("***************************");
        System.out.println("1. Insertar libro");
        System.out.println("2. Eliminar libro");
        System.out.println("3. Actualizar libro");
        System.out.println("4. Buscar libro");
        System.out.println("5. Salir");
        System.out.println("***************************");
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Introduzca un número entero: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void insertarLibro() {
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("***************************");
        System.out.println("****** Insertar libro *****");
        System.out.println("***************************");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        boolean disponible = leerBoolean("¿Disponible? (Sí/No): ");
        Libro libro = new Libro(0, titulo, autor, genero, disponible);
        try {
            libroDAO.insertarLibro(libro);
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    private void eliminarLibro() {
        System.out.println("***************************");
        System.out.println("****** Eliminar libro *****");
        System.out.println("***************************");
        int id = leerEntero("Ingrese el ID del libro que desea eliminar: ");
        try {
            libroDAO.eliminarLibro(id);
            System.out.println("Libro eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    private void actualizarLibro() {
        System.out.println("***************************");
        System.out.println("**** Actualizar libro *****");
        System.out.println("***************************");
        int id = leerEntero("Ingrese el ID del libro que desea actualizar: ");
        try {
            List<Libro> librosEncontrados = libroDAO.buscarLibro("id", String.valueOf(id));
            if (librosEncontrados.isEmpty()) {
                System.out.println("No se encontró ningún libro con ese ID.");
                return;
            }
            Libro libro = librosEncontrados.get(0);
            System.out.println("Libro encontrado:");
            System.out.println(libro);
            scanner.nextLine(); // Consumir el salto de línea pendiente
            System.out.print("Nuevo título (o Enter para dejarlo igual): ");
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libro.setTitulo(nuevoTitulo);
            }
            System.out.print("Nuevo autor (o Enter para dejarlo igual): ");
            String nuevoAutor = scanner.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libro.setAutor(nuevoAutor);
            }
            System.out.print("Nuevo género (o Enter para dejarlo igual): ");
            String nuevoGenero = scanner.nextLine();
            if (!nuevoGenero.isEmpty()) {
                libro.setGenero(nuevoGenero);
            }
            boolean nuevoDisponible = leerBoolean("¿Nuevo estado de disponibilidad? (Sí/No): ");
            libro.setDisponible(nuevoDisponible);
            libroDAO.actualizarLibro(libro);
            System.out.println("Libro actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    private void buscarLibro() {
        System.out.println("***************************");
        System.out.println("****** Buscar libro *******");
        System.out.println("***************************");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por título");
        System.out.println("3. Buscar por autor");
        System.out.println("4. Buscar por género");
        System.out.println("5. Buscar por disponibilidad");
        int opcion = leerEntero("Seleccione una opción de búsqueda: ");
        String columna = "";
        switch (opcion) {
            case 1:
                columna = "id";
                break;
            case 2:
                columna = "titulo";
                break;
            case 3:
                columna = "autor";
                break;
            case 4:
                columna = "genero";
                break;
            case 5:
                columna = "disponible";
                break;
            default:
                System.out.println("Opción de búsqueda no válida.");
                return;
        }
        String valor = scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Ingrese el valor a buscar: ");
        valor = scanner.nextLine();
        try {
            List<Libro> librosEncontrados = libroDAO.buscarLibro(columna, valor);
            if (librosEncontrados.isEmpty()) {
                System.out.println("No se encontraron libros con ese criterio de búsqueda.");
            } else {
                System.out.println("Libros encontrados:");
                for (Libro libro : librosEncontrados) {
                    System.out.println(libro);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
        }
    }

    private boolean leerBoolean(String mensaje) {
        System.out.print(mensaje);
        String respuesta = scanner.nextLine();
        return respuesta.equalsIgnoreCase("sí") || respuesta.equalsIgnoreCase("si");
    }
}*/