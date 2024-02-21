package com.technorose.techrose.utils;

import lombok.Getter;

public class Result {
    @Getter
    private boolean Success;

    @Getter
    private String Message;

    public Result(boolean success) {
        Success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        Message = message;
    }

}
