package com.example.DogProject.service;

import java.io.StringReader;

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
    
    public static final String RANDOM_DOG_SEARCH ="https://api.thedogapi.com/v1/images/search";

    //DOG_API_KEY
    @Value("${dog.api.key}")
    private String dogKey;

    public String getRandomDog() {
        String url = UriComponentsBuilder.fromUriString(RANDOM_DOG_SEARCH)
                    .queryParam("api_key", dogKey)
                    .toUriString();
        
        
        RequestEntity<Void> req = RequestEntity
                    .get(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonArray arr = reader.readArray();
        JsonObject obj = arr.getJsonObject(0);
        String imageUrl = obj.getString("url");
        
        return imageUrl;
    }

    
    


}   


