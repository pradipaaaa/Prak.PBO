package library;

import java.sql.*;

public class Koneksi {
    String DBurl = "jdbc:mysql://localhost/library";
    String DBuser = "root";
    String DBpass = "";

    public Connection koneksi;
    public Statement statement;

    public Koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl, DBuser, DBpass);
            statement = koneksi.createStatement();
            System.out.println("Koneksi Berhasil");
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
    }
}
