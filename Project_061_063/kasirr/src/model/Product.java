package model;

public class Product {
    private int id;
    private String code;
    private String name;
    private double price;
    private int stock;
    private int minStock;

    public Product() {}

    public Product(int id, String code, String name, double price, int stock, int minStock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.minStock = minStock;
    }

    public int getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getMinStock() {
        return minStock;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }
}

