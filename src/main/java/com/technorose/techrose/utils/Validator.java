package com.technorose.techrose.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static Boolean email_validator(String email) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        Matcher emailMatcher = emailPattern.matcher(email);

        return emailMatcher.find();
    }
}
