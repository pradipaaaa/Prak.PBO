
package responsi_123230061;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Koneksi {
    private static final String url = "jdbc:mysql://localhost:3306/toko_db";
    private static final String user = "root";
    private static final String pass = "";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pass);
    }
}
