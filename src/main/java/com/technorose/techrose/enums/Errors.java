package com.technorose.techrose.enums;

public enum Errors {

    UnhandledExceptionOccurred("ERRx0000", "An unhandled error occurred"),
    InvalidCredentialsError("ERRx0001", "Invalid credentials!"),
    IncorrectPasswordError("ERRx0002", "Incorrect password!"),
    UserExistError("ERRx0003", "Registered user!"),
    UserNotFoundError("ERRx0004", "User not found!");

    private final String code;
    private final String description;

    Errors(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
