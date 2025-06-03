package view;

import model.User;

import javax.swing.*;
import java.awt.*;

public class KasirDashboard extends JFrame {
    private User kasirUser;

    public KasirDashboard(User user) {
        super("Dashboard Kasir - " + user.getFullName());
        this.kasirUser = user;

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnProduk = new JButton("Manajemen Produk");
        btnProduk.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Akses ditolak. Hanya admin yang boleh membuka Manajemen Produk.");
        });

        JButton btnTransaksi = new JButton("Transaksi Penjualan");
        btnTransaksi.addActionListener(e -> new TransactionForm(user).setVisible(true));

        JButton btnReport = new JButton("Laporan Transaksi");
        btnReport.addActionListener(e -> new ReportFrame().setVisible(true));

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        add(new JLabel("Halo, " + user.getFullName(), SwingConstants.CENTER));
        add(btnProduk);
        add(btnTransaksi);
        add(btnReport);
        add(btnLogout);
    }
}


