package com.exadel.placebook.controller;


import com.exadel.placebook.exception.ApplicationRuntimeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld() throws Exception{
        return "Hello world!";
    }
    @GetMapping("/status")
    public String StatusController () throws Exception{
        return "Application is running!";
    }
}