package com.exadel.placebook.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld(String name) {
        return "Hello world!";
    }
    @GetMapping("/status")
    public String StatusController (String name) {
        return "Application is Running!";
    }

}