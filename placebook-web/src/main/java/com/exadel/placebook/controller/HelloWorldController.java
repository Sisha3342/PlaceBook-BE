package com.exadel.placebook.controller;


import com.exadel.placebook.exception.ErrorResponseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld() throws Exception{
        if(true) throw new ErrorResponseException("hello im exception");
        return "Hello world!";
    }
}