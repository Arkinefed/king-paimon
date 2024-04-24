package com.arkinefed.kingpaimonrest.service;

import com.arkinefed.kingpaimonrest.data.request.LoginRequest;
import com.arkinefed.kingpaimonrest.data.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest data);

    String login(LoginRequest data);
}
