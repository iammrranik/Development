package com.example.demo.api;

import com.example.demo.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductAPI {
    static ArrayList<Product> products=  new ArrayList<>();

    public ProductAPI() {
        products.add(new Product(1, "Chips", 100, 10f));
        products.add(new Product(2, "Popcorn", 50, 30f));
        products.add(new Product(3, "Pen", 500, 5.0f));
    }

    @GetMapping("/api/product")
    public ArrayList<Product> getProducts() {
        System.out.println("Get all products");
        return products;
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable Integer id) {
        System.out.println("Get product with id " + (id-1));
        return products.get(id-1);
    }

    @PostMapping("/api/product")
    public Product addProduct(@RequestBody Product product) {
        System.out.println("Add product " + product);
        products.add(product);
        return product;
    }

}

