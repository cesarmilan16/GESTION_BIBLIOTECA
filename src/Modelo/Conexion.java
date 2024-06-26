package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Atributos de la clase
    static String url = "jdbc:mysql://localhost:3306/gestion_biblioteca";
    static String usuario = "root";
    static String contraseña = "Cesarmilan1996";
    static Connection conexion = null;

    // Método para establecer conexión con la base de datos
    public static Connection establecerConexion(){
        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa con la base de datos MySQL!");
        } catch (SQLException e) {
            // Manejar cualquier excepción que ocurra al intentar conectar a la base de datos
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    // Método para cerrar la conexión con la base de datos
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                // Manejar cualquier excepción que ocurra al intentar cerrar la conexión
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}