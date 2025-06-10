package tugas1_123230061;

public class Makanan extends Barang {
    private String tanggalKadaluarsa;

    public Makanan(String nama, double harga, int stok, String tanggalKadaluarsa) {
        super(nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public void cekKualitas() {
        System.out.println("Cek kualitas berdasarkan tanggal kadaluarsa: " + tanggalKadaluarsa);
    }
}
