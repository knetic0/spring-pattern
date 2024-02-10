package com.technorose.techrose.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelDto {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "user_name")
    private String username;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;
}
