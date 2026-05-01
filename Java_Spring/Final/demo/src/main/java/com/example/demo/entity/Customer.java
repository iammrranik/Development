package com.example.demo.entity;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String address;
    private static int totalCustomer=0;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static int getTotalCustomer() {
        return totalCustomer;
    }

    public static void setTotalCustomer(int totalCustomer) {
        Customer.totalCustomer = totalCustomer;
    }

    public Customer(String name, String phone, String address) {
        this.setId(++totalCustomer);
        this.setName(name);
        this.setPhone(phone);
        this.setAddress(address);
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName() + " " + this.getPhone() + " " + this.getAddress() + "\n";
    }

}
