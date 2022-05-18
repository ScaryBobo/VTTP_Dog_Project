package com.example.DogProject.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.DogProject.DogApiModels.QueryBreedApiModel.SearchBreedResp;
import com.example.DogProject.DogApiModels.RandomDogApiModel.Breed;
import com.example.DogProject.DogApiModels.RandomDogApiModel.GenDog;
import com.example.DogProject.model.Dog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class DogService {
    
   
    //DOG_API_KEY
    @Value("${dog.api.key}")
    private String dogKey;

    public Dog getRandomDog() {
        String url = UriComponentsBuilder.fromUriString("https://api.thedogapi.com/v1/images/search")
                    .queryParam("api_key", dogKey)
                    .toUriString();
        
        
        RequestEntity<Void> req = RequestEntity
                    .get(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Dog dog = new Dog();

        try {        
            List<GenDog> genDogs = Arrays.asList(objectMapper.readValue(resp.getBody(), GenDog[].class));
        
            GenDog genDog = genDogs.get(0);
            dog.setDogImageUrl(genDog.getUrl());

            if(!genDog.getBreeds().isEmpty()){
                Breed breed = genDog.getBreeds().get(0);
                dog.setDogName(breed.getName());
                dog.setDogHeight(breed.getHeight().getMetric());
                dog.setDogWeight(breed.getWeight().getMetric());
                dog.setBredPurpose(breed.getBredFor());
                dog.setBreedGroup(breed.getBreedGroup());
                dog.setLifeSpan(breed.getLifeSpan());
                dog.setTemperament(breed.getTemperament());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading dog random api");
            System.out.println(resp.getBody());
        }

        return dog;
    } 

    public Dog queryDog(String breedName){
        String url = UriComponentsBuilder.fromUriString("https://api.thedogapi.com/v1/breeds/search")
                    .queryParam("q", breedName )
                    .queryParam("api_key", dogKey)
                    .toUriString();
        
        RequestEntity<Void> req = RequestEntity
                    .get(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);


        ObjectMapper objectMapper = new ObjectMapper();
        Dog dog = new Dog();

        try {
            List<SearchBreedResp> searchBreedResps = Arrays.asList(objectMapper.readValue(resp.getBody(), SearchBreedResp[].class));
            SearchBreedResp searchBreedResp = searchBreedResps.get(0);
            dog.setDogName(searchBreedResp.getName());
            dog.setDogHeight(searchBreedResp.getHeight().getMetric());
            dog.setDogWeight(searchBreedResp.getWeight().getMetric());
            dog.setBredPurpose(searchBreedResp.getBredFor());
            dog.setBreedGroup(searchBreedResp.getBreedGroup());
            dog.setLifeSpan(searchBreedResp.getLifeSpan());
            dog.setTemperament(searchBreedResp.getTemperament());

        } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error reading dog random api");
           System.out.println(resp.getBody());

        }

        return dog;
    }

    
    


}   


