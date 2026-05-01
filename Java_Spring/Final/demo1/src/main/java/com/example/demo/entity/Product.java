package com.example.demo.entity;

public class Product {
    private int id;
    private String name;
    private int availableQuantity;
    private float unitPrice;
    private static int totalProduct = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static int getTotalProduct() {
        return totalProduct;
    }

    public static void setTotalProduct(int totalProduct) {
        Product.totalProduct = totalProduct;
    }

    public Product(String name, int availableQuantity, float unitPrice) {
        this.setId(++totalProduct);
        this.setName(name);
        this.setAvailableQuantity(availableQuantity);
        this.setUnitPrice(unitPrice);
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName() + " " + this.getAvailableQuantity() + " " + this.getUnitPrice() + "\n";
    }


}

