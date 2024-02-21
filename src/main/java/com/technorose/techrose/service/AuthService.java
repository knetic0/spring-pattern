package com.technorose.techrose.service;

import com.technorose.techrose.dto.LoginDto;
import com.technorose.techrose.dto.RegisterDto;
import com.technorose.techrose.dto.TokenCheckDto;
import com.technorose.techrose.enums.Errors;
import com.technorose.techrose.models.User;
import com.technorose.techrose.repository.UserRepository;
import com.technorose.techrose.utils.DataResult;
import com.technorose.techrose.utils.JwtUtility;
import com.technorose.techrose.utils.Result;
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

    public Result userRegister(RegisterDto args) {
        if(args.getUsername().trim().equals("") || args.getUsername().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getFirstName().trim().equals("") || args.getFirstName().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getLastName().trim().equals("") || args.getLastName().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getEmail().trim().equals("") || args.getEmail().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(Validator.email_validator(args.getEmail()).equals(false)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getPhoneNumber().trim().equals("") || args.getPhoneNumber().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getPassword().trim().equals("") || args.getPassword().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        if(userRepository.findByUsername(args.getUsername()).isPresent()) {
            return new Result(false, Errors.UserNotFoundError.getDescription());
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

        return new Result(true);
    }

    public DataResult<User> userLogin(LoginDto args) {
        if(args.getUsername().trim().equals("") || args.getUsername().equals(null)) {
            return new DataResult<User>(new User(), false, Errors.InvalidCredentialsError.getDescription());
        }

        if(args.getPassword().trim().equals("") || args.getPassword().equals(null)) {
            return new DataResult<User>(new User(), false, Errors.InvalidCredentialsError.getDescription());
        }

        User user = userRepository.findByUsername(args.getUsername()).orElseGet(null);

        if (user == null) {
            return new DataResult<User>(new User(), false, Errors.UserNotFoundError.getDescription());
        }

        boolean ok = passwordEncoder.matches(args.getPassword(), user.getHashedPassword());

        if(!ok) {
            return new DataResult<User>(new User(), false, Errors.IncorrectPasswordError.getDescription());
        }

        String token = JwtUtility.generateToken(user);

        return new DataResult<User>(user, true);
    }

    public Result tokenCheck(TokenCheckDto args) {
        if(args.getClientTime().equals(null)) {
            return new Result(false, Errors.InvalidCredentialsError.getDescription());
        }

        return new Result(true);
    }
}
