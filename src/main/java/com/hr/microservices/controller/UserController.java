package com.hr.microservices.controller;

import com.hr.microservices.entity.User;
import com.hr.microservices.repository.master.UserMasterRepository;
import com.hr.microservices.repository.slave.UserSlaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserMasterRepository userMasterRepository;
    @Autowired
    private UserSlaveRepository userSlaveRepository;

    @GetMapping("/active/{user-id}")
    public ResponseEntity<User> getActiveUser(@PathVariable("user-id") final Integer userId) {
        final Optional<User> userOptional = userMasterRepository.findById(userId);
        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/inactive/{user-id}")
    public ResponseEntity<User> getInactiveUser(@PathVariable("user-id") final Integer userId) {
        final Optional<User> userOptional = userSlaveRepository.findById(userId);
        return userOptional.isPresent() ? ResponseEntity.ok(userOptional.get()) : ResponseEntity.badRequest().build();
    }
}