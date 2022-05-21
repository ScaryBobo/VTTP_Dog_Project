package com.example.DogProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.DogProject.model.Dog;
import com.example.DogProject.service.DogService;

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
    @InjectMocks
    private DogService subject;

    @Mock
    private RestTemplate restTemplate = mock(RestTemplate.class);
   
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
}
