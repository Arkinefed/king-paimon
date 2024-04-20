package com.arkinefed.kingpaimonrest.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generate(UserDetails userDetails);

    public boolean validate(String token);

    public String getUsername(String token);
}
