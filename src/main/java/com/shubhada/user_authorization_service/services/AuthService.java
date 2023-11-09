package com.shubhada.user_authorization_service.services;

import com.shubhada.user_authorization_service.exceptions.InvalidLoginCredentials;
import com.shubhada.user_authorization_service.exceptions.UserAlreadyExistsException;
import com.shubhada.user_authorization_service.models.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public String login(String email,String password) throws InvalidLoginCredentials;
    public boolean validate(String token);
    public void userSignUp(User user) throws UserAlreadyExistsException;

}
