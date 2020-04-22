package com.example.test.controller;

import com.example.test.model.TestEntity;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService service;



    @GetMapping("/index")
    public ModelAndView showPage() {
        return new ModelAndView("index");
    }

    @PostMapping("/index")
    public ModelAndView addEntity(@RequestParam String firstName,
                                  @RequestParam String lastName) {
        ModelAndView mav = new ModelAndView("index");
        TestEntity entity = new TestEntity();
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        mav.addObject("entity", service.save(entity));
        return mav;
    }

    @PostMapping("/del")
    public ModelAndView deleteEntity(@RequestParam Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/all");
        service.delete(id);
        return mav;
    }

    @GetMapping("/all")
    public ModelAndView showAll() {
        ModelAndView mav = new ModelAndView("index");
        List<TestEntity> list = service.getAll();
        mav.addObject("all", list);
        return mav;
    }
}
