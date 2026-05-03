package com.example.database.task.api;

import com.example.database.task.domain.Book;
import com.example.database.task.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookApi {

    private final BookService bookService;

    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }

    @GetMapping("/{page}/{size}")
    public List<Book> findAll(@PathVariable int page, @PathVariable int size) {
        return bookService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable int id) {
        return bookService.findById(id);
    }

    @GetMapping("/count")
    public int count() {
        return bookService.count();
    }
}