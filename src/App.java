import Modelo.Conexion;

public class App {
    public static void main(String[] args) throws Exception {
        Conexion.establecerConexion();

        Conexion.cerrarConexion();
    }
}