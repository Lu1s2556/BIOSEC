package utilidades;
import java.sql.*;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/BIOSEC";
    private static final String USER = "BIOSEC";
    private static final String PASS = "Base4020";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}