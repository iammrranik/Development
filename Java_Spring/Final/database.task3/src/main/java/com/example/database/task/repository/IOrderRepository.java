package com.example.database.task.repository;

import com.example.database.task.domain.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    int save(Order order);
    Optional<Order> findById(Integer id);
    List<Order> findAll();
    int updateReturnDetails(Order order);
}