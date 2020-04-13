package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MessageController {

    @GetMapping("/")
    public ModelAndView showPage() {
        return new ModelAndView("index");
    }

    @PostMapping("/")
    public ModelAndView returnMessage(@RequestParam(required = false) String message) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", message);
        return mav;
    }
}
