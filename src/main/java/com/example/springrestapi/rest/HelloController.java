package com.example.springrestapi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String index() {
        return "hello_yes_or_not_or_yes";
    }

}