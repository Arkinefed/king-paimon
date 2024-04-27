package com.arkinefed.kingpaimonrest.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkinefed.kingpaimonrest.data.request.LoginRequest;
import com.arkinefed.kingpaimonrest.data.request.RegisterRequest;
import com.arkinefed.kingpaimonrest.exception.EmailAlreadyTakenException;
import com.arkinefed.kingpaimonrest.exception.UserAlreadyExistsException;
import com.arkinefed.kingpaimonrest.exception.UserNotFound;
import com.arkinefed.kingpaimonrest.model.AppUser;
import com.arkinefed.kingpaimonrest.repository.AppUserRepository;
import com.arkinefed.kingpaimonrest.service.AuthService;
import com.arkinefed.kingpaimonrest.service.JwtService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest data) throws UserAlreadyExistsException {
        if (appUserRepository.existsByEmail(data.getEmail().toLowerCase())) {
            throw new EmailAlreadyTakenException(data.getEmail());
        }

        if (appUserRepository.existsByUsername(data.getUsername().toLowerCase())) {
            Optional<AppUser> user = appUserRepository.findByUsername(data.getUsername().toLowerCase());

            if (user.isPresent() && data.getUsername().toLowerCase().equals(user.get().getUsername())) {
                throw new UserAlreadyExistsException(data.getUsername());
            }
        }

        AppUser user = AppUser.builder()
                .username(data.getUsername().toLowerCase())
                .password(passwordEncoder.encode(data.getPassword()))
                .email(data.getEmail().toLowerCase())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .role(AppUser.Role.user)
                .whenRegistered(LocalDateTime.now())
                .build();

        appUserRepository.save(user);

        return jwtService.generate(user);
    }

    @Override
    public String login(LoginRequest data) throws UserNotFound, IllegalArgumentException, AuthenticationException {
        if (!appUserRepository.existsByUsername(data.getUsername().toLowerCase())) {
            throw new UserNotFound(data.getUsername());
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getUsername().toLowerCase(), data.getPassword()));

        AppUser user = appUserRepository.findByUsername(data.getUsername().toLowerCase())
                .orElseThrow(() -> new UserNotFound(data.getUsername()));

        return jwtService.generate(user);
    }
}
