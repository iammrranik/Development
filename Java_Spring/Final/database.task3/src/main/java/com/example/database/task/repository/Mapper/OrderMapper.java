package com.example.database.task.repository.Mapper;

import com.example.database.task.domain.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getInt("id"),
                rs.getInt("memberId"),
                rs.getInt("bookId"),
                rs.getInt("quantity"),
                rs.getDate("borrowDate") != null ? rs.getDate("borrowDate").toLocalDate() : null,
                rs.getDate("expectedReturnDate") != null ? rs.getDate("expectedReturnDate").toLocalDate() : null,
                rs.getDate("actualReturnDate") != null ? rs.getDate("actualReturnDate").toLocalDate() : null,
                rs.getFloat("fine")
        );
    }
}