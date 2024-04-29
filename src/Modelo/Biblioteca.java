package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
        
    }
}