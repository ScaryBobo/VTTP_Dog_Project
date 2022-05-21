package com.example.DogProject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.DogProject.model.User;
import com.example.DogProject.repository.UserRepository;
import com.example.DogProject.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService subject;

    @Mock
    private UserRepository userRepo = mock(UserRepository.class);

    @Test
    void canReturnValidUserTest(){
        // setup
        when(userRepo.getUserRecordByUsername(any()))
            .thenReturn(Optional.of("someUser"));

        // tests
        Optional<String> actual = subject.findUserRecordByUsername("someUserName");

        // asserts
        Optional<String> expected = Optional.of("someUser");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void canInsertUserTest(){
        //setup
        User testUser = new User("b@gmail.com", "test123", "87654321");
        
        // //test
        subject.insertUser(testUser);
        
        // //assert
        verify(userRepo).insertNewUser(
            any(), any(), any(), any());
    }

    @Test 
    void checkDuplicateEmailTest(){
       // setup
        User user = new User();
        user.setEmail("leslielowgy@gmail.com");

        when(userRepo.getUserRecordByEmail(any()))
            .thenReturn(Optional.of(user.getEmail()));
        //test
        Optional<String> actual = subject.checkDuplicateEmail(user);
        
        //assert
        Optional<String> expected = Optional.of(user.getEmail());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkDuplicateUsernameTest(){
         // setup
         User user = new User();
         user.setUsername("leslie");
 
         when(userRepo.getUserRecordByUsername(any()))
             .thenReturn(Optional.of(user.getUsername()));
         //test
         Optional<String> actual = subject.checkDuplicateUsername(user);
         
         //assert
         Optional<String> expected = Optional.of(user.getUsername());
         Assertions.assertEquals(expected, actual);
    }

    @Test
    void findUserWhenUserIdExist(){
        User user = new User();
        user.setUserId(123456);

        when(userRepo.getUserByUserId(anyInt()))
            .thenReturn(Optional.of(user));

        Optional<User> actual = subject.findUserByUserId(user.getUserId());

        Assertions.assertEquals(Optional.of(user), actual);
    }

    @Test
    void testFindUserIdByUsername(){

        User user = new User(); 
        user.setUserId(123456);
        user.setUsername("test123456");       

        when(userRepo.getUserIdByUsername(user.getUsername()))
            .thenReturn(Optional.of(user.getUserId()));

        Optional<Integer> actual = subject.findUserIdByUsername(user.getUsername());
        Assertions.assertEquals(Optional.of(user.getUserId()), actual);
    }
    

   




}
