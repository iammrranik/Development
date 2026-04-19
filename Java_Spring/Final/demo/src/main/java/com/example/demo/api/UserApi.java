package com.example.demo.api;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller // Only java understand this controller
@RestController // All frontend and backend can get data from this controller
@CrossOrigin
public class UserApi {

//    @RequestMapping("/user")
//    @GetMapping("/user")
    @GetMapping("/user/{id}")
//    public User getUser(@RequestParam(required = false) Integer id){
    public User getUser(@PathVariable("id") Integer id){
        System.out.println(id);
        User user = new User(1, "xyz", 20);
        return user;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        System.out.println(user);
        return user;
    }



}
