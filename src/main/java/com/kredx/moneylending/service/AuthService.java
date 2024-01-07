package com.kredx.moneylending.service;

import com.kredx.moneylending.dto.LogInDto;
import com.kredx.moneylending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void login(LogInDto logInDto) {
        // Authenticate the user using Spring Security's basic authentication
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(logInDto.getUsername(), logInDto.getPassword());
        authenticationManager.authenticate(authenticationToken);

        // If authentication is successful, set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public void logout() {
        // Clear the security context to log the user out
        SecurityContextHolder.clearContext();
    }
}

