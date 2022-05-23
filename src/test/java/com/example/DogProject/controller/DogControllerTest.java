package com.example.DogProject.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.DogProject.model.Dog;
import com.example.DogProject.service.DogService;
import com.example.DogProject.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = DogController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class DogControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DogService dogSvc;
    @MockBean
    private UserService userSvc;

    @Test
    void testDogQuery() {
        RequestBuilder req = MockMvcRequestBuilders
                                    .get("/search")
                                    .param("breedName", "test")
                                    .sessionAttr("username", "leslie");
        Dog expected = new Dog();
        expected.setDogName("Shiba Inu");
        expected.setDogWeight("8-10");
        expected.setDogHeight("34-42");
        expected.setBredPurpose("someReasons");
        expected.setBreedGroup("Non-Sporting");
        expected.setLifeSpan("12-16years");
        expected.setTemperament("someTemperament");
        
    when(dogSvc.queryDog("test")).thenReturn(expected);
    when(userSvc.findUserIdByUsername("leslie")).thenReturn(Optional.of(123456));

        try {
            mvc.perform(req)
            .andExpect(MockMvcResultMatchers.view().name("imagepreview"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogName"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogHeight"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogWeight")) 
            .andExpect(MockMvcResultMatchers.model().attributeExists("bredPurpose"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("breedGroup"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("lifespan"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("temperament"));                        
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(userSvc).findUserIdByUsername("leslie");
        verify(dogSvc).insertQuerySearchHistory(any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void testGetRandomDogImages() {
        RequestBuilder req = MockMvcRequestBuilders
                                    .get("/random")
                                    .sessionAttr("username", "leslie");

        
        Dog expected = new Dog();
        expected.setDogName("Shiba Inu");
        expected.setDogWeight("8-10");
        expected.setDogHeight("34-42");
        expected.setBredPurpose("someReasons");
        expected.setBreedGroup("Non-Sporting");
        expected.setLifeSpan("12-16years");
        expected.setTemperament("someTemperament");
        expected.setDogImageUrl("someUrl");


    when(dogSvc.getRandomDog()).thenReturn(expected);
    when(userSvc.findUserIdByUsername("leslie")).thenReturn(Optional.of(123456));
  
        try {
            mvc.perform(req)
            .andExpect(MockMvcResultMatchers.view().name("imagepreview"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("image"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogName"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogHeight"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("dogWeight")) 
            .andExpect(MockMvcResultMatchers.model().attributeExists("bredPurpose"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("breedGroup"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("lifespan"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("temperament"));                        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    verify(userSvc).findUserIdByUsername("leslie");
    verify(dogSvc).insertRandomSearchHistory(any(), any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void testSearchHistory() {
        RequestBuilder req = MockMvcRequestBuilders
                            .get("/history")
                            .sessionAttr("username", "leslie");

    when(userSvc.findUserIdByUsername("leslie")).thenReturn(Optional.of(123456));
    when(dogSvc.getLatestFiveHistory(123456)).thenReturn(anyList());

        try {
        mvc.perform(req)
        .andExpect(MockMvcResultMatchers.view().name("userhistory"))
        .andExpect(MockMvcResultMatchers.model().attributeExists("dogs"));        
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
}
