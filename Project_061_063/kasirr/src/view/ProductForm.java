
package view;

import model.Product;
import model.ProductDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductForm extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductForm() {
        super("Manajemen Produk");

        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // tabel
        String[] columnNames = {"ID", "Kode", "Nama", "Harga", "Stok", "Min Stok"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        loadData();

        // tombol
        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");

        btnTambah.addActionListener(e -> showForm(null));
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Product p = getSelectedProduct(row);
                showForm(p);
            }
        });
        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                if (ProductDAO.delete(id)) {
                    JOptionPane.showMessageDialog(this, "Berhasil dihapus!");
                    loadData();
                }
            }
        });

        JPanel panelBtn = new JPanel();
        panelBtn.add(btnTambah);
        panelBtn.add(btnEdit);
        panelBtn.add(btnHapus);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Product> list = ProductDAO.getAllProducts();
        for (Product p : list) {
            tableModel.addRow(new Object[]{
                p.getId(), p.getCode(), p.getName(),
                p.getPrice(), p.getStock(), p.getMinStock()
            });
        }
    }

    private void showForm(Product p) {
        JTextField code = new JTextField(p == null ? "" : p.getCode());
        JTextField name = new JTextField(p == null ? "" : p.getName());
        JTextField price = new JTextField(p == null ? "" : String.valueOf(p.getPrice()));
        JTextField stock = new JTextField(p == null ? "" : String.valueOf(p.getStock()));
        JTextField minStock = new JTextField(p == null ? "" : String.valueOf(p.getMinStock()));

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Kode:")); panel.add(code);
        panel.add(new JLabel("Nama:")); panel.add(name);
        panel.add(new JLabel("Harga:")); panel.add(price);
        panel.add(new JLabel("Stok:")); panel.add(stock);
        panel.add(new JLabel("Min Stok:")); panel.add(minStock);

        int result = JOptionPane.showConfirmDialog(this, panel,
            p == null ? "Tambah Produk" : "Edit Produk", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Product prod = new Product();
                prod.setCode(code.getText());
                prod.setName(name.getText());
                prod.setPrice(Double.parseDouble(price.getText()));
                prod.setStock(Integer.parseInt(stock.getText()));
                prod.setMinStock(Integer.parseInt(minStock.getText()));
                if (p != null) prod.setId(p.getId());

                boolean sukses = (p == null) ? ProductDAO.insert(prod) : ProductDAO.update(prod);
                if (sukses) {
                    JOptionPane.showMessageDialog(this, "Berhasil disimpan!");
                    loadData();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error input: " + ex.getMessage());
            }
        }
    }

    private Product getSelectedProduct(int row) {
        Product p = new Product();
        p.setId(Integer.parseInt(table.getValueAt(row, 0).toString()));
        p.setCode(table.getValueAt(row, 1).toString());
        p.setName(table.getValueAt(row, 2).toString());
        p.setPrice(Double.parseDouble(table.getValueAt(row, 3).toString()));
        p.setStock(Integer.parseInt(table.getValueAt(row, 4).toString()));
        p.setMinStock(Integer.parseInt(table.getValueAt(row, 5).toString()));
        return p;
    }
}
