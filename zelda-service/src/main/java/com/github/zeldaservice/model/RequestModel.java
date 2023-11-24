package com.github.zeldaservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RequestModel {
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("count")
    public Integer count;
    @JsonProperty("data")
    public List<ZeldaGameModel> data;
}
