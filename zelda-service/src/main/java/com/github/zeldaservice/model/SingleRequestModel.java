package com.github.zeldaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleRequestModel {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("data")
    private ZeldaGameModel singleData;
}
