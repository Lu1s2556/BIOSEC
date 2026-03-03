package luis_rojas.luisr;

/**
 * Deprecated provisional helper. Replaced by `MySQLDB` which connects to
 * the user's MySQL (XAMPP) instance. This class is kept as a harmless stub
 * to avoid compilation errors in case other code references it.
 */
public final class DBHelper {
    private DBHelper() { }

    @Deprecated
    public static void initDatabase() {
        // intentionally no-op; use MySQLDB instead
    }

    @Deprecated
    public static java.util.List<Concept> getConcepts() {
        return java.util.Collections.emptyList();
    }
}
