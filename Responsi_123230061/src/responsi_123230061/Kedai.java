package responsi_123230061;

public class Kedai extends Toko{
    private int id;
    private String nama;
    private String barang;
    private double harga;
    private double cicilan;
    private double bunga;
    private double angsuran;
    private double total;
    
    public Kedai(int id, String nama, String barang, double harga, double cicilan) {
        super(id, nama);
        this.barang = barang;
        this.harga = harga;
        this.cicilan = cicilan;
        updateBunga();
        updateAngsuran();
        updateTotal();

    }
    
    public void updateBunga(){
        this.bunga = (bunga / 100) * harga;
    }
    
    public void updateAngsuran(){
        this.angsuran = (harga / cicilan) + bunga;
    }
    
    public void updateTotal(){
        this.total = (angsuran * cicilan);
    }
    
    public String getBarang(){
        return barang;
    }
    
    public void  setBarang(String barang){
        this.barang = barang;
    }
    
    public double getHarga(){
        return harga;
    }
    
    public void  setHarga(double harga){
        this.harga = harga;
    }
    
    public double getCicilan(){
        return cicilan;
    }
    
    public void  setCicilan(double cicilan){
        this.cicilan = cicilan;
    }
    
    public double getBunga(){
        return bunga;
    }
    
    public double getAngsuran(){
        return angsuran;
    }
    
    public double getTotal(){
        return total;
    }
    
    
   
    
   
    
    

}

