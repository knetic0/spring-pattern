package com.technorose.techrose.controller;

import com.technorose.techrose.dto.auth.*;
import com.technorose.techrose.enums.Errors;
import com.technorose.techrose.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/user-register")
    public ResponseEntity<UserRegisterResult> userRegister(@RequestBody UserRegisterArgs args) {
        UserRegisterResult result = new UserRegisterResult();

        try {
            result = authService.register(args);
        }
        catch(Exception ex) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.UnhandledExceptionOccurred.getCode());
            result.getResult().setErrorDescription(Errors.UnhandledExceptionOccurred.getDescription());
            result.getResult().setErrorException(ex.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/user-login")
    public ResponseEntity<UserLoginResult> userLogin(@RequestBody UserLoginArgs args) {
        UserLoginResult result = new UserLoginResult();

        try {
            result = authService.userLogin(args);
        }
        catch(Exception ex) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.UnhandledExceptionOccurred.getCode());
            result.getResult().setErrorDescription(Errors.UnhandledExceptionOccurred.getDescription());
            result.getResult().setErrorException(ex.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/token-check")
    public ResponseEntity<TokenCheckResult> tokenCheck(@RequestBody TokenCheckArgs args) {
        TokenCheckResult result = new TokenCheckResult();

        try {
            result = authService.tokenCheck(args);
        }
        catch(Exception ex) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.UnhandledExceptionOccurred.getCode());
            result.getResult().setErrorDescription(Errors.UnhandledExceptionOccurred.getDescription());
            result.getResult().setErrorException(ex.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
