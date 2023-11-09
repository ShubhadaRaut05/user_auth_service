package com.shubhada.user_authorization_service.services;

import com.shubhada.user_authorization_service.exceptions.InvalidLoginCredentials;
import com.shubhada.user_authorization_service.exceptions.UserAlreadyExistsException;
import com.shubhada.user_authorization_service.models.Session;
import com.shubhada.user_authorization_service.models.User;
import com.shubhada.user_authorization_service.repositories.SessionRepository;
import com.shubhada.user_authorization_service.repositories.UserRepository;
import com.shubhada.user_authorization_service.services.utils.GenerateAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService implements AuthService{
    private UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository,
                           SessionRepository sessionRepository){
        this.userRepository=userRepository;
        this.sessionRepository = sessionRepository;
    }
    @Override
    public String login(String email, String password) throws InvalidLoginCredentials {
        User userExists= userRepository.findByEmailAndPassword(email,password).
             orElseThrow(() -> new InvalidLoginCredentials("Incorrect user login credentials"));
        String token= GenerateAuthToken.generateRandomToken();
        Session session=new Session();
        session.setUser(userExists);
        session.setToken(token);
        sessionRepository.save(session);

        return token;
    }

    @Override
    public boolean validate(String token) {
        return sessionRepository.existsByToken(token);
    }

    @Override
    public void userSignUp(User user) throws UserAlreadyExistsException {

        Optional<User> userExists=userRepository.findByEmail(user.getEmail());
       if(userExists.isPresent()){
           throw new UserAlreadyExistsException("User with same email already exists");
       }
        userRepository.save(user);

    }
}
