package com.example.demo.api;

import com.example.demo.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerAPI {
    static ArrayList<Customer> customers=new ArrayList<>();

    public CustomerAPI() {
        customers.add(new Customer("A", "01111111111", "Dhaka"));
        customers.add(new Customer("B", "01111111112", "Rangpur"));
        customers.add(new Customer("C", "01111111113", "Gulshan"));
    }

    @GetMapping("/api/customer")
    public ArrayList<Customer> getCustomers() {
        System.out.println("Get all customers");
        return customers;
    }

    @GetMapping("/api/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        if(id<0 || id>customers.size()){
            System.out.println("Customer with id "+id+" not found");
            return null;
        }
        System.out.println("Get customer with id " + (id-1));
        return customers.get(id-1);
    }

    @PostMapping("/api/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        if(customer!=null) {
            customers.add(customer);
            System.out.println("Add customer " + customer);
            return customer;
        }
        System.out.println("Customer can not be null");
        return null;
    }

    @PutMapping("/api/customer/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        Customer updatedCustomer = customers.get(id-1);
        if(updatedCustomer!=null){
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setAddress(customer.getAddress());
            System.out.println("Updated customer " + updatedCustomer);
            return updatedCustomer;
        }
        System.out.println("Customer can't be updated");
        return null;
    }

    @DeleteMapping("/api/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        Customer customer = customers.get(id-1);
        if(customer!=null){
            customers.remove(customer);
            System.out.println("Deleted customer " + customer);
        }else{
            System.out.println("Can not delete customer with id " + (id-1));
            System.out.println("More details: \n" + customers.toString());
        }
    }



}
