package com.exadel.placebook.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world!";
    }
    @GetMapping("/status")
    public String StatusController () {
        return "Application is Running!";
    }

}