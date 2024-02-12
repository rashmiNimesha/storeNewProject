package com.example.storenewproject;
public class ProductTable {
    private String ProductName;
    private String referenceNumber;
    private String category;

    public ProductTable(String productName, String referenceNumber, String category) {
        ProductName = productName;
        this.referenceNumber = referenceNumber;
        this.category = category;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
