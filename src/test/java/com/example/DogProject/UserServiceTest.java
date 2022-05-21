package com.example.DogProject;

import java.util.Optional;

import com.example.DogProject.model.User;
import com.example.DogProject.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userSvc;


    @Test
    void canReturnValidUserTest(){
        Optional<String> opt = userSvc.findUserRecordByUsername("leslie");
        Assertions.assertTrue(opt.isPresent());
    }

    @Test
    void canInsertUserTest(){
        
    }

    @Test 
    void checkDuplicateEmailTest(){
        User user = new User();
        user.setEmail("leslielowgy@gmail.com");
        Optional<String> opt = userSvc.checkDuplicateEmail(user);
        Assertions.assertTrue(opt.isPresent());
    }

    @Test
    void checkDuplicateUsernameTest(){
        User user = new User();
        user.setUsername("leslie");
        Optional<String> opt = userSvc.checkDuplicateUsername(user);
        Assertions.assertTrue(opt.isPresent());
    }

}
