package tugas1_123230061;

public class Buku extends Barang {
    private String penulis;

    public Buku(String nama, double harga, int stok, String penulis) {
        super(nama, harga, stok);
        this.penulis = penulis;
    }

    @Override
    public void cekKualitas() {
        System.out.println("Cek berdasarkan penulis terkenal: " + penulis);
    }
}
