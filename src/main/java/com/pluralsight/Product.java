package com.pluralsight;

public class Product {
    private String productName;
    private String SKU;
    private double price;
    private String department;

    public Product(String productName, String SKU, double price,String department ){
        this.productName = productName;
        this.SKU = SKU;
        this.price = price;
        this.department = department;
    }
    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        department = department;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

