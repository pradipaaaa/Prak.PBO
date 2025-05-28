package responsi_123230061;

import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class FrameKedai extends JFrame{
    private Control controller = new Control();
    private JTextField txtId = new JTextField();
    private JTextField txtNama = new JTextField();
    private JTextField txtBarang = new JTextField();
    private JTextField txtHarga = new JTextField();
    private JTextField txtCicilan = new JTextField();
    private DefaultTableModel model = new DefaultTableModel(new String[]{"id","nama", "barang", "harga", "cicilan", "bunga", "angsuran", "total"}, 0);
    private JTable table = new JTable(model);
    
    public FrameKedai() {
        setTitle("Toko Bela Negara");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6,2));
        
        formPanel.add(new JLabel("Nama"));
        formPanel.add(txtNama);
         formPanel.add(new JLabel("Barang"));
        formPanel.add(txtBarang);
         formPanel.add(new JLabel("Harga"));
        formPanel.add(txtHarga);
         formPanel.add(new JLabel("Cicilan"));
        formPanel.add(txtCicilan);
        
        JButton btnAdd = new JButton("Create");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Delete");
        
        btnAdd.addActionListener(e -> {
            controller.tambahBayar(txtNama.getText(), txtBarang.getText(), 
                    Double.parseDouble(txtHarga.getText()), Double.parseDouble(txtCicilan.getText()));
            
            JOptionPane.showMessageDialog(formPanel, "Data Berhasil Ditambahkan");
            refreshTable();
            clearFields();
        });
        
        btnEdit.addActionListener(e -> {
            if(!txtId.getText().isEmpty()){
                controller.updateBayar(Integer.parseInt(txtId.getText()), txtNama.getText(), txtBarang.getText(), 
                    Double.parseDouble(txtHarga.getText()), Double.parseDouble(txtCicilan.getText()));
                JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate");
                refreshTable();
                clearFields();
            }else {
                 JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu untuk diupdate");             
            }
        });
        
        btnHapus.addActionListener(e -> {
            if(!txtId.getText().isEmpty()){
                controller.hapusBayar(Integer.parseInt(txtId.getText()));
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                refreshTable();
                clearFields();
            }else {
                 JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu untuk dihapus");             
            }
        });
        
        JPanel ButtonPanel = new Button JPanel(new GridLayout(1,4));
        ButtonPanel.add(btnAdd);
        ButtonPanel.add(btnEdit);
        ButtonPanel.add(btnHapus);
        
        add(formPanel,BorderLayout.NORTH);
        add(new JScrollPane(table),BorderLayout.CENTER);
        add(ButtonPanel,BorderLayout.SOUTH);
        
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) ->{
            
        });
    }
    
    
}
