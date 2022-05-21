package com.example.DogProject.model;

import java.io.Serializable;

public class Dog implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String dogName;
    private String dogWeight;
    private String dogHeight;
    private String bredPurpose;
    private String breedGroup;
    private String lifeSpan;
    private String temperament;
    private String dogImageUrl;
    
    private String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDogWeight() {
        return dogWeight;
    }
    public void setDogWeight(String dogWeight) {
        this.dogWeight = dogWeight;
    }
  
    public String getDogImageUrl() {
        return dogImageUrl;
    }
    public void setDogImageUrl(String dogImageUrl) {
        this.dogImageUrl = dogImageUrl;
    }
    public String getDogName() {
        return dogName;
    }
    public void setDogName(String dogName) {
        this.dogName = dogName;
    }
    public String getDogHeight() {
        return dogHeight;
    }
    public void setDogHeight(String dogHeight) {
        this.dogHeight = dogHeight;
    }
    public String getBredPurpose() {
        return bredPurpose;
    }
    public void setBredPurpose(String bredPurpose) {
        this.bredPurpose = bredPurpose;
    }
    public String getBreedGroup() {
        return breedGroup;
    }
    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }
    public String getLifeSpan() {
        return lifeSpan;
    }
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
    public String getTemperament() {
        return temperament;
    }
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }


    

}
