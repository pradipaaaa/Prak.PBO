package responsi_123230061;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudToko {
    public void insert (Kedai kedai) throws SQLException {
        String sql = "INSERT INTO pembayaran (nama, barang, harga, cicilan, bunga, angsuran, total) VALUES(?,?,?,?,?,?,?)";
        try(Connection conn = Koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, kedai.getNama());
            stmt.setString(2, kedai.getBarang());
            stmt.setDouble(3, kedai.getHarga());
            stmt.setDouble(4, kedai.getCicilan());
            stmt.setDouble(5, kedai.getBunga());
            stmt.setDouble(6, kedai.getAngsuran());
            stmt.setDouble(7, kedai.getTotal());
            
            stmt.executeUpdate();
        }
    }
    
    public void update (Kedai kedai) throws SQLException {
        String sql = "UPDATE pembayaran SET nama=?, barang=?, harga=?, ciclan=?, bunga=?, angsuran=?, total=? WHERE id=?";
        try(Connection conn = Koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, kedai.getNama());
            stmt.setString(2, kedai.getBarang());
            stmt.setDouble(3, kedai.getHarga());
            stmt.setDouble(4, kedai.getCicilan());
            stmt.setDouble(5, kedai.getBunga());
            stmt.setDouble(6, kedai.getAngsuran());
            stmt.setDouble(7, kedai.getTotal());
            
            stmt.executeUpdate();
        }
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pembayaran WHERE id=?";
        try(Connection conn = Koneksi.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public List<Kedai> getAllKedai() throws SQLException{
        List<Kedai> list = new ArrayList<>();
        String sql = "SELECT * FROM pembayaran";
        
         try(Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
             
             while(rs.next()){
                 Kedai kedai = new Kedai(
                      rs.getInt("id"),
                      rs.getString("nama"),
                      rs.getString("barang"),
                      rs.getDouble("harga"),
                      rs.getDouble("cicilan")
                         
                 );
                 list.add(kedai);
             }
         }
         return list;
     }
}
