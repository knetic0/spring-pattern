package com.technorose.techrose.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    @JsonProperty(value = "success")
    private boolean success;

    @JsonProperty(value = "error_code")
    private String errorCode;

    @JsonProperty(value = "error_description")
    private String errorDescription;

    @JsonProperty(value = "error_exception")
    private String errorException;
}
