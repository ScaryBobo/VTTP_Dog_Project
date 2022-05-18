
package com.example.DogProject.DogApiModels.RandomDogApiModel;

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
    "imperial",
    "metric"
})
@Generated("jsonschema2pojo")
public class Weight {

    @JsonProperty("imperial")
    private String imperial;
    @JsonProperty("metric")
    private String metric;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("imperial")
    public String getImperial() {
        return imperial;
    }

    @JsonProperty("imperial")
    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    @JsonProperty("metric")
    public String getMetric() {
        return metric;
    }

    @JsonProperty("metric")
    public void setMetric(String metric) {
        this.metric = metric;
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
