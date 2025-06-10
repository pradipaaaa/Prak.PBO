package tugas1_123230061;

public class Main {
    public static void main(String[] args) {
        try {
           
            Makanan makanan = new Makanan("Roti", 15000, 10, "12-06-2025");
            Elektronik elektronik = new Elektronik("Laptop", 5000000, 5, 24);
            Buku buku = new Buku("Pemrograman Java", 120000, 1, "Elvandra");

            makanan.tampilkanInfo();
            elektronik.tampilkanInfo();
            buku.tampilkanInfo();

            makanan.hitungHargaDiskon(10);
            elektronik.hitungHargaDiskon(5);
            buku.hitungHargaDiskon(15);

            makanan.cekStok();
            elektronik.cekStok();
            buku.cekStok();

            makanan.cekKualitas();
            elektronik.cekKualitas();
            buku.cekKualitas();


            if (buku.stok == 0) {
                throw new Exception("Error: Stok buku habis!");
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}