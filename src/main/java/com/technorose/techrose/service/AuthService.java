package com.technorose.techrose.service;

import com.technorose.techrose.dto.UserModelDto;
import com.technorose.techrose.dto.auth.*;
import com.technorose.techrose.enums.Errors;
import com.technorose.techrose.models.User;
import com.technorose.techrose.repository.UserRepository;
import com.technorose.techrose.utils.JwtUtility;
import com.technorose.techrose.utils.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper mapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = new ModelMapper();
    }

    public UserRegisterResult register(UserRegisterArgs args) {
        UserRegisterResult result = new UserRegisterResult();

        if(args.getUsername().trim().equals("") || args.getUsername().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getFirstName().trim().equals("") || args.getFirstName().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getLastName().trim().equals("") || args.getLastName().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getEmail().trim().equals("") || args.getEmail().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(Validator.email_validator(args.getEmail()).equals(false)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getPhoneNumber().trim().equals("") || args.getPhoneNumber().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getPassword().trim().equals("") || args.getPassword().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(userRepository.findByUsername(args.getUsername()).isPresent()) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.UserExistError.getCode());
            result.getResult().setErrorDescription(Errors.UserExistError.getDescription());

            return result;
        }

        String hashedPassword = passwordEncoder.encode(args.getPassword());

        User user = new User();
        user.setFirstName(args.getFirstName());
        user.setLastName(args.getLastName());
        user.setUsername(args.getUsername());
        user.setPhoneNumber(args.getPhoneNumber());
        user.setEmail(args.getEmail());
        user.setHashedPassword(hashedPassword);

        userRepository.save(user);

        result.getResult().setSuccess(true);

        return result;
    }

    public UserLoginResult userLogin(UserLoginArgs args) {
        UserLoginResult result = new UserLoginResult();

        if(args.getUsername().trim().equals("") || args.getUsername().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        if(args.getPassword().trim().equals("") || args.getPassword().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        User user = userRepository.findByUsername(args.getUsername()).orElseGet(null);

        if (user == null) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.UserNotFoundError.getCode());
            result.getResult().setErrorDescription(Errors.UserNotFoundError.getDescription());

            return result;
        }

        boolean ok = passwordEncoder.matches(args.getPassword(), user.getHashedPassword());

        if(!ok) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.IncorrectPasswordError.getCode());
            result.getResult().setErrorDescription(Errors.IncorrectPasswordError.getDescription());

            return result;
        }

        String token = JwtUtility.generateToken(user);

        UserModelDto userModelDto = mapper.map(user, UserModelDto.class);

        result.setToken(token);
        result.setUser(userModelDto);
        result.getResult().setSuccess(true);

        return result;
    }

    public TokenCheckResult tokenCheck(TokenCheckArgs args) {
        TokenCheckResult result = new TokenCheckResult();

        if(args.getClientTime().equals(null)) {
            result.getResult().setSuccess(false);
            result.getResult().setErrorCode(Errors.InvalidCredentialsError.getCode());
            result.getResult().setErrorDescription(Errors.InvalidCredentialsError.getDescription());

            return result;
        }

        result.getResult().setSuccess(true);
        result.setClientTime(args.getClientTime());
        result.setServerTime(new Date());

        return result;
    }
}
