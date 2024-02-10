package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technorose.techrose.dto.Result;
import com.technorose.techrose.dto.UserModelDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResult {

    public UserLoginResult() {
        this.result = new Result();
    }

    @JsonProperty(value = "result")
    private Result result;

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "user")
    private UserModelDto user;
}
