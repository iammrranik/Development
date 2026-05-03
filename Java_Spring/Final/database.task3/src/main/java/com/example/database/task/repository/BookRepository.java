package com.example.database.task.repository;

import com.example.database.task.domain.Book;
import com.example.database.task.repository.Mapper.BookMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BookRepository implements IBookRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Book book) {
        String sql = """
                INSERT INTO book (title, author, availableCopies, price)
                VALUES (:title, :author, :availableCopies, :price)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor());
        params.addValue("availableCopies", book.getAvailableCopies());
        params.addValue("price", book.getPrice());

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Book> findById(Integer id) {
        String sql = """
                SELECT id, title, author, availableCopies, price
                FROM book
                WHERE id = :id
                """;
        Map<String, Object> params = Map.of("id", id);
        List<Book> books = namedParameterJdbcTemplate.query(sql, params, new BookMapper());

        return books.stream().findFirst();
    }

    @Override
    public List<Book> findAll(int page, int size) {
        int offset = (page <= 1) ? 0 : (page - 1) * size;

        String sql = """
                SELECT id, title, author, availableCopies, price
                FROM book
                ORDER BY id
                LIMIT :limit OFFSET :offset
                """;
        Map<String, Object> params = Map.of("offset", offset, "limit", size);
        List<Book> books = namedParameterJdbcTemplate.query(sql, params, new BookMapper());

        return books.isEmpty() ? Collections.emptyList() : books;
    }

    @Override
    public int count() {
        String sql = "SELECT count(*) FROM book";
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public int update(Book book) {
        String sql = """
                UPDATE book
                SET title = :title, author = :author, 
                    availableCopies = :availableCopies, price = :price
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor());
        params.addValue("availableCopies", book.getAvailableCopies());
        params.addValue("price", book.getPrice());
        params.addValue("id", book.getId());

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateAvailableCopies(Integer id, int quantity) {
        String sql = """
                UPDATE book 
                SET availableCopies = :availableCopies 
                WHERE id = :id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("availableCopies", quantity);
        params.addValue("id", id);

        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM book WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.update(sql, params);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
}