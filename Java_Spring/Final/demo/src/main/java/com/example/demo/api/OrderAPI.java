package com.example.demo.api;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

public class OrderAPI {
    static ArrayList<Order> orders = new ArrayList<>();
    static int id = 1;

    public OrderAPI() { }

    @GetMapping("/api/order")
    public ArrayList<Order> getOrders() {
        System.out.println("Get all Orders");
        return orders;
    }

    @GetMapping("/api/order/{id}")
    public Order getOrder(@PathVariable Integer id) {
        System.out.println("Get Order with id " + id);
        return orders.get(id);
    }

    @PostMapping("api/order")
    public Order createOrder(@RequestParam Integer productId, @RequestParam Integer quantity) {
        Product product = ProductAPI.products.get(productId-1);

        if(product==null || product.getAvailableQuantity()<quantity){
            System.out.println("Product not available");
            return null;
        }

        product.setAvailableQuantity(product.getAvailableQuantity()-quantity);
        float totalPrice = product.getUnitPrice() *quantity;

        Order order = new Order(productId, quantity, id, totalPrice);
        id++;
        orders.add(order);
        System.out.println("Order with id " + id + " created");
        return order;
    }


}
