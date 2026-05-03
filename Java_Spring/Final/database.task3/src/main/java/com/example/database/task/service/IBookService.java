package com.example.database.task.service;


import com.example.database.task.domain.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book save(Book book);
    Optional<Book> findById(Integer id);
    List<Book> findAll(int page, int size);
    int count();
    int update(Book book);
    int delete(int id);
    void updateStock(Integer bookId, int quantityChange);
}