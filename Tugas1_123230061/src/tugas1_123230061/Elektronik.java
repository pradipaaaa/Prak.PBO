package tugas1_123230061;

public class Elektronik extends Barang {
    private int garansi;

    public Elektronik(String nama, double harga, int stok, int garansi) {
        super(nama, harga, stok);
        this.garansi = garansi;
    }

    @Override
    public void cekKualitas() {
        System.out.println("Cek kualitas berdasarkan garansi: " + garansi + " bulan.");
    }
}