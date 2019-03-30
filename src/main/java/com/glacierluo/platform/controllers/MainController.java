package com.glacierluo.platform.controllers;

import com.glacierluo.platform.entity.User;
import com.glacierluo.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/")
public class MainController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public @ResponseBody String register (@RequestBody User user){
        userRepository.save(user);
        return "{\"desc\": \"Register success!\", \"code\": 200 }";
    }



    @GetMapping("/allUser")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
