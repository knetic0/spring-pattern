package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenCheckArgs {

    @JsonProperty(value = "client_time")
    private Date clientTime;
}
