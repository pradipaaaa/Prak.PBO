package view;

import util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ReportFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ReportFrame() {
        super("Laporan Transaksi");

        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Tanggal", "Total", "Kasir"}, 0);
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadData() {
        String sql = """
            SELECT t.id, t.transaction_date, t.total_amount, u.full_name
            FROM transactions t
            JOIN users u ON t.user_id = u.id
            ORDER BY t.transaction_date DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getTimestamp("transaction_date"),
                        rs.getDouble("total_amount"),
                        rs.getString("full_name")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data laporan:\n" + e.getMessage());
        }
    }
}

