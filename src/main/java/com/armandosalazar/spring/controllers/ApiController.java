package com.armandosalazar.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armandosalazar.spring.dto.UserRequest;
import com.armandosalazar.spring.serivices.interfaces.UserService;
import com.armandosalazar.spring.utils.exceptions.ApiUnprocessableEntity;
import com.armandosalazar.spring.validator.UserValidator;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class ApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping(value = "/by/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody UserRequest user) throws ApiUnprocessableEntity {
        this.userValidator.validate(user);
        this.userService.save(user);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody UserRequest user, @PathVariable Integer id)
            throws ApiUnprocessableEntity {
        this.userService.update(user, id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        this.userService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
