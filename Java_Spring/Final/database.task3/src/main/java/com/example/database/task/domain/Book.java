package com.example.database.task.domain;

public class Book {
    private int id;
    private String title;
    private String author;
    private int availableCopies;
    private float price;

    public Book() {}

    public Book(int id, String title, String author, int availableCopies, float price) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setAvailableCopies(availableCopies);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
