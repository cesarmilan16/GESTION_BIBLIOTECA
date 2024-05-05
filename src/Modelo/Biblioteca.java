package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Herramientas.Utilidades;

public class Biblioteca {
    // Definimos los atributos de la clase
    private LibroDAO libroDAO;
    private Scanner scanner = new Scanner(System.in);

    // Constructor que establece la conexión al instanciar el objeto
    public Biblioteca(LibroDAO libroDAO, Connection conexion) {
        this.libroDAO = libroDAO;
    }

    // Método para insertar libros
    public void insertarLibro() {
        // Ingresamos los datos del libro
        System.out.println("***************************");
        System.out.println("****** Insertar libro *****");
        System.out.println("***************************");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        boolean disponible = Utilidades.leerBoolean("¿Disponible? (1/0): ");
        // Creamos un nuevo libro con los datos introducidos
        Libro libro = new Libro(0, titulo, autor, genero, disponible);
        try {
            // Tratamos de añadir ese libro a la base de datos
            libroDAO.insertarLibro(libro);
            System.out.println("Libro insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        }
    }

    // Método para eliminar un libro de la base de datos
    public void eliminarLibro() {
        System.out.println("***************************");
        System.out.println("****** Eliminar libro *****");
        System.out.println("***************************");
        // Ingresamos el ID del libro a eliminar
        int id = Utilidades.leerNum("Ingrese el ID del libro que desea eliminar: ");
        try {
            // Creamos una lista de Libro donde se añadira un libro si tiene esa id
            ArrayList<Libro> librosEncontrados = libroDAO.buscarLibro("id", String.valueOf(id));
            if (!librosEncontrados.isEmpty()) {
                // Si finalmente se encuentra en la BD se eliminará de esta
                libroDAO.eliminarLibro(id);
                System.out.println("Libro eliminado correctamente.");
            }
            else {
                // Si no saltará el siguiente mensaje
                System.out.println("Libro no encontrado.");
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al eliminar el libro
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    // Método para actualizar el libro
    public void actualizarLibro() throws SQLException {
        boolean salir = false;
        System.out.println("***************************");
        System.out.println("**** Actualizar libro *****");
        System.out.println("***************************");
        // Solicita al usuario que ingrese el ID del libro que desea actualizar
        int id = Utilidades.leerNum("Ingrese el ID del libro que desea actualizar: ");
        try {
            // Busca en la base de datos el libro con el ID proporcionado
            ArrayList<Libro> librosEncontrados = libroDAO.buscarLibro("id", String.valueOf(id));
            if (librosEncontrados.isEmpty()) {
                // Si no se encuentra ningún libro con el ID proporcionado, muestra un mensaje de error y regresa
                System.out.println("No se encontró ningún libro con ese ID.");
                return;
            }
            // Obtiene el primer libro encontrado (debería haber solo uno ya que se busca por ID)
            Libro libro = librosEncontrados.get(0);
            // Imprime los detalles del libro encontrado
            System.out.println("Libro encontrado:");
            libro.imprimirLibro();
            // Muestra el menú de opciones para actualizar el libro y continúa hasta que el usuario elija salir
            while (!salir) {
                salir = menuActualizar(libro);
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al actualizar el libro
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    // Menú para elegir que campo actualizar
    private boolean menuActualizar(Libro libro) throws SQLException {
        boolean salir = false;
        try {
            
                System.out.println("***************************");
                System.out.println("**** Actualizar libro *****");
                System.out.println("***************************");
                System.out.println("1.- Actualizar titulo");
                System.out.println("2.- Actualizar autor");
                System.out.println("3.- Actualizar genero");
                System.out.println("4.- Actualizar disponibilidad");
                System.out.println("9.- Salir");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Nuevo título: ");
                        String nuevoTitulo = scanner.nextLine();
                        if (!nuevoTitulo.isEmpty()) {
                            libro.setTitulo(nuevoTitulo);
                            libroDAO.actualizarLibro(libro);
                            System.out.println("Libro actualizado correctamente.");
                        }
                        break;
                    case "2":
                        System.out.print("Nuevo autor: ");
                        String nuevoAutor = scanner.nextLine();
                        if (!nuevoAutor.isEmpty()) {
                            libro.setAutor(nuevoAutor);
                            libroDAO.actualizarLibro(libro);
                            System.out.println("Libro actualizado correctamente.");
                    }
                        break;
                    case "3":
                        System.out.print("Nuevo género: ");
                        String nuevoGenero = scanner.nextLine();
                        if (!nuevoGenero.isEmpty()) {
                            libro.setGenero(nuevoGenero);
                            libroDAO.actualizarLibro(libro);
                            System.out.println("Libro actualizado correctamente.");
                        }
                        break;
                    case "4":
                        boolean nuevoDisponible = Utilidades.leerBoolean("¿Nuevo estado de disponibilidad? (1/0)");
                        libro.setDisponible(nuevoDisponible);
                        libroDAO.actualizarLibro(libro);
                        System.out.println("Libro actualizado correctamente.");
                        break;
                    case "9":
                        salir = true;
                        break;
                    default:
                        break;
            }


        } catch (SQLException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
        return salir;
    }

    // Método para buscar libro según el campo que escogamos
    public void buscarLibro() {
        System.out.println("***************************");
        System.out.println("****** Buscar libro *******");
        System.out.println("***************************");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por título");
        System.out.println("3. Buscar por autor");
        System.out.println("4. Buscar por género");
        System.out.println("5. Buscar por disponibilidad");
        int opcion = Utilidades.leerNum("Seleccione una opción de búsqueda: ");
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
        String valor = null;
        if (columna.equals("disponible")) {
            System.out.print("Ingrese el valor a buscar (1/0): ");
            valor = scanner.nextLine();
        }
        else {
            System.out.print("Ingrese el valor a buscar: ");
            valor = scanner.nextLine();
        }
        try {
            ArrayList<Libro> librosEncontrados = libroDAO.buscarLibro(columna, valor);
            if (librosEncontrados.isEmpty()) {
                System.out.println("No se encontraron libros con ese criterio de búsqueda.");
            } else {
                System.out.println("Libros encontrados:");
                for (Libro libro : librosEncontrados) {
                    libro.imprimirLibro();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
        }
    }
}