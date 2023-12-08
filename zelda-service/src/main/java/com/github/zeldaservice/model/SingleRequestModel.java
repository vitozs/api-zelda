package com.github.zeldaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleRequestModel {
    @JsonProperty("success")
    public boolean success; // private com getter e setter! nunca público. vc já tem o @Data e o @Data provem getter e setter, então põe private!
    @JsonProperty("data")
    public ZeldaGameModel singleData;
}
