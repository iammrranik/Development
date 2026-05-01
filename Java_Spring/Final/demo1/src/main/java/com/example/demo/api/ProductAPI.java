package com.example.demo.api;

import com.example.demo.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductAPI {
    static ArrayList<Product> products=  new ArrayList<>();

    public ProductAPI() {
        products.add(new Product("Chips", 100, 10f));
        products.add(new Product("Popcorn", 50, 30f));
        products.add(new Product("Pen", 500, 5.0f));
    }

    @GetMapping("/api/product")
    public ArrayList<Product> getProducts() {
        System.out.println("Get all products");
        return products;
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable Integer id) {
        if(id<0 || id>products.size()) {
            System.out.println("Invalid product ID");
            return null;
        }
        System.out.println("Get product with id " + (id-1));
        return products.get(id-1);
    }

    @PostMapping("/api/product")
    public Product addProduct(@RequestBody Product product) {
        if(product != null) {
            System.out.println("Add product " + product);
            products.add(product);
            return product;
        }
        System.out.println("Product can not be null");
        return null;
    }

    @PutMapping("/api/product/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        if(id<0 || id>products.size() ||  product!=null) {
            Product updatedProduct = products.get(id-1);
            updatedProduct.setName(product.getName());
            updatedProduct.setAvailableQuantity(product.getAvailableQuantity());
            updatedProduct.setUnitPrice(product.getUnitPrice());
            return updatedProduct;
        }
        System.out.println("Product can not be null or id not found");
        return null;
    }

    @DeleteMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        if(id<0 || id>products.size()) {
            System.out.println("Invalid product ID");
        }else{
            products.remove(id-1);
            System.out.println("Deleted product with id " + (id-1));
        }
    }


}

