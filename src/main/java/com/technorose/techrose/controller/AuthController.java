package com.technorose.techrose.controller;

import com.technorose.techrose.dto.LoginDto;
import com.technorose.techrose.dto.RegisterDto;
import com.technorose.techrose.dto.TokenCheckDto;
import com.technorose.techrose.enums.Errors;
import com.technorose.techrose.models.User;
import com.technorose.techrose.service.AuthService;
import com.technorose.techrose.utils.DataResult;
import com.technorose.techrose.utils.Result;
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
    public ResponseEntity<Result> userRegister(@RequestBody RegisterDto args) {
        try {
            return new ResponseEntity<>(authService.userRegister(args), HttpStatus.CREATED);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(new Result(false, Errors.UnhandledExceptionOccurred.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/user-login")
    public ResponseEntity<DataResult<User>> userLogin(@RequestBody LoginDto args) {
        try {
            return new ResponseEntity<>(authService.userLogin(args), HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(new DataResult<>(null,false, Errors.UnhandledExceptionOccurred.getDescription()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/token-check")
    public ResponseEntity<Result> tokenCheck(@RequestBody TokenCheckDto args) {
        try {
            return new ResponseEntity<>(authService.tokenCheck(args), HttpStatus.OK);
        }
        catch(Exception ex) {
            return new ResponseEntity<>(new Result(false), HttpStatus.UNAUTHORIZED);
        }
    }
}
