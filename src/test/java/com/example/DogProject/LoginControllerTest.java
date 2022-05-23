package com.example.DogProject;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.DogProject.controller.LoginController;
import com.example.DogProject.model.User;
import com.example.DogProject.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userSvc;

    @Test
    void userLoginTestWhenUserExist(){
        RequestBuilder req = MockMvcRequestBuilders
                            .get("/login")
                            .queryParam("username", "test")
                            .queryParam("password", "123456")
                            .accept(MediaType.TEXT_HTML_VALUE);

        when(userSvc.findUserRecordByUsername("test")).thenReturn(Optional.of("username"));
          try {
              mvc.perform(req)
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.view().name("search"));
              
              
          } catch (Exception e) {
              fail("Invalid login");
          }                  
    }

    @Test
    void userLoginTestWhenUserDoesNotExist(){
        RequestBuilder req = MockMvcRequestBuilders
                            .get("/login")
                            .queryParam("username", "test")
                            .queryParam("password", "123456")
                            .accept(MediaType.TEXT_HTML_VALUE);
    
        when(userSvc.findUserRecordByUsername("test")).thenReturn(Optional.empty());
        try {
            mvc.perform(req)
            .andExpect(MockMvcResultMatchers.view().name("index"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("error"));
            
        } catch (Exception e) {
            fail("Invalid login");
        }
    }

    @Test
    void directToUserFormTest(){
        RequestBuilder req = MockMvcRequestBuilders
                            .post("/createUser")
                            .contentType(MediaType.TEXT_HTML_VALUE);
        try {
            mvc.perform(req)
            .andExpect(MockMvcResultMatchers.view().name("userform"));
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    @Test
    void createUserWhenDuplicatedEmailTest(){
        RequestBuilder req = MockMvcRequestBuilders
                            .post("/create")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("username", "test")
                            .param("password", "123456")
                            .param("email", "a@gmail.com");
    
       
    when(userSvc.checkDuplicateEmail(any())).thenReturn(Optional.of("email"));

    try {
        mvc.perform(req)
        .andExpect(MockMvcResultMatchers.view().name("userform"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("dupEmailError"));
        
    } catch (Exception e) {
        e.printStackTrace();
    }

    verify(userSvc).checkDuplicateEmail(any(User.class));
    verify(userSvc,never()).insertUser(any(User.class));
                                  
                            
    }

    @Test
    void createUserWhenDuplicatedUsernameTest(){
        RequestBuilder req = MockMvcRequestBuilders
                            .post("/create")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("username", "test")
                            .param("password", "123456")
                            .param("email", "a@gmail.com");
    
       
    when(userSvc.checkDuplicateUsername(any())).thenReturn(Optional.of("username"));

    try {
        mvc.perform(req)
        .andExpect(MockMvcResultMatchers.view().name("userform"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("dupUsernameError"));
        
    } catch (Exception e) {
        e.printStackTrace();
    }

    verify(userSvc).checkDuplicateUsername(any(User.class));
    verify(userSvc,never()).insertUser(any(User.class));
                                  
                            
    }

    @Test
    void directToLoginPageTest(){
        RequestBuilder req = MockMvcRequestBuilders
                                .post("/toLogin")
                                .contentType(MediaType.TEXT_HTML_VALUE);

        try {
            mvc.perform(req)
            .andExpect(MockMvcResultMatchers.view().name("index"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    

    
}
