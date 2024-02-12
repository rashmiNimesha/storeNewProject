package com.example.storenewproject;

public class CategoryTable {
    private String categoryName;
    private String referenceNo;
    private String description;

    public CategoryTable(String categoryName, String referenceNo, String description) {
        this.categoryName = categoryName;
        this.referenceNo = referenceNo;
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
