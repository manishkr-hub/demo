package com.kredx.moneylending.service;

import com.kredx.moneylending.entity.User;
import com.kredx.moneylending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signUp(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            // Username is already taken
            throw new IllegalArgumentException("Username is already taken");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        userRepository.save(newUser);
        return newUser;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }

}
