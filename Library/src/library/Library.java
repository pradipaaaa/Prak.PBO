package library;

public class Library {
    private int id;
    private String judul, genre, penulis, penerbit, lokasi;
    private int stok;

    public Library(int id, String judul, String genre, String penulis, String penerbit, String lokasi, int stok) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.lokasi = lokasi;
        this.stok = stok;
    }

    public Library(String judul, String genre, String penulis, String penerbit, String lokasi, int stok) {
        this(0, judul, genre, penulis, penerbit, lokasi, stok);
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }

    public String getPenerbit() { return penerbit; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }

    public String getLokasi() { return lokasi; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}
