package com.example.DogProject.controller;

import java.util.Optional;

import com.example.DogProject.model.User;
import com.example.DogProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping (path="/")
public class LoginController {
    
    @Autowired
    private UserService userSvc;

    @GetMapping (path="/login")
    public String userLogin(@RequestParam (name="username") String username, 
                            @RequestParam (name="password") String password, Model model) {
    
    Optional<String> verifyifUserExist = userSvc.findUserByUsername(username); 
    if (!verifyifUserExist.isEmpty()){
        return "search";
    } else {
        model.addAttribute("error", "Invalid Login");
        return "index";
    }
   

    }

    @PostMapping (path="/createUser")
        public String directToUserForm() {
            return "userform";
        }
    

    @PostMapping(path="/create")
    public String createUser(@RequestBody MultiValueMap<String,String> form){
        User user = new User();
        user.setUsername(form.getFirst("username"));
        user.setPassword(form.getFirst("password"));
        user.setEmail(form.getFirst("email"));
        boolean addedSuccess = userSvc.insertUser(user);
        if (addedSuccess){
            return "insertsuccess";
        } else {
            return "error";
        }
        
    }

    @PostMapping(path="/toLogin")
        public String directToLoginPage(){
            return "index";
    }
}


