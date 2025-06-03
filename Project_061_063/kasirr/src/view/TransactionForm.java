package view;

import model.*;
import util.PDFGenerator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;

public class TransactionForm extends JFrame {
    private JComboBox<String> comboProduk;
    private JTextField qtyField, cashField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalLabel, kembalianLabel;
    private List<TransactionDetail> cart = new ArrayList<>();
    private List<Product> productList = ProductDAO.getAllProducts();
    private User currentUser;

    public TransactionForm(User user) {
        super("Transaksi Penjualan");
        this.currentUser = user;

        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // panel atas
        JPanel topPanel = new JPanel(new FlowLayout());

        comboProduk = new JComboBox<>();
        for (Product p : productList) {
            comboProduk.addItem(p.getName() + " (Stok: " + p.getStock() + ")");
        }

        qtyField = new JTextField(5);
        JButton addBtn = new JButton("Tambah");
        addBtn.addActionListener(e -> tambahItem());

        topPanel.add(new JLabel("Produk:"));
        topPanel.add(comboProduk);
        topPanel.add(new JLabel("Qty:"));
        topPanel.add(qtyField);
        topPanel.add(addBtn);

        // tabel
        String[] kolom = {"Produk", "Qty", "Harga", "Subtotal"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        // panel pawah
        JPanel bottomPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        totalLabel = new JLabel("Rp 0");
        cashField = new JTextField();
        kembalianLabel = new JLabel("Rp 0");
        JButton bayarBtn = new JButton("Bayar & Cetak");
        bayarBtn.addActionListener(e -> prosesPembayaran());

        bottomPanel.add(new JLabel("Total:"));
        bottomPanel.add(totalLabel);
        bottomPanel.add(new JLabel("Tunai:"));
        bottomPanel.add(cashField);
        bottomPanel.add(new JLabel("Kembali:"));
        bottomPanel.add(kembalianLabel);
        bottomPanel.add(new JLabel(""));
        bottomPanel.add(bayarBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void tambahItem() {
        int index = comboProduk.getSelectedIndex();
        Product p = productList.get(index);

        try {
            int qty = Integer.parseInt(qtyField.getText());
            if (qty <= 0 || qty > p.getStock()) {
                JOptionPane.showMessageDialog(this, "Jumlah tidak valid.");
                return;
            }

            TransactionDetail d = new TransactionDetail();
            d.setProductId(p.getId());
            d.setProductName(p.getName());
            d.setQuantity(qty);
            d.setUnitPrice(p.getPrice());
            cart.add(d);

            tableModel.addRow(new Object[]{
                    d.getProductName(),
                    d.getQuantity(),
                    String.format("%.0f", d.getUnitPrice()),
                    String.format("%.0f", d.getSubtotal())
            });

            updateTotal();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input jumlah tidak valid.");
        }
    }

    private void updateTotal() {
        double total = cart.stream().mapToDouble(TransactionDetail::getSubtotal).sum();
        totalLabel.setText("Rp " + String.format("%.0f", total));
    }

    private void prosesPembayaran() {
        double total = cart.stream().mapToDouble(TransactionDetail::getSubtotal).sum();

        try {
            double cash = Double.parseDouble(cashField.getText());
            if (cash < total) {
                JOptionPane.showMessageDialog(this, "Uang kurang.");
                return;
            }

            double kembali = cash - total;
            kembalianLabel.setText("Rp " + String.format("%.0f", kembali));

            Transaction trx = new Transaction();
            trx.setUserId(currentUser.getId());
            trx.setTotalAmount(total);
            trx.setCashReceived(cash);
            trx.setChangeAmount(kembali);
            trx.setDetails(cart);

            if (TransactionDAO.saveTransaction(trx)) {
                PDFGenerator.generateReceipt(trx);
                JOptionPane.showMessageDialog(this, "Transaksi berhasil dan struk dicetak.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input uang tunai tidak valid.");
        }
    }
}

