package com.chatsound.chatsound_jhahn_v0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String goToMain(){
        return "mainPage";
    }
}
