package latkuis;

import javax.swing.*;
import java.awt.*;

// Harga.java
public class Harga extends JFrame {
    public Harga(String nama, String telepon, String kendaraan, int harga, int hari) {
        setTitle("Detail dan Total Harga");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        
        add(new JLabel("Nama: " + nama));
        add(new JLabel("Telepon: " + telepon));
        add(new JLabel("Kendaraan: " + kendaraan));
        add(new JLabel("Harga Sewa per Hari: Rp " + harga));
        add(new JLabel("Total Harga: Rp " + (harga * hari)));
        
        JButton btnSelesai = new JButton("Selesai");
        btnSelesai.addActionListener(e -> System.exit(0));
        add(btnSelesai);
        
        setVisible(true);
    }
}