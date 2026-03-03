package luis_rojas.luisr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JDBC helper to query the `conceptos` table from a MySQL server.
 * Expects a MySQL server on localhost:3306 and database `bloodsql`.
 * Default XAMPP credentials (user: root, empty password) are used.
 */
public class MySQLDB {
    private static final String URL = "jdbc:mysql://localhost:3306/bloodsql?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // XAMPP default

    public static List<Concept> getConcepts() {
        List<Concept> list = new ArrayList<>();
        String sql = "SELECT codigo, nombre, precio1 FROM conceptos ORDER BY nombre";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int code = rs.getInt("codigo");
                String name = rs.getString("nombre");
                double price = 0.0;
                try { price = rs.getDouble("precio1"); } catch (Exception ex) { /* ignore */ }
                list.add(new Concept(code, name == null ? "" : name, price));
            }
        } catch (Exception e) {
            System.err.println("MySQLDB.getConcepts error: " + e.getMessage());
        }
        return list;
    }
}
