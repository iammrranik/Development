package com.example.demo.api;

import com.example.demo.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerAPI {
    static ArrayList<Customer> customers=new ArrayList<>();

    public CustomerAPI() {
        customers.add(new Customer(1, "A", "01111111111", "Dhaka"));
        customers.add(new Customer(2, "B", "01111111112", "Rangpur"));
        customers.add(new Customer(3, "C", "01111111113", "Gulshan"));
    }

    @GetMapping("/api/customer")
    public ArrayList<Customer> getCustomers() {
        System.out.println("Get all customers");
        return customers;
    }

    @GetMapping("/api/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        System.out.println("Get customer with id " + (id-1));
        return customers.get(id-1);
    }

    @PostMapping("/api/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        System.out.println("Add customer " + customer);
        return customer;
    }



}
