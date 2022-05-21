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

        Optional<User> opt = userRepo.getUserByUserId(userId);
        if (opt.isEmpty())
            return Optional.empty();
        User user = opt.get();
        return Optional.of(user);
    }

    public Optional<Integer> findUserIdByUsername (String username){
        Optional <Integer> opt = userRepo.getUserIdByUsername(username);
        if (opt.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(opt.get());
        }
    }

    public Optional<String> findUserRecordByUsername (String username){
        Optional <String> opt = userRepo.getUserRecordByUsername(username);
        if (opt.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(opt.get());
        }
    }




    public boolean insertUser (User user){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        Integer userId = Integer.parseInt(String.format("%06d", number));

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        
        boolean addedSuccess = userRepo.insertNewUser(userId, username, password, email);
    
            if (addedSuccess) {
                return true;
            } else {
                return false;
            } 
    }   
    
    public Optional<String> checkDuplicateEmail (User user) {
        String email = user.getEmail();
        Optional<String> opt = userRepo.getUserRecordByEmail(email);
        if (opt.isPresent()){
            return Optional.of(email);
            } else {
            return Optional.empty();
            }
    }

    public Optional<String> checkDuplicateUsername (User user) {
        String username = user.getUsername();

        Optional<String> opt = userRepo.getUserRecordByUsername(username);
        if (opt.isPresent()){
            return Optional.of(username);
        } else {
            return Optional.empty();
        }
    }



}



