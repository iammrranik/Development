package com.example.database.task.service;

import com.example.database.task.domain.Book;
import com.example.database.task.domain.Order;
import com.example.database.task.repository.BookRepository;
import com.example.database.task.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Order borrowBook(int memberId, int bookId, int quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found."));

        if (book.getAvailableCopies() < quantity) {
            throw new RuntimeException("Insufficient stock!");
        }

        LocalDate today = LocalDate.now();
        Order order = new Order();
        order.setMemberId(memberId);
        order.setBookId(bookId);
        order.setQuantity(quantity);
        order.setBorrowDate(today);
        order.setExpectedReturnDate(today.plusDays(7));
        order.setFine(0.0f);

        orderRepository.save(order);
        bookRepository.updateAvailableCopies(bookId, book.getAvailableCopies() - quantity);

        return order;
    }

    @Override
    @Transactional
    public Order returnBook(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        if (order.getActualReturnDate() != null) {
            throw new RuntimeException("Book already returned.");
        }

        LocalDate today = LocalDate.now();
        float calculatedFine = 0.0f;

        // 15 taka per day fine if returned after expected date
        if (today.isAfter(order.getExpectedReturnDate())) {
            long lateDays = ChronoUnit.DAYS.between(order.getExpectedReturnDate(), today);
            calculatedFine = lateDays * 15.0f;
        }

        order.setActualReturnDate(today);
        order.setFine(calculatedFine);
        orderRepository.updateReturnDetails(order);

        // Restock the book
        Book book = bookRepository.findById(order.getBookId()).orElseThrow();
        bookRepository.updateAvailableCopies(book.getId(), book.getAvailableCopies() + order.getQuantity());

        return order;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}