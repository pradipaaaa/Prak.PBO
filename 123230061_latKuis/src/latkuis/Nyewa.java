package latkuis;

import javax.swing.*;
import java.awt.*;

// Nyewa.java
public class Nyewa extends JFrame {
    private JTextField namaField, teleponField, hariField;
    private JRadioButton[] kendaraanOptions;
    private int[] hargaSewa;
    
    public Nyewa(String jenis) {
        setTitle("Detail Penyewaan - " + jenis);
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        
        add(new JLabel("Nama Penyewa:"));
        namaField = new JTextField();
        add(namaField);
        
        add(new JLabel("Nomor Telepon:"));
        teleponField = new JTextField();
        add(teleponField);
        
        String[] kendaraanList = (jenis.equals("Motor")) ?
            new String[]{"Honda Beat - 50000", "Yamaha NMax - 75000", "Vespa - 100000"} :
            new String[]{"Avanza - 250000", "Innova - 400000", "Fortuner - 600000"};
        hargaSewa = (jenis.equals("Motor")) ? new int[]{50000, 75000, 100000} : new int[]{250000, 400000, 600000};
        
        ButtonGroup group = new ButtonGroup();
        kendaraanOptions = new JRadioButton[kendaraanList.length];
        JPanel panelKendaraan = new JPanel(new GridLayout(kendaraanList.length, 1));
        for (int i = 0; i < kendaraanList.length; i++) {
            kendaraanOptions[i] = new JRadioButton(kendaraanList[i]);
            group.add(kendaraanOptions[i]);
            panelKendaraan.add(kendaraanOptions[i]);
        }
        add(panelKendaraan);
        
        add(new JLabel("Jumlah Hari Sewa:"));
        hariField = new JTextField();
        add(hariField);
        
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpanData());
        add(btnSimpan);
        
        setVisible(true);
    }
    
    private void simpanData() {
        try {
            String nama = namaField.getText();
            String telepon = teleponField.getText();
            int hari = Integer.parseInt(hariField.getText());
            int harga = 0;
            String kendaraanDipilih = "";
            
            for (int i = 0; i < kendaraanOptions.length; i++) {
                if (kendaraanOptions[i].isSelected()) {
                    kendaraanDipilih = kendaraanOptions[i].getText();
                    harga = hargaSewa[i];
                    break;
                }
            }
            
            if (kendaraanDipilih.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih kendaraan dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            new Harga(nama, telepon, kendaraanDipilih, harga, hari);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah hari yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
