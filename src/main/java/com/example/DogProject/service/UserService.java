package com.example.DogProject.service;

import java.util.Optional;
import java.util.Random;

import com.example.DogProject.model.User;
import com.example.DogProject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public Optional<User> findUserByUserId (Integer userId){
        return userRepo.getUserByUserId(userId);
    
    }

    public Optional<Integer> findUserIdByUsername (String username){
         return userRepo.getUserIdByUsername(username);
    }

    public Optional<String> findUserRecordByUsername (String username){
        return userRepo.getUserRecordByUsername(username);
    }

    public boolean insertUser (User user){
        return userRepo.insertNewUser(
            getUserId(), 
            user.getUsername(), 
            user.getPassword(), 
            user.getEmail());
    }   
    
    public Optional<String> checkDuplicateEmail (User user) {
        return userRepo.getUserRecordByEmail(user.getEmail());
    }

    public Optional<String> checkDuplicateUsername (User user) {
        return userRepo.getUserRecordByUsername(user.getUsername());
    }

    private int getUserId(){
        Random rnd = new Random();
        return Integer.parseInt(
            String.format("%06d", rnd.nextInt(999999)));
    }
}



