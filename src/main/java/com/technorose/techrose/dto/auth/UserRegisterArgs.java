package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterArgs {

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @JsonProperty(value = "password")
    private String password;
}
