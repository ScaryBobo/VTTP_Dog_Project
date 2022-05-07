package com.example.DogProject.controller;



import com.example.DogProject.service.DogService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping (path="/")
public class DogController {
    @Autowired
    private DogService dogSvc;

    @GetMapping (path="/random")
    public ModelAndView getRandomDogImages (){
        ModelAndView mvc = new ModelAndView();
        String imageUrl = dogSvc.getRandomDog();
        mvc.setViewName("imagepreview");
        mvc.addObject("image", imageUrl);

        return mvc;
    }
}
