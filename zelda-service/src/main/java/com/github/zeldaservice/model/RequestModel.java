package com.github.zeldaservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RequestModel {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("data")
    private List<ZeldaGameModel> data;
}
