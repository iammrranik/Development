package com.example.database.task.repository;

import com.example.database.task.domain.Book;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {

    int save(Book book);

    Optional<Book> findById(Integer id);

    List<Book> findAll(int page, int size);

    int count();

    int update(Book book);

    int delete(int id);

    int updateAvailableCopies(Integer id, int quantity);
}