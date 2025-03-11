package latkuis;

import javax.swing.*;
import java.awt.*;

// PilihanKendaraan.java
public class PilihanKendaraan extends JFrame {
    public PilihanKendaraan() {
        setTitle("Pilihan Kendaraan");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JButton btnMotor = new JButton("Motor");
        JButton btnMobil = new JButton("Mobil");
        
        btnMotor.addActionListener(e -> new Nyewa("Motor"));
        btnMobil.addActionListener(e -> new Nyewa("Mobil"));
        
        add(btnMotor);
        add(btnMobil);
        
        setVisible(true);
    }
}
