package responsi_123230061;

import java.util.List;

public class Control {
    private CrudToko toko = new CrudToko();
    
    public void tambahBayar(String nama, String barang, double harga, double cicilan) {
        Kedai kedai = new Kedai(0, nama, barang, harga, cicilan);
        try {
            toko.insert(kedai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateBayar(int id, String nama, String barang, double harga, double cicilan) {
        Kedai kedai = new Kedai(id, nama, barang, harga, cicilan);
        try {
            toko.update(kedai);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hapusBayar(int id) {
        try {
            toko.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Kedai> getAllKedai(){
        try {
            return toko.getAllKedai();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
