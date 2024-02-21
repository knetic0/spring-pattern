package com.technorose.techrose.utils;

import lombok.Getter;

public class DataResult<T> extends Result {

    @Getter
    private T Data;

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        Data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        Data = data;
    }
}
