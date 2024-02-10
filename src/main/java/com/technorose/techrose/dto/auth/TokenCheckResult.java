package com.technorose.techrose.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.technorose.techrose.dto.Result;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenCheckResult {

    public TokenCheckResult() {
        this.result = new Result();
    }

    @JsonProperty(value = "client_time")
    private Date clientTime;

    @JsonProperty(value = "server_time")
    private Date serverTime;

    @JsonProperty(value = "result")
    private Result result;
}
