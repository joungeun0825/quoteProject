package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class ViewController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/create/book")
    public String createBookView(){
        return "createBook";
    }


}
