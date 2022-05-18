
package com.example.DogProject.DogApiModels.QueryBreedApiModel;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bred_for",
    "breed_group",
    "height",
    "id",
    "life_span",
    "name",
    "reference_image_id",
    "temperament",
    "weight"
})
@Generated("jsonschema2pojo")
public class SearchBreedResp {

    @JsonProperty("bred_for")
    private String bredFor;
    @JsonProperty("breed_group")
    private String breedGroup;
    @JsonProperty("height")
    private Height height;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("life_span")
    private String lifeSpan;
    @JsonProperty("name")
    private String name;
    @JsonProperty("reference_image_id")
    private String referenceImageId;
    @JsonProperty("temperament")
    private String temperament;
    @JsonProperty("weight")
    private Weight weight;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bred_for")
    public String getBredFor() {
        return bredFor;
    }

    @JsonProperty("bred_for")
    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    @JsonProperty("breed_group")
    public String getBreedGroup() {
        return breedGroup;
    }

    @JsonProperty("breed_group")
    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    @JsonProperty("height")
    public Height getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Height height) {
        this.height = height;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("life_span")
    public String getLifeSpan() {
        return lifeSpan;
    }

    @JsonProperty("life_span")
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("reference_image_id")
    public String getReferenceImageId() {
        return referenceImageId;
    }

    @JsonProperty("reference_image_id")
    public void setReferenceImageId(String referenceImageId) {
        this.referenceImageId = referenceImageId;
    }

    @JsonProperty("temperament")
    public String getTemperament() {
        return temperament;
    }

    @JsonProperty("temperament")
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @JsonProperty("weight")
    public Weight getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
