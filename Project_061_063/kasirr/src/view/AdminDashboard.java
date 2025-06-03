package view;

import model.User;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private User adminUser;

    public AdminDashboard(User user) {
        super("Dashboard Admin - " + user.getFullName());
        this.adminUser = user;

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnProduk = new JButton("Manajemen Produk");
        btnProduk.addActionListener(e -> new ProductForm().setVisible(true));

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


