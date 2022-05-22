package com.example.DogProject;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.DogProject.model.Dog;
import com.example.DogProject.model.User;
import com.example.DogProject.repository.GeneratedImageRepository;
import com.example.DogProject.service.DogService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DogServiceTest {

    private String dogApiJsonResponse = "[{\"breeds\":[{\"weight\":{\"imperial\":\"17-23\",\"metric\":\"8-10\"},\"height\":{\"imperial\":\"13.5-16.5\",\"metric\":\"34-42\"},\"id\":222,\"name\":\"Shiba Inu\",\"bred_for\":\"someReasons\",\"breed_group\":\"Non-Sporting\",\"life_span\":\"12-16years\",\"temperament\":\"someTemperament\",\"reference_image_id\":\"Zn3IjPX3f\"}],\"id\":\"Zn3IjPX3f\",\"url\":\"someUrl\",\"width\":1080,\"height\":1080}]";
    private String queryDogApiJsonResponse = "[{\"bred_for\":\"someReasons\",\"breed_group\":\"Non-Sporting\",\"height\":{\"imperial\":\"13.5-16.5\",\"metric\":\"34-42\"\r\n},\"id\":222,\"life_span\":\"12-16years\",\r\n\"name\":\"Shiba Inu\",\"reference_image_id\":\"Zn3IjPX3f\",\"temperament\":\"someTemperament\",\"weight\":{\"imperial\":\"17-23\",\"metric\":\"8-10\"\r\n}\r\n}\r\n]";
    @InjectMocks
    private DogService subject;

    @Mock
    private RestTemplate restTemplate = mock(RestTemplate.class);

    @Mock
    private GeneratedImageRepository genImgRepo = mock(GeneratedImageRepository.class);
   
    @Test
    void testGetRandomDog(){
        // setup
        Dog expected = new Dog();
        expected.setDogName("Shiba Inu");
        expected.setDogWeight("8-10");
        expected.setDogHeight("34-42");
        expected.setBredPurpose("someReasons");
        expected.setBreedGroup("Non-Sporting");
        expected.setLifeSpan("12-16years");
        expected.setTemperament("someTemperament");
        expected.setDogImageUrl("someUrl");

        when(restTemplate.exchange(any(), any(Class.class)))
            .thenReturn(ResponseEntity.of(Optional.of(dogApiJsonResponse)));


        // test
        Dog actual = subject.getRandomDog();

        // assertion
        // assertEquals(expected.getDogName(), actual.getDogName());
        // assertEquals(expected.getBredPurpose(), actual.getBredPurpose());
        
        assertTrue(expected.equals(actual));
        
    }

    @Test
    void testGetQueryDog() {
        //setup

     

        Dog expected = new Dog();
        expected.setDogName("Shiba Inu");
        expected.setDogWeight("8-10");
        expected.setDogHeight("34-42");
        expected.setBredPurpose("someReasons");
        expected.setBreedGroup("Non-Sporting");
        expected.setLifeSpan("12-16years");
        expected.setTemperament("someTemperament");

        when(restTemplate.exchange(any(), any(Class.class)))
        .thenReturn(ResponseEntity.of(Optional.of(queryDogApiJsonResponse))); 

        Dog actual = subject.queryDog("Shiba");

        // assertEquals(expected.getTemperament(), actual.getTemperament());


        assertTrue(expected.equals(actual));
    }

    @Test
    void insertRandomSearchHistoryTest(){
        Integer userId = 999999;
        String dogName = "dogtest";
        String dogHeight = "1";
        String dogWeight = "1";
        String bredPurpose = "testPurpose";
        String breedGroup = "testGroup";
        String lifespan = "1";
        String temperament = "testTemperament";
        String imageUrl = "someUrl";

        subject.insertRandomSearchHistory(userId, dogName, dogHeight, dogWeight, bredPurpose, 
                                        breedGroup, lifespan, temperament, imageUrl);
        verify(genImgRepo).insertNewRandomImgRecord(userId, dogName, dogHeight, dogWeight, bredPurpose, 
                                        breedGroup, lifespan, temperament, imageUrl);
    }

    @Test
    void insertQuerySearchHistoryTest(){
        Integer userId = 999999;
        String dogName = "dogtest";
        String dogHeight = "1";
        String dogWeight = "1";
        String bredPurpose = "testPurpose";
        String breedGroup = "testGroup";
        String lifespan = "1";
        String temperament = "testTemperament";
       

        subject.insertQuerySearchHistory(userId, dogName, dogHeight, dogWeight, bredPurpose, 
                                        breedGroup, lifespan, temperament);
        verify(genImgRepo).insertNewQueryRecord(userId, dogName, dogHeight, dogWeight, bredPurpose, 
                                        breedGroup, lifespan, temperament);
    }

    @Test
    void getSearchHistoryTest(){
        User user = new User();
        user.setUserId(999999);
        List<Dog> dogTestList = new ArrayList<>();
        
        Dog dog = new Dog();
        dog.setDogName("dogtest");
        dog.setDogHeight("1");
        dog.setDogWeight("1");
        dog.setBredPurpose("testPurpose");
        dog.setBreedGroup("testGroup");
        dog.setLifeSpan("1");
        dog.setTemperament("testTemperament");

        dogTestList.add(dog);

        when(genImgRepo.getLatestFiveSearchByUserId(anyInt())).thenReturn(dogTestList);
        
        List<Dog> actualList = subject.getLatestFiveHistory(user.getUserId());

        Assertions.assertEquals(dogTestList, actualList);

    }



}
