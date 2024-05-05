package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    // Atributo de la clase para la conexión a la base de datos
    private Connection conexion;

    // Constructor de la clase que recibe una conexión a la base de datos
    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para insertar un nuevo libro en la base de datos
    public void insertarLibro(Libro libro) throws SQLException {
        // Consulta SQL para insertar un nuevo libro
        String query = "INSERT INTO libros (titulo, autor, genero, disponible) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getGenero());
            pstmt.setBoolean(4, libro.isDisponible());
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar un libro de la base de datos por su ID
    public void eliminarLibro(int id) throws SQLException {
        // Consulta SQL para eliminar un libro por su ID
        String query = "DELETE FROM libros WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Método para actualizar los datos de un libro en la base de datos
    public void actualizarLibro(Libro libro) throws SQLException {
        // Consulta SQL para actualizar los datos de un libro
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

    // Método para buscar libros en la base de datos según un valor en una columna específica
    public ArrayList<Libro> buscarLibro(String columna, String valor) throws SQLException {
        // Consulta SQL para buscar libros según una columna y un valor específicos
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