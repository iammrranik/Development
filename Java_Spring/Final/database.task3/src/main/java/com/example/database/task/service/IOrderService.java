package com.example.database.task.service;

import com.example.database.task.domain.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order borrowBook(int memberId, int bookId, int quantity);
    Order returnBook(int orderId);
    Optional<Order> getOrderById(int id);
    List<Order> getAllOrders();
}