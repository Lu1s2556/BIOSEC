package utilidades;
import java.sql.*;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/BIOSEC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}