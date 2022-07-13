package com.local.message.LocalMessage.controller;

import com.local.message.LocalMessage.dto.UserDTO;
import com.local.message.LocalMessage.model.User;
import com.local.message.LocalMessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public ResponseEntity<String> addNewUserToDatabase(@RequestBody UserDTO userDTO){
        return new ResponseEntity<String>(userService.saveUser(userDTO), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<User> authentication(@RequestBody UserDTO userDTO){
        return userService.authenticate(userDTO);
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<String> authenticationGet(){
        String str="aa";
        return new ResponseEntity<String>(str,HttpStatus.OK);
    }
}
