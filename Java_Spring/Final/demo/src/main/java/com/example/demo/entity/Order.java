package com.example.demo.entity;

public class Order {
    private int productId;
    private int orderedQuantity;
    private int id;
    private float totalPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(int productId, int orderedQuantity, int id, float totalPrice) {
        this.setProductId(productId);
        this.setOrderedQuantity(orderedQuantity);
        this.setId(id);
        this.setTotalPrice(totalPrice);
    }


}

