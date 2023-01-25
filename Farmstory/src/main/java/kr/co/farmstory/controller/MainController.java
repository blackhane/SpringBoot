package kr.co.farmstory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Clock;

@Controller
public class MainController {

    @GetMapping(value = {"", "index"})
    public String index(){
        return "index";
    }
}
