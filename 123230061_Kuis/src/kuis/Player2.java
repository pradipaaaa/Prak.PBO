package kuis;

import javax.swing.*;
import java.awt.*;

public class Player2 extends JFrame {
    private JTextField namaField2,nimField2,jurusanField2;
    private JRadioButton[] pilihanSerangan2;

    public Player2(){
    setTitle("Input Data Player 2");
    setSize(400,300);
    setLayout(new GridLayout(4,1));
    
    add(new JLabel("Nama : "));
    namaField2 = new JTextField();
    add(namaField2);
    
    add(new JLabel("NIM : "));
    nimField2 = new JTextField();
    add(nimField2);
    
    add(new JLabel("Jurusan : "));
    jurusanField2 = new JTextField();
    add(jurusanField2);
    
    String[] listSerangan2 = new String[]{"Gunting", "Batu", "Kertas"};
    
    ButtonGroup group2 = new ButtonGroup();
    pilihanSerangan2 = new JRadioButton[listSerangan2.length];
    JPanel panelPlayer2 = new JPanel(new GridLayout(listSerangan2.length,1));
    
        for (int i = 0; i < listSerangan2.length; i++) {
            pilihanSerangan2[i] = new JRadioButton(listSerangan2[i]);
            group2.add(pilihanSerangan2[i]);
            panelPlayer2.add(pilihanSerangan2[i]);
        }
        add(panelPlayer2);
        
          JButton btnSimpan = new JButton("Simpan");
          btnSimpan.addActionListener(e -> simpan());
    }   
    private void simpan(){
        try {
            String nama2 = namaField2.getText();
            String nim2 = nimField2.getText();
            String jurusan2 = jurusanField2.getText();
            String serangan2 = "";
            
            for (int i = 0; i < pilihanSerangan2.length; i++) {
                if (pilihanSerangan2[i].isSelected()) {
                    serangan2 = pilihanSerangan2[i].getText();
                    break;
                }
            }
            if(serangan2.isEmpty()){
                JOptionPane.showMessageDialog(this,"pilih serangan terlebih dahulu", "Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            new Hasil(nama2,nim2,jurusan2,serangan2);
            dispose();
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Masukkan nim yang valid", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
