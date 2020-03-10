package com.example.backgroundsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping({"/","index.html"})
    public String index(){
        return "BkSys/index";
    }

    @GetMapping("welcome")
    public String welcome(){
        return "BkSys/welcome";
    }
}
