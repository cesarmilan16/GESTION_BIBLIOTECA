package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Query {
    private Connection conexion;
    private Scanner scanner = new Scanner(System.in);

    public Query() {
        this.conexion = Conexion.establecerConexion();
    }
    
    public void insertarLibro() throws SQLException {
        try {
            conexion.setAutoCommit(false);
            String consultaDisponibilidad = "SELECT disponible FROM libros WHERE titulo = ?";
            PreparedStatement pstmtConsulta = conexion.prepareStatement(consultaDisponibilidad);
            String queryInsertar = "INSERT INTO libros (titulo, autor, genero) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsertar = conexion.prepareStatement(queryInsertar);

            System.out.println("***************************");
            System.out.println("****** Insertar libro *****");
            System.out.println("***************************");
            System.out.println("Titulo: ");
            String titulo = scanner.nextLine();

            pstmtConsulta.setString(1, titulo);
            ResultSet resultado = pstmtConsulta.executeQuery();

            boolean disponible = false;
            if (resultado.next()) {
                disponible = resultado.getBoolean("disponible");
            }
            
            if (!disponible) {
                System.out.println("Autor: ");
                String autor = scanner.nextLine();
                System.out.println("Genero: ");
                String genero = scanner.nextLine();

                pstmtInsertar.setString(1, titulo);
                pstmtInsertar.setString(2, autor);
                pstmtInsertar.setString(3, genero);
                pstmtInsertar.executeUpdate(); 
                System.out.println("Libro insertado correctamente."); 
            } else {
                
                        System.out.println("Libro ya disponible");
                    }

            // Cerrar recursos
            resultado.close();
            pstmtConsulta.close();
        } catch (SQLException e) {
            conexion.rollback(); // Deshace la transacción en caso de error
            e.printStackTrace();
        } finally {
            conexion.setAutoCommit(true); // Vuelve al modo de confirmación automática por defecto
        }
    }  
}