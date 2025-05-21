// PerpusFrame.java
package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class PerpusFrame extends JFrame {
    private JTextField tfJudul, tfGenre, tfPenulis, tfPenerbit, tfLokasi, tfStok, tfCari;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbKategori;
    private int selectedId = -1;

    public PerpusFrame() {
        setTitle("Pendataan Buku Perpustakaan");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inisialisasi semua text field
        tfJudul = new JTextField();
        tfGenre = new JTextField();
        tfPenulis = new JTextField();
        tfPenerbit = new JTextField();
        tfLokasi = new JTextField();
        tfStok = new JTextField();

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(new JLabel("Judul")); formPanel.add(tfJudul);
        formPanel.add(new JLabel("Genre")); formPanel.add(tfGenre);
        formPanel.add(new JLabel("Penulis")); formPanel.add(tfPenulis);
        formPanel.add(new JLabel("Penerbit")); formPanel.add(tfPenerbit);
        formPanel.add(new JLabel("Lokasi")); formPanel.add(tfLokasi);
        formPanel.add(new JLabel("Stok")); formPanel.add(tfStok);

        // Tombol Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnTambah = new JButton("Tambah");
        JButton btnUbah = new JButton("Ubah");
        JButton btnHapus = new JButton("Hapus");
        JButton btnTampil = new JButton("Tampilkan Semua");
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUbah);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnTampil);

        // Panel Utama Atas (gabung form dan tombol)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Judul", "Genre", "Penulis", "Penerbit", "Lokasi", "Stok"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Search Panel
        JPanel searchPanel = new JPanel();
        cbKategori = new JComboBox<>(new String[]{"judul", "genre", "penulis", "penerbit"});
        tfCari = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        searchPanel.add(new JLabel("Cari berdasarkan: "));
        searchPanel.add(cbKategori);
        searchPanel.add(tfCari);
        searchPanel.add(btnCari);
        add(searchPanel, BorderLayout.SOUTH);

        // Event Listener
        btnTambah.addActionListener(e -> handleTambah());
        btnUbah.addActionListener(e -> handleUbah());
        btnHapus.addActionListener(e -> handleHapus());
        btnTampil.addActionListener(e -> loadTable());
        btnCari.addActionListener(e -> handleCari());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    selectedId = Integer.parseInt(table.getValueAt(row, 0).toString());
                    tfJudul.setText(table.getValueAt(row, 1).toString());
                    tfGenre.setText(table.getValueAt(row, 2).toString());
                    tfPenulis.setText(table.getValueAt(row, 3).toString());
                    tfPenerbit.setText(table.getValueAt(row, 4).toString());
                    tfLokasi.setText(table.getValueAt(row, 5).toString());
                    tfStok.setText(table.getValueAt(row, 6).toString());
                }
            }
        });
    }

    private void handleTambah() {
        try {
            Library buku = new Library(
                tfJudul.getText(), tfGenre.getText(), tfPenulis.getText(),
                tfPenerbit.getText(), tfLokasi.getText(), Integer.parseInt(tfStok.getText())
            );
            Kontrol.tambahBuku(buku);
            loadTable();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid atau koneksi gagal: " + ex.getMessage());
        }
    }

    private void handleUbah() {
        if (selectedId == -1) return;
        try {
            Library buku = new Library(selectedId, tfJudul.getText(), tfGenre.getText(), tfPenulis.getText(),
                    tfPenerbit.getText(), tfLokasi.getText(), Integer.parseInt(tfStok.getText()));
            Kontrol.ubahBuku(buku);
            loadTable();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update gagal: " + ex.getMessage());
        }
    }

    private void handleHapus() {
        if (selectedId == -1) return;
        try {
            Kontrol.hapusBuku(selectedId);
            loadTable();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hapus gagal: " + ex.getMessage());
        }
    }

    private void handleCari() {
        try {
            String kategori = cbKategori.getSelectedItem().toString();
            String keyword = tfCari.getText();
            List<Library> hasil = Kontrol.cariBuku(kategori, keyword);
            tampilkanData(hasil);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cari gagal: " + ex.getMessage());
        }
    }

    private void loadTable() {
        try {
            tampilkanData(Kontrol.getSemuaBuku());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Load gagal: " + ex.getMessage());
        }
    }

    private void tampilkanData(List<Library> list) {
        tableModel.setRowCount(0);
        for (Library b : list) {
            tableModel.addRow(new Object[]{b.getId(), b.getJudul(), b.getGenre(), b.getPenulis(), b.getPenerbit(), b.getLokasi(), b.getStok()});
        }
    }

    private void clearForm() {
        tfJudul.setText(""); tfGenre.setText(""); tfPenulis.setText("");
        tfPenerbit.setText(""); tfLokasi.setText(""); tfStok.setText("");
        selectedId = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PerpusFrame().setVisible(true));
    }
}
