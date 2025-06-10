package tugas1_123230061;

public abstract class Barang implements Produk {
    protected String nama;
    protected double harga;
    protected int stok;

    public Barang(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public abstract void cekKualitas();

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama + ", Harga: " + harga + ", Stok: " + stok);
    }

    @Override
    public void hitungHargaDiskon(double diskon) {
        double hargaDiskon = harga - (harga * diskon / 100);
        System.out.println("Harga setelah diskon: " + hargaDiskon);
    }

    @Override
    public void cekStok() {
        if (stok > 0) {
            System.out.println("Stok tersedia.");
        } else {
            System.out.println("Stok habis.");
        }
    }
}
