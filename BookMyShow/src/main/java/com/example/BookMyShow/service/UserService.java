package com.example.BookMyShow.service;

import com.example.BookMyShow.exception.InvalidUserException;
import com.example.BookMyShow.models.User;
import com.example.BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User signUp(String name, String emailId, String password) throws InvalidUserException {
        Optional<User> optionalUser = userRepository.findByEmail(emailId);

        if (optionalUser.isPresent()) {
            // Go to Login workflow
            login(emailId, password);
        }
        User user = new User();
        user.setName(name);
        user.setEmail(emailId);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword((bCryptPasswordEncoder.encode(password)));
        return userRepository.save(user);
    }

    public boolean login(String emailId, String password) throws InvalidUserException {
        Optional<User> optionalUser = userRepository.findByEmail(emailId);
        if(optionalUser.isEmpty()) {
            throw new InvalidUserException("User Does not exist");
        }

        String passwordInDB = optionalUser.get().getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.matches(password, passwordInDB);
    }
}
