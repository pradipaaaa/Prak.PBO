package model;

public class TransactionDetail {
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    // getter dan setter
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}


