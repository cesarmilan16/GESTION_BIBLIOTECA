package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    private Connection conexion;

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Query para insertar nuevo libro con los datos de un objeto libro que hemos creado
    public void insertarLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO libros (titulo, autor, genero, disponible) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.setBoolean(4, libro.isDisponible());
            pstmt.executeUpdate();
        }
    }

    // Query para eliminar un libro de la base de datos según su id
    public void eliminarLibro(int id) throws SQLException {
        String query = "DELETE FROM libros WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Query que actualiza los valores del libro
    public void actualizarLibro(Libro libro) throws SQLException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, genero = ?, disponible = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.setBoolean(4, libro.isDisponible());
            pstmt.setInt(5, libro.getId());
            pstmt.executeUpdate();
        }
    }

    public ArrayList<Libro> buscarLibro(String columna, String valor) throws SQLException {
        String query = "SELECT * FROM libros WHERE " + columna + " = ?";
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, valor);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String genero = rs.getString("genero");
                    boolean disponible = rs.getBoolean("disponible");
                    librosEncontrados.add(new Libro(id, titulo, autor, genero, disponible));
                }
            }
        }
        return librosEncontrados;
    }
}