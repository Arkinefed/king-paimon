package com.arkinefed.kingpaimonrest.service;

import com.arkinefed.kingpaimonrest.model.AppUser;

public interface JwtService {
    public String generate(AppUser user);

    public boolean validate(String token);

    public String getUsername(String token);
}
