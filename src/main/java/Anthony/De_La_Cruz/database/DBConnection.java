package Anthony.De_La_Cruz.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn != null && !conn.isClosed()) return conn;

        // Mantengo tu estructura original
        Properties props = new Properties();

        // Datos DIRECTOS en la clase (como pediste)
        String url = "jdbc:mysql://hack.cnis6occib39.us-east-1.rds.amazonaws.com:3306/inventario_equipos?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "holamundo1234";

        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
