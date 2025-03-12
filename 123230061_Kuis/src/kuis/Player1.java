package kuis;

import javax.swing.*;
import java.awt.*;

public class Player1 extends JFrame {
    private JTextField namaField,nimField,jurusanField;
    private JRadioButton[] pilihanSerangan;

    public Player1(){
   
    for (int i = 0; i < 3; i++) {
    setTitle("Input Data Player " + i++);
    setSize(400,300);
    setLayout(new GridLayout(4,1));    
    
    add(new JLabel("Nama : "));
    namaField = new JTextField();
    add(namaField);
    
    add(new JLabel("NIM : "));
    nimField = new JTextField();
    add(nimField);
    
    add(new JLabel("Jurusan : "));
    jurusanField = new JTextField();
    add(jurusanField);
    
    String[] listSerangan = new String[]{"Gunting", "Batu", "Kertas"};
    
    ButtonGroup group = new ButtonGroup();
    pilihanSerangan = new JRadioButton[listSerangan.length];
    JPanel panelPlayer = new JPanel(new GridLayout(listSerangan.length,1));
    
    for (int j = 0; j < listSerangan.length; i++) {
            pilihanSerangan[j] = new JRadioButton(listSerangan[i]);
            group.add(pilihanSerangan[j]);
            panelPlayer.add(pilihanSerangan[j]);
        }
    add(panelPlayer);
    }
        
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpan());
    }   
    private void simpan(){
        try {
            String nama = namaField.getText();
            String nim = nimField.getText();
            String jurusan = jurusanField.getText();
            String serangan = "";
            
            for (int i = 0; i < pilihanSerangan.length; i++) {
                if (pilihanSerangan[i].isSelected()) {
                    serangan = pilihanSerangan[i].getText();
                    break;
                }
            }
            if(serangan.isEmpty()){
                JOptionPane.showMessageDialog(this,"pilih serangan terlebih dahulu", "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            new Hasil(nama,nim,jurusan,serangan);
            dispose();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Masukkan nim yang valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
