package com.example.database.task.api;

import com.example.database.task.domain.Order;
import com.example.database.task.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderApi {

    private final OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/borrow")
    public void borrow(@RequestParam int memberId, @RequestParam int bookId, @RequestParam int quantity) {
        orderService.borrowBook(memberId, bookId, quantity);
    }

    @PostMapping("/return/{orderId}")
    public void returnBook(@PathVariable int orderId) {
        orderService.returnBook(orderId);
    }

    @GetMapping
    public List<Order> findAll() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> findOne(@PathVariable int id) {
        return orderService.getOrderById(id);
    }
}