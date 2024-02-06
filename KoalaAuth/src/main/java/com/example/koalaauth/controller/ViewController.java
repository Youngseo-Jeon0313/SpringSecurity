package com.example.koalaauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }
    @RequestMapping("/attend")
    public String attend(){
        return "attend";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginerror")
    public String loginerror(){
        return "loginerror";
    }
}


