package com.springsecurity.aprendendoSecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class testeController {

    @GetMapping(value = "/userAuth")
    @PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1> Teste </h1>";
    }

    @GetMapping(value = "/livre")
    public String testeLivre(){
        return "<h1> Você acessou! </h1>";
    }
}
