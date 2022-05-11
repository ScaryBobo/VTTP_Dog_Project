package com.example.DogProject.controller;




import com.example.DogProject.service.DogService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping (path="/")
public class DogController {
    @Autowired
    private DogService dogSvc;

    @GetMapping (path="/random")
    public ModelAndView getRandomDogImages (){
        ModelAndView mvc = new ModelAndView();
        String imageUrl = dogSvc.getRandomDog().getDogImageUrl();
        String dogName = dogSvc.getRandomDog().getDogName();
        String dogHeight = dogSvc.getRandomDog().getDogHeight();
        String dogWeight = dogSvc.getRandomDog().getDogWeight();
        String bredPurpose = dogSvc.getRandomDog().getBredPurpose();
        String breedGroup = dogSvc.getRandomDog().getBreedGroup();
        String lifespan = dogSvc.getRandomDog().getLifeSpan();
        String temperament = dogSvc.getRandomDog().getTemperament();
        mvc.setViewName("imagepreview");
        mvc.addObject("image",imageUrl );
        mvc.addObject("dogName", dogName);
        mvc.addObject("dogHeight", dogHeight);
        mvc.addObject("dogWeight", dogWeight);
        mvc.addObject("bredPurpose", bredPurpose);
        mvc.addObject("breedGroup", breedGroup);
        mvc.addObject("lifespan", lifespan);
        mvc.addObject("temperament", temperament);
        
        return mvc;
    }

    @GetMapping (path="/search")
    public ModelAndView dogQuery(@RequestParam (name="breedName") String breedName){
        ModelAndView mvc = new ModelAndView();
       
        String imageUrl = dogSvc.queryDog(breedName).getDogImageUrl();
        String dogName = dogSvc.queryDog(breedName).getDogName();
        // mvc.setViewName("imagepreview");
        // mvc.addObject("image",imageUrl );
        mvc.addObject("dogName", dogName);
        return mvc;
        
    }
    
}
