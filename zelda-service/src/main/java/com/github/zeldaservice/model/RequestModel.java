package com.github.zeldaservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RequestModel {
    @JsonProperty("success")
    public boolean success; // private com getter e setter! nunca público. vc já tem o @Data e o @Data provem getter e setter, então põe private!
    @JsonProperty("count")
    public Integer count; //
    @JsonProperty("data")
    public List<ZeldaGameModel> data;
}
