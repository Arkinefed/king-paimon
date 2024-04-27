package com.arkinefed.kingpaimonrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arkinefed.kingpaimonrest.data.request.LoginRequest;
import com.arkinefed.kingpaimonrest.data.request.RegisterRequest;
import com.arkinefed.kingpaimonrest.exception.UserAlreadyExistsException;
import com.arkinefed.kingpaimonrest.exception.UserNotFound;
import com.arkinefed.kingpaimonrest.misc.CommonVariables;
import com.arkinefed.kingpaimonrest.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(CommonVariables.API_V1 + "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (request.getUsername() == null ||
                request.getPassword() == null ||
                request.getEmail() == null ||
                request.getFirstName() == null ||
                request.getLastName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not all required values were provided");
        }

        if (request.getUsername().length() > 128 ||
                request.getPassword().length() > 128 ||
                request.getEmail().length() > 128 ||
                request.getFirstName().length() > 128 ||
                request.getLastName().length() > 128) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some values were too long");
        }

        if (!request.getUsername().matches("^[a-zA-Z0-9_]*$") ||
                !request.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$") ||
                !request.getFirstName().matches("^[a-zA-Z-]*$") ||
                !request.getLastName().matches("^[a-zA-Z-]*$") ||
                !request.getPassword().equals(request.getPassword().replaceAll("\\s+", ""))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some values were invalid");
        }

        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getUsername() == null ||
                request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not all required values were provided");
        }

        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
        }
    }
}
