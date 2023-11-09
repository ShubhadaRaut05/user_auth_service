package com.shubhada.user_authorization_service.controllers;

import com.shubhada.user_authorization_service.dtos.LoginRequestDTO;
import com.shubhada.user_authorization_service.dtos.UserDTO;
import com.shubhada.user_authorization_service.exceptions.InvalidLoginCredentials;
import com.shubhada.user_authorization_service.exceptions.UserAlreadyExistsException;
import com.shubhada.user_authorization_service.models.User;
import com.shubhada.user_authorization_service.repositories.UserRepository;
import com.shubhada.user_authorization_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    private AuthService authService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService,
                          UserRepository userRepository){
        this.authService=authService;
        this.userRepository = userRepository;
    }
    public User convertUserDTOToUser(UserDTO userDTO){
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getName());
        return user;
    }
    @PostMapping("/usersignup")
    public ResponseEntity<String> userSignUp(@RequestBody  UserDTO userDTO) throws UserAlreadyExistsException {

    authService.userSignUp(convertUserDTOToUser(userDTO));
    return ResponseEntity.ok("Signed Up Successfully: ");

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) throws InvalidLoginCredentials {


      return ResponseEntity.ok(authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));

    }
    @GetMapping("/validate/{token}")
    public boolean validate(@PathVariable("token") String token){

        return authService.validate(token);
    }
}
