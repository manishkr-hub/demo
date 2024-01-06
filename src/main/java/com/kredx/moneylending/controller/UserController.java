package com.kredx.moneylending.controller;

import com.kredx.moneylending.entity.User;
import com.kredx.moneylending.service.AuthService;
import com.kredx.moneylending.service.UserService;
import com.kredx.moneylending.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Contants.API_VERSION + Contants.USER_ENDPOINT)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User signedUpUser = userService.signUp(user);
        return ResponseEntity.ok(signedUpUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authService.login(user);
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok("Logout successful");
    }
}
