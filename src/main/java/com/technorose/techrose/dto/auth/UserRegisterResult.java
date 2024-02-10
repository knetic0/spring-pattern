package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technorose.techrose.dto.Result;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterResult {

    public UserRegisterResult() {
        this.result = new Result();
    }

    @JsonProperty(value = "result")
    private Result result;
}
