package com.kredx.moneylending.service;

import com.kredx.moneylending.entity.Role;
import com.kredx.moneylending.entity.User;
import com.kredx.moneylending.repository.RoleRepository;
import com.kredx.moneylending.repository.UserRepository;
import com.kredx.moneylending.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signUp(SignUpDto signUpDto) {
        Optional<User> existingUser = userRepository.findByUsername(signUpDto.getUsername());
        if (existingUser.isPresent()) {
            // Username is already taken
            throw new IllegalArgumentException("Username is already taken");
        }

        User newUser = signUpDto.toUser();
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ADMIN").get();
        newUser.setRoles(Collections.singleton(roles));

        return userRepository.save(newUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }

}
