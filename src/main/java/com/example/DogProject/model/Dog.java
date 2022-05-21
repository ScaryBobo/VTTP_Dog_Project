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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bredPurpose == null) ? 0 : bredPurpose.hashCode());
        result = prime * result + ((breedGroup == null) ? 0 : breedGroup.hashCode());
        result = prime * result + ((dogHeight == null) ? 0 : dogHeight.hashCode());
        result = prime * result + ((dogImageUrl == null) ? 0 : dogImageUrl.hashCode());
        result = prime * result + ((dogName == null) ? 0 : dogName.hashCode());
        result = prime * result + ((dogWeight == null) ? 0 : dogWeight.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((lifeSpan == null) ? 0 : lifeSpan.hashCode());
        result = prime * result + ((temperament == null) ? 0 : temperament.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dog other = (Dog) obj;
        if (bredPurpose == null) {
            if (other.bredPurpose != null)
                return false;
        } else if (!bredPurpose.equals(other.bredPurpose))
            return false;
        if (breedGroup == null) {
            if (other.breedGroup != null)
                return false;
        } else if (!breedGroup.equals(other.breedGroup))
            return false;
        if (dogHeight == null) {
            if (other.dogHeight != null)
                return false;
        } else if (!dogHeight.equals(other.dogHeight))
            return false;
        if (dogImageUrl == null) {
            if (other.dogImageUrl != null)
                return false;
        } else if (!dogImageUrl.equals(other.dogImageUrl))
            return false;
        if (dogName == null) {
            if (other.dogName != null)
                return false;
        } else if (!dogName.equals(other.dogName))
            return false;
        if (dogWeight == null) {
            if (other.dogWeight != null)
                return false;
        } else if (!dogWeight.equals(other.dogWeight))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (lifeSpan == null) {
            if (other.lifeSpan != null)
                return false;
        } else if (!lifeSpan.equals(other.lifeSpan))
            return false;
        if (temperament == null) {
            if (other.temperament != null)
                return false;
        } else if (!temperament.equals(other.temperament))
            return false;
        return true;
    }

    

    

}
