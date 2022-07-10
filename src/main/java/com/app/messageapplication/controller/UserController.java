package com.app.messageapplication.controller;

import com.app.messageapplication.Entity.User;
import com.app.messageapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers/{username}")
    public List<User> getUsers(@PathVariable(value = "username") String username){
        return userService.getConversations(username);
    }
}
