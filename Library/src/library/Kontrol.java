package library;

import java.sql.*;
import java.util.*;

public class Kontrol {

    public static void tambahBuku(Library buku) throws SQLException {
        String sql = "INSERT INTO buku (judul, genre, penulis, penerbit, lokasi, stok) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new Koneksi().koneksi; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, buku.getJudul());
            stmt.setString(2, buku.getGenre());
            stmt.setString(3, buku.getPenulis());
            stmt.setString(4, buku.getPenerbit());
            stmt.setString(5, buku.getLokasi());
            stmt.setInt(6, buku.getStok());
            stmt.executeUpdate();
        }
    }

    public static void ubahBuku(Library buku) throws SQLException {
        String sql = "UPDATE buku SET judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stok=? WHERE id=?";
        try (Connection conn = new Koneksi().koneksi; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, buku.getJudul());
            stmt.setString(2, buku.getGenre());
            stmt.setString(3, buku.getPenulis());
            stmt.setString(4, buku.getPenerbit());
            stmt.setString(5, buku.getLokasi());
            stmt.setInt(6, buku.getStok());
            stmt.setInt(7, buku.getId());
            stmt.executeUpdate();
        }
    }

    public static void hapusBuku(int id) throws SQLException {
        String sql = "DELETE FROM buku WHERE id=?";
        try (Connection conn = new Koneksi().koneksi; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Library> getSemuaBuku() throws SQLException {
        List<Library> list = new ArrayList<>();
        String sql = "SELECT * FROM buku";
        try (Connection conn = new Koneksi().koneksi; Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Library(
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getString("genre"),
                        rs.getString("penulis"),
                        rs.getString("penerbit"),
                        rs.getString("lokasi"),
                        rs.getInt("stok")
                ));
            }
        }
        return list;
    }

    public static List<Library> cariBuku(String kategori, String keyword) throws SQLException {
        List<Library> list = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE " + kategori + " LIKE ?";
        try (Connection conn = new Koneksi().koneksi; PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Library(
                        rs.getInt("id"),
                        rs.getString("judul"),
                        rs.getString("genre"),
                        rs.getString("penulis"),
                        rs.getString("penerbit"),
                        rs.getString("lokasi"),
                        rs.getInt("stok")
                ));
            }
        }
        return list;
    }
}
