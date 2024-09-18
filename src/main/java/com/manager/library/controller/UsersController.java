package com.manager.library.controller;

import com.manager.library.model.domain.Users;
import com.manager.library.model.dtos.UserRequestDTO;
import com.manager.library.model.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        Users createdUser = usersService.createUser(userRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(
            @PathVariable UUID id,
            @RequestBody UserRequestDTO userRequestDTO) {
        Users updatedUser = usersService.updateUser(userRequestDTO, id);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable UUID id) {
        Users user = usersService.getUser(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }
}