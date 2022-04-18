package com.local.message.LocalMessage.service;

import com.local.message.LocalMessage.dto.UserDTO;
import com.local.message.LocalMessage.model.User;
import com.local.message.LocalMessage.reposiotory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String saveUser(UserDTO userDTO){
        if(userRepository.findUserByUsername(userDTO.getUsername())==null) {
            User user = new User(userDTO.getUsername(), userDTO.getPassword());
            userRepository.save(user);
            return "user saved" ;
        }else return "userAlreadyExists";
    }

    public ResponseEntity<User> authenticate(UserDTO userDTO){
        User user=userRepository.findUserByUsername(userDTO.getUsername());
        if(user==null||!user.getPassword().equals(userDTO.getPassword()))return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        else return new ResponseEntity<>(user, HttpStatus.OK);
    }

}