package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


import Herramientas.Utilidades;

public class Biblioteca {
    private Connection conexion;
    private Scanner scanner = new Scanner(System.in);

    // Constructor que establece la conexión al instanciar el objeto
    public Biblioteca() {
        this.conexion = Conexion.establecerConexion();
    }
    
    // Método para insertar un libro en la base de datos
    public void insertarLibro() throws SQLException {
        try {
            conexion.setAutoCommit(false); // Desactiva la confirmación automática de transacciones
            String consultaDisponibilidad = "SELECT disponible FROM libros WHERE titulo = ?";
            PreparedStatement pstmtConsulta = conexion.prepareStatement(consultaDisponibilidad);
            String queryInsertar = "INSERT INTO libros (titulo, autor, genero) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsertar = conexion.prepareStatement(queryInsertar);

            // Solicita al usuario que ingrese los detalles del libro
            System.out.println("***************************");
            System.out.println("****** Insertar libro *****");
            System.out.println("***************************");
            System.out.println("Titulo: ");
            String titulo = scanner.nextLine();

            // Consulta si el libro ya existe en la base de datos
            pstmtConsulta.setString(1, titulo);
            ResultSet resultado = pstmtConsulta.executeQuery();

            boolean disponible = false;
            if (resultado.next()) {
                disponible = resultado.getBoolean("disponible"); // Verifica la disponibilidad del libro
            }
            
            // Si el libro no está disponible, solicita información adicional y lo inserta en la base de datos
            if (!disponible) {
                System.out.println("Autor: ");
                String autor = scanner.nextLine();
                System.out.println("Genero: ");
                String genero = scanner.nextLine();

                // Inserta el libro en la base de datos
                pstmtInsertar.setString(1, titulo);
                pstmtInsertar.setString(2, autor);
                pstmtInsertar.setString(3, genero);
                pstmtInsertar.executeUpdate(); 
                System.out.println("\nLibro insertado correctamente.\n"); 
            } else {
                // Si el libro ya está disponible, muestra un mensaje
                System.out.println("\nLibro ya disponible\n");
            }

            // Cierra los recursos
            resultado.close();
            pstmtConsulta.close();
        } catch (SQLException e) {
            conexion.rollback(); // Deshace la transacción en caso de error
            e.printStackTrace();
        } finally {
            conexion.setAutoCommit(true); // Vuelve al modo de confirmación automática por defecto
        }
    }

    // Método para eliminar un libro de la base de datos
    public void eliminarLibro() throws SQLException {
        try {
            conexion.setAutoCommit(false); // Desactiva la confirmación automática de transacciones
            String consultaDisponibilidad = "SELECT disponible FROM libros WHERE titulo = ?";
            PreparedStatement pstmtConsulta = conexion.prepareStatement(consultaDisponibilidad);
            String queryEliminar = "DELETE FROM libros WHERE titulo = ?";
            PreparedStatement pstmtEliminar = conexion.prepareStatement(queryEliminar);

            // Solicita al usuario el título del libro que desea eliminar
            System.out.println("***************************");
            System.out.println("****** Eliminar libro *****");
            System.out.println("***************************");
            System.out.println("Titulo: ");
            String titulo = scanner.nextLine();

            // Consulta si el libro existe en la base de datos
            pstmtConsulta.setString(1, titulo);
            ResultSet resultado = pstmtConsulta.executeQuery();

            boolean disponible = false;
            if (resultado.next()) {
                disponible = resultado.getBoolean("disponible"); // Verifica la disponibilidad del libro
            }
            
            // Si el libro no está disponible no se puede eliminar
            if (!disponible) {
                System.out.println("\nLibro no se encuentra en la base de datos.\n"); 
            } else {
                // Si el libro ya está disponible se elimina
                pstmtEliminar.setString(1, titulo);
                int filasAfectadas = pstmtEliminar.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("\nLibro eliminado con exito\n");
                } else {
                    System.out.println("\nNo se encontro el libro: " + titulo + "\n");
                }
            }

            // Cierra los recursos
            resultado.close();
            pstmtConsulta.close();
        } catch (SQLException e) {
            conexion.rollback(); // Deshace la transacción en caso de error
            e.printStackTrace();
        } finally {
            conexion.setAutoCommit(true); // Vuelve al modo de confirmación automática por defecto
        }
    }

    public void actualizarLibro() throws SQLException {
        boolean salir = false;
        // Solicita al usuario el título del libro que desea Actualizar
        System.out.println("***************************");
        System.out.println("**** Actualizar libro *****");
        System.out.println("***************************");
        System.out.println("Titulo: ");
        String titulo = scanner.nextLine();
        
        try {
            conexion.setAutoCommit(false); // Desactiva la confirmación automática de transacciones
            String consultaDisponibilidad = "SELECT disponible FROM libros WHERE titulo = ?";
            PreparedStatement pstmtConsulta = conexion.prepareStatement(consultaDisponibilidad);

            // Consulta si el libro existe en la base de datos
            pstmtConsulta.setString(1, titulo);
            ResultSet resultado = pstmtConsulta.executeQuery();

            boolean disponible = false;
            if (resultado.next()) {
                disponible = resultado.getBoolean("disponible"); // Verifica la disponibilidad del libro
            }
            
            if (!disponible) {
                System.out.println("\nLibro no se encuentra en la base de datos.\n");
            }

            resultado.close();
            pstmtConsulta.close();
        } catch (SQLException e) {
            conexion.rollback(); // Deshace la transacción en caso de error
            e.printStackTrace();
        }
        while (!salir) {
            salir = menuLibro(titulo);
        }
    }
    
    public boolean menuLibro(String titulo) throws SQLException {
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
                        actualizarColumna("titulo",titulo);
                        break;
                    case "2":
                        actualizarColumna("autor", titulo);
                        break;
                    case "3":
                        actualizarColumna("genero",titulo);
                        break;
                    case "4":
                        actualizarColumna("disponible", titulo);
                        break;
                    case "9":
                        salir = true;
                    default:
                        break;
            }


        } catch (SQLException e) {
            conexion.rollback(); // Deshace la transacción en caso de error
            e.printStackTrace();
        } finally {
            conexion.setAutoCommit(true); // Vuelve al modo de confirmación automática por defecto
        }
        return salir;
    }

    public void actualizarColumna(String columna, String titulo) throws SQLException {
        try {
            String queryActualizar = "UPDATE libros SET " + columna + " = ? WHERE titulo = ?";
            PreparedStatement pstmtActualizar = conexion.prepareStatement(queryActualizar);
    
        
            if (columna == "disponible") {
                boolean nuevaDisponibilidad = Utilidades.leerBoolean("Nuevo valor para disponibilidad (1/0): ");
                pstmtActualizar.setBoolean(1, nuevaDisponibilidad);
                pstmtActualizar.setString(2, titulo);
            }
            else {
                System.out.println("Nuevo valor para " + columna + ": ");
                String nuevoValor = scanner.nextLine();
                pstmtActualizar.setString(1, nuevoValor);
                pstmtActualizar.setString(2, titulo);
            }
    
            int filasActualizadas = pstmtActualizar.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Libro actualizado correctamente.");
            } else {
                System.out.println("No se encontró el libro.");
            }
    
            pstmtActualizar.close();
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
        }
    }
}