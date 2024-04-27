package com.arkinefed.kingpaimonrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkinefed.kingpaimonrest.data.request.LoginRequest;
import com.arkinefed.kingpaimonrest.data.request.RegisterRequest;
import com.arkinefed.kingpaimonrest.exception.UserAlreadyExistsException;
import com.arkinefed.kingpaimonrest.exception.UserNotFound;
import com.arkinefed.kingpaimonrest.misc.CommonVariables;
import com.arkinefed.kingpaimonrest.service.AuthService;

@RestController
@RequestMapping(CommonVariables.API_V1 + "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();

        if (username == null ||
                password == null ||
                email == null ||
                firstName == null ||
                lastName == null) {
            return ResponseEntity.badRequest().body("not all required values were provided");
        }

        if (username.length() > 128 ||
                password.length() > 128 ||
                email.length() > 128 ||
                firstName.length() > 128 ||
                lastName.length() > 128) {
            return ResponseEntity.badRequest().body("some values were too long");
        }

        if (!username.matches("^[a-zA-Z0-9_]*$") ||
                !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$") ||
                !firstName.matches("^[a-zA-Z-]*$") ||
                !lastName.matches("^[a-zA-Z-]*$") ||
                !password.equals(password.replaceAll("\\s+", ""))) {
            return ResponseEntity.badRequest().body("some values were invalid");
        }

        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getUsername() == null ||
                request.getPassword() == null) {
            return ResponseEntity.badRequest().body("not all required values were provided");
        }

        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("invalid username or password");
        }
    }
}
