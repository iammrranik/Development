package com.example.database.task.service;

import com.example.database.task.domain.Book;
import com.example.database.task.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll(int page, int size) {
        return bookRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return bookRepository.count();
    }

    @Override
    public int update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public int delete(int id) {
        return bookRepository.delete(id);
    }

    @Override
    public void updateStock(Integer bookId, int quantityChange) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            int newCount = book.getAvailableCopies() + quantityChange;

            if (newCount < 0) {
                throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
            }

            bookRepository.updateAvailableCopies(bookId, newCount);
        } else {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }
}