package com.example.storenewproject;

public class StockTable {
    private String name;
    private String unitPrice;
    private String quantity;
    private String supplier;

    public StockTable(String name, String unitPrice, String quantity, String supplier) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
