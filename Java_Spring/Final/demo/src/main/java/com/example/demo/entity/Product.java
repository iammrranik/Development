package com.example.demo.entity;

public class Product {
    private int id;
    private String name;
    private int availableQuantity;
    private float unitPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Product(int id, String name, int availableQuantity, float unitPrice) {
        this.setId(id);
        this.setName(name);
        this.setAvailableQuantity(availableQuantity);
        this.setUnitPrice(unitPrice);

    }


}

