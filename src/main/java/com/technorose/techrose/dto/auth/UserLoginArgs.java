package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginArgs {

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;
}
