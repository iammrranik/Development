package com.example.database.task.repository;

import com.example.database.task.domain.Order;
import com.example.database.task.repository.Mapper.OrderMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository implements IOrderRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public OrderRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int save(Order order) {
        String sql = """
            INSERT INTO orders (memberId, bookId, quantity, borrowDate, expectedReturnDate, fine)
            VALUES (:memberId, :bookId, :quantity, :borrowDate, :expectedReturnDate, :fine)
            """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("memberId", order.getMemberId())
                .addValue("bookId", order.getBookId())
                .addValue("quantity", order.getQuantity())
                .addValue("borrowDate", order.getBorrowDate())
                .addValue("expectedReturnDate", order.getExpectedReturnDate())
                .addValue("fine", 0.0f); // Initialize fine to 0

        return jdbc.update(sql, params);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        String sql = "SELECT * FROM orders WHERE id = :id";
        List<Order> orders = jdbc.query(sql, Map.of("id", id), new OrderMapper());
        return orders.stream().findFirst();
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders ORDER BY id DESC";
        return jdbc.query(sql, new OrderMapper());
    }

    @Override
    public int updateReturnDetails(Order order) {
        String sql = """
            UPDATE orders 
            SET actualReturnDate = :actualReturnDate, fine = :fine 
            WHERE id = :id
            """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("actualReturnDate", order.getActualReturnDate())
                .addValue("fine", order.getFine())
                .addValue("id", order.getId());

        return jdbc.update(sql, params);
    }
}