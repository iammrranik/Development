package com.example.database.task.domain;

import java.time.LocalDate;

public class Order {
    private int id;
    private int memberId;
    private int bookId;
    private int quantity;
    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private float fine;

    public Order() {}

    public Order(int id, int memberId, int bookId, int quantity, LocalDate borrowDate,
                 LocalDate expectedReturnDate, LocalDate actualReturnDate, float fine) {
        this.setId(id);
        this.setMemberId(memberId);
        this.setBookId(bookId);
        this.setQuantity(quantity);
        this.setBorrowDate(borrowDate);
        this.setExpectedReturnDate(expectedReturnDate);
        this.setActualReturnDate(actualReturnDate);
        this.setFine(fine);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getExpectedReturnDate() { return expectedReturnDate; }
    public void setExpectedReturnDate(LocalDate expectedReturnDate) { this.expectedReturnDate = expectedReturnDate; }

    public LocalDate getActualReturnDate() { return actualReturnDate; }
    public void setActualReturnDate(LocalDate actualReturnDate) { this.actualReturnDate = actualReturnDate; }

    public float getFine() { return fine; }
    public void setFine(float fine) { this.fine = fine; }
}