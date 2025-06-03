-- ========================================
-- DATABASE SCHEMA UNTUK APLIKASI KASIR
-- ========================================

CREATE DATABASE kasir_minimarket;
USE kasir_minimarket;

-- Tabel Users (Admin & Kasir)
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'kasir') NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Categories
CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabel Products
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    category_id INT,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    min_stock INT DEFAULT 10,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Tabel Transactions
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL,
    payment_method ENUM('cash', 'debit', 'credit') DEFAULT 'cash',
    cash_received DECIMAL(12,2),
    change_amount DECIMAL(12,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tabel Transaction Details
CREATE TABLE transaction_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    transaction_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- ========================================
-- INSERT SAMPLE DATA
-- ========================================

-- Insert Users
INSERT INTO users (username, password, role, full_name) VALUES
('admin', 'admin123', 'admin', 'Administrator'),
('kasir1', 'kasir123', 'kasir', 'Kasir Satu'),
('kasir2', 'kasir123', 'kasir', 'Kasir Dua');

-- Insert Categories
INSERT INTO categories (name, description) VALUES
('Makanan Ringan', 'Snack dan makanan ringan'),
('Minuman', 'Minuman segar dan berkarbonasi'),
('Makanan Instan', 'Mie instan dan makanan siap saji'),
('Perawatan Tubuh', 'Sabun, shampo, dan produk kebersihan'),
('Alat Tulis', 'Perlengkapan tulis dan kantor'),
('Obat-obatan', 'Obat bebas dan vitamin'),
('Rokok', 'Produk tembakau'),
('Perlengkapan Rumah', 'Kebutuhan rumah tangga'),
('Makanan Kaleng', 'Makanan dalam kemasan kaleng'),
('Susu & Produk Susu', 'Susu dan produk olahan susu');

-- Insert 50+ Products
INSERT INTO products (code, name, category_id, price, stock, min_stock, description) VALUES
-- Makanan Ringan (Category 1)
('SNK001', 'Chitato Rasa Sapi Panggang', 1, 8500.00, 50, 10, 'Keripik kentang rasa sapi panggang'),
('SNK002', 'Taro Cokelat', 1, 6500.00, 45, 10, 'Snack taro rasa cokelat'),
('SNK003', 'Pringles Original', 1, 25000.00, 30, 5, 'Keripik kentang premium'),
('SNK004', 'Biskuit Roma Kelapa', 1, 4500.00, 60, 15, 'Biskuit rasa kelapa'),
('SNK005', 'Oreo Vanilla', 1, 12000.00, 40, 10, 'Biskuit sandwich vanilla'),
('SNK006', 'Richeese Nabati', 1, 3500.00, 80, 20, 'Keripik jagung keju'),
('SNK007', 'Tanggo Cokelat', 1, 7500.00, 35, 10, 'Wafer cokelat'),
('SNK008', 'Lays Klasik', 1, 9500.00, 25, 8, 'Keripik kentang original'),

-- Minuman (Category 2)
('DRK001', 'Coca Cola 330ml', 2, 5000.00, 100, 20, 'Minuman berkarbonasi'),
('DRK002', 'Sprite 330ml', 2, 5000.00, 90, 20, 'Minuman soda jeruk'),
('DRK003', 'Fanta Orange 330ml', 2, 5000.00, 85, 20, 'Minuman soda rasa jeruk'),
('DRK004', 'Aqua 600ml', 2, 3500.00, 120, 30, 'Air mineral'),
('DRK005', 'Teh Botol Sosro', 2, 4500.00, 70, 15, 'Teh manis dalam botol'),
('DRK006', 'Pocari Sweat', 2, 7500.00, 50, 10, 'Minuman isotonik'),
('DRK007', 'Mizone Apple Guava', 2, 6500.00, 45, 10, 'Minuman vitamin'),
('DRK008', 'Yakult', 2, 3000.00, 60, 15, 'Minuman probiotik'),
('DRK009', 'Kopi Kapal Api Susu', 2, 8500.00, 40, 10, 'Kopi susu siap minum'),
('DRK010', 'Fresh Tea Melati', 2, 4000.00, 55, 12, 'Teh melati'),

-- Makanan Instan (Category 3)
('INS001', 'Indomie Goreng', 3, 3500.00, 100, 25, 'Mie instan goreng'),
('INS002', 'Indomie Ayam Bawang', 3, 3500.00, 90, 25, 'Mie instan kuah ayam bawang'),
('INS003', 'Mie Sedaap Goreng', 3, 3500.00, 80, 20, 'Mie instan goreng'),
('INS004', 'Pop Mie Ayam', 3, 5500.00, 50, 15, 'Mie cup rasa ayam'),
('INS005', 'Supermi Ayam Bawang', 3, 3000.00, 70, 18, 'Mie instan ekonomis'),
('INS006', 'Sarimi Isi 2 Ayam', 3, 4500.00, 60, 15, 'Mie instan isi 2'),

-- Perawatan Tubuh (Category 4)
('HYG001', 'Lifebuoy Total 10', 4, 8500.00, 40, 10, 'Sabun batang antibakteri'),
('HYG002', 'Pantene Shampoo 170ml', 4, 18500.00, 30, 8, 'Shampo perawatan rambut'),
('HYG003', 'Pepsodent Whitening', 4, 12500.00, 35, 10, 'Pasta gigi pemutih'),
('HYG004', 'Rinso Detergent 770g', 4, 25000.00, 25, 5, 'Deterjen bubuk'),
('HYG005', 'Mama Lime 750ml', 4, 8500.00, 45, 12, 'Cairan pencuci piring'),

-- Alat Tulis (Category 5)
('STA001', 'Pulpen Pilot', 5, 3500.00, 50, 15, 'Pulpen tinta biru'),
('STA002', 'Pensil 2B Faber Castell', 5, 2500.00, 60, 20, 'Pensil grafit 2B'),
('STA003', 'Buku Tulis 58 Lembar', 5, 4500.00, 40, 10, 'Buku tulis bergaris'),
('STA004', 'Penghapus Karet', 5, 1500.00, 80, 25, 'Penghapus putih'),
('STA005', 'Tip-Ex Kertas', 5, 3000.00, 35, 10, 'Tip-ex koreksi'),

-- Obat-obatan (Category 6)
('MED001', 'Panadol Extra', 6, 8500.00, 30, 8, 'Obat sakit kepala'),
('MED002', 'Antangin JRG', 6, 2500.00, 50, 15, 'Obat masuk angin'),
('MED003', 'Tolak Angin', 6, 3000.00, 45, 12, 'Jamu masuk angin'),
('MED004', 'Betadine 60ml', 6, 25000.00, 20, 5, 'Antiseptik luka'),

-- Rokok (Category 7)
('CIG001', 'Gudang Garam Surya', 7, 23000.00, 50, 10, 'Rokok kretek'),
('CIG002', 'Sampoerna Mild', 7, 25000.00, 45, 10, 'Rokok filter'),
('CIG003', 'Djarum Super', 7, 22000.00, 40, 8, 'Rokok kretek filter'),

-- Perlengkapan Rumah (Category 8)
('HOM001', 'Tissue Paseo', 8, 8500.00, 35, 10, 'Tissue wajah'),
('HOM002', 'Sunlight Lime 750ml', 8, 9500.00, 30, 8, 'Cairan pencuci piring'),
('HOM003', 'Baygon Aerosol', 8, 28000.00, 20, 5, 'Obat nyamuk semprot'),
('HOM004', 'Stella Regazza', 8, 45000.00, 15, 3, 'Pengharum ruangan'),

-- Makanan Kaleng (Category 9)
('CAN001', 'Sarden ABC 155g', 9, 12500.00, 40, 10, 'Sarden dalam saus tomat'),
('CAN002', 'Kornet Pronas 340g', 9, 28000.00, 25, 5, 'Daging kornet kaleng'),
('CAN003', 'Ayam Brand Tuna 185g', 9, 18500.00, 30, 8, 'Tuna dalam minyak'),

-- Susu & Produk Susu (Category 10)
('MLK001', 'Susu Dancow 800g', 10, 65000.00, 20, 5, 'Susu bubuk untuk keluarga'),
('MLK002', 'Yogurt Cimory 120ml', 10, 6500.00, 35, 10, 'Yogurt drink'),
('MLK003', 'Keju Kraft Singles', 10, 32000.00, 15, 5, 'Keju lembaran'),
('MLK004', 'Susu Kental Manis Cap Nona', 10, 8500.00, 45, 12, 'Susu kental manis'),

-- Additional items to reach 50+
('SNK009', 'Sereal Koko Crunch', 1, 28000.00, 25, 5, 'Sereal cokelat'),
('DRK011', 'Es Teh Pucuk', 2, 4500.00, 60, 15, 'Teh hijau dingin'),
('INS007', 'Biskuit Monde Butter', 3, 15000.00, 30, 8, 'Biskuit butter cookies'),
('HYG006', 'Shampo Head & Shoulders', 4, 22000.00, 20, 5, 'Shampo anti ketombe');

-- ========================================
-- SAMPLE TRANSACTIONS (OPTIONAL)
-- ========================================

-- Sample Transaction 1
INSERT INTO transactions (user_id, total_amount, payment_method, cash_received, change_amount) VALUES
(2, 45500.00, 'cash', 50000.00, 4500.00);

SET @last_transaction_id = LAST_INSERT_ID();

INSERT INTO transaction_details (transaction_id, product_id, quantity, unit_price, subtotal) VALUES
(@last_transaction_id, 1, 2, 8500.00, 17000.00),
(@last_transaction_id, 9, 3, 5000.00, 15000.00),
(@last_transaction_id, 21, 1, 3500.00, 3500.00),
(@last_transaction_id, 30, 1, 12500.00, 12500.00);

-- Update stock after transaction
UPDATE products SET stock = stock - 2 WHERE id = 1;
UPDATE products SET stock = stock - 3 WHERE id = 9;
UPDATE products SET stock = stock - 1 WHERE id = 21;
UPDATE products SET stock = stock - 1 WHERE id = 30;

-- ========================================
-- VIEWS FOR REPORTS
-- ========================================

-- View untuk laporan penjualan harian
CREATE VIEW daily_sales_report AS
SELECT 
    DATE(t.transaction_date) as sale_date,
    COUNT(t.id) as total_transactions,
    SUM(t.total_amount) as total_revenue,
    AVG(t.total_amount) as avg_transaction_value
FROM transactions t
GROUP BY DATE(t.transaction_date)
ORDER BY sale_date DESC;

-- View untuk produk terlaris
CREATE VIEW best_selling_products AS
SELECT 
    p.name as product_name,
    c.name as category_name,
    SUM(td.quantity) as total_sold,
    SUM(td.subtotal) as total_revenue
FROM transaction_details td
JOIN products p ON td.product_id = p.id
JOIN categories c ON p.category_id = c.id
GROUP BY p.id, p.name, c.name
ORDER BY total_sold DESC;

-- View untuk stok rendah
CREATE VIEW low_stock_alert AS
SELECT 
    p.code,
    p.name,
    c.name as category,
    p.stock,
    p.min_stock,
    (p.min_stock - p.stock) as shortage
FROM products p
JOIN categories c ON p.category_id = c.id
WHERE p.stock <= p.min_stock
ORDER BY shortage DESC;