package com.example.DogProject.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.DogProject.model.Dog;
import com.example.DogProject.service.DogService;
import com.example.DogProject.service.UserService;

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
    
    @Autowired
    private UserService userSvc;

    @GetMapping (path="/random")
    public ModelAndView getRandomDogImages (HttpSession sess){
        ModelAndView mvc = new ModelAndView();
        Dog dog = dogSvc.getRandomDog();

        String imageUrl = dog.getDogImageUrl();
        String dogName = dog.getDogName();
        String dogHeight = dog.getDogHeight();
        String dogWeight = dog.getDogWeight();
        String bredPurpose = dog.getBredPurpose();
        String breedGroup = dog.getBreedGroup();
        String lifespan = dog.getLifeSpan();
        String temperament = dog.getTemperament();
        mvc.setViewName("imagepreview");
        mvc.addObject("image",imageUrl );
        mvc.addObject("dogName", dogName);
        mvc.addObject("dogHeight", dogHeight);
        mvc.addObject("dogWeight", dogWeight);
        mvc.addObject("bredPurpose", bredPurpose);
        mvc.addObject("breedGroup", breedGroup);
        mvc.addObject("lifespan", lifespan);
        mvc.addObject("temperament", temperament);

        sess.setMaxInactiveInterval(60*60);
        System.out.println(sess.getAttribute("username"));
        String username = sess.getAttribute("username").toString();
        Integer userId = userSvc.findUserIdByUsername(username).get();
        boolean insertRandomSearchRecord = dogSvc.insertRandomSearchHistory(userId, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament, imageUrl);

        return mvc;
    }

    @GetMapping (path="/search")
    public ModelAndView dogQuery(@RequestParam (name="breedName") String breedName, HttpSession sess){
        ModelAndView mvc = new ModelAndView();
        System.out.println("breed name " + breedName);
        Dog dog = dogSvc.queryDog(breedName.trim());
        String dogName = dog.getDogName();
        String dogHeight = dog.getDogHeight();
        String dogWeight = dog.getDogWeight();
        String bredPurpose = dog.getBredPurpose();
        String breedGroup = dog.getBreedGroup();
        String lifespan = dog.getLifeSpan();
        String temperament = dog.getTemperament();
        
        mvc.setViewName("imagepreview");
        mvc.addObject("dogName", dogName);
        mvc.addObject("dogHeight", dogHeight);
        mvc.addObject("dogWeight", dogWeight);
        mvc.addObject("bredPurpose", bredPurpose);
        mvc.addObject("breedGroup", breedGroup);
        mvc.addObject("lifespan", lifespan);
        mvc.addObject("temperament", temperament);

        sess.setMaxInactiveInterval(60*60);
        System.out.println(sess.getAttribute("username"));
        String username = sess.getAttribute("username").toString();
        Integer userId = userSvc.findUserIdByUsername(username).get();
        boolean insertQuerySearchRecord = dogSvc.insertQuerySearchHistory(userId, dogName, dogHeight, dogWeight, bredPurpose, breedGroup, lifespan, temperament);
        

        return mvc;
        
    }

    @GetMapping (path="/history")
    public ModelAndView searchHistory(HttpSession sess){
        ModelAndView mvc = new ModelAndView();
        sess.setMaxInactiveInterval(60*60);
        System.out.println(sess.getAttribute("username"));
        String username = sess.getAttribute("username").toString();
        Integer userId = userSvc.findUserIdByUsername(username).get();
        List<Dog> dogList = new ArrayList<>();
        dogList = dogSvc.getLatestFiveHistory(userId);



        mvc.setViewName("userhistory");
        mvc.addObject("dogs", dogList);
       
        return mvc;
    }
    
}
