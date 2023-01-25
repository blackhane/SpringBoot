package kr.co.farmstory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("user/terms")
    public String terms(){
        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("user/register")
    public String register(String vo){
        return "redirect:/user/login";
    }
}
