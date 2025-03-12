package kuis;

import javax.swing.*;
import java.awt.*;

public class Hasil extends JFrame {
    public Hasil(String nama, String nim, String jurusan, String serangan){
        setTitle("Detail Hasil Pertandingan");
        setSize(400,300);
        setLayout(new GridLayout(2,1));
        //buat if jika batu menang lawan gunting, kertas menang lawan batu, gunting menang lawan kertas
                    
        add(new JLabel("Pemenang pertandingan adalah" + nama));
        add(new JLabel("Nama: "+ nama));
        add(new JLabel("NIM: "+ nim));
        add(new JLabel("Jurusan: "+ jurusan));
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> new Login());
        
        add(btnLogout);
        setVisible(true);
    }
}
