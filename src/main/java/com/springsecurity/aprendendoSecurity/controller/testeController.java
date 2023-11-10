package com.springsecurity.aprendendoSecurity.controller;

import com.springsecurity.aprendendoSecurity.entity.UserEntity;
import com.springsecurity.aprendendoSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class testeController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String teste(){
        return "<h1> cadastrar </h1>";
    }

    @GetMapping(value = "/livre")
    @PreAuthorize("hasRole('USER')")
    public String testeLivre(){
        return "<h1> Você acessou! </h1>";
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<UserEntity> cadastrar(@RequestBody final UserEntity user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(user));
        } catch (Exception e){
            throw new RuntimeException(HttpStatus.BAD_REQUEST + e.getMessage());
        }
    }
}
