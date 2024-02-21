package com.technorose.techrose.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;
}
