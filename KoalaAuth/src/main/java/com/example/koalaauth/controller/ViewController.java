package com.example.koalaauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/attend")
    public String attend(){
        return "attend";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/loginerror")
    String loginerror(){
        return "loginerror";
    }
}


