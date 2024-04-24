package com.arkinefed.kingpaimonrest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arkinefed.kingpaimonrest.repository.AppUserRepository;
import com.arkinefed.kingpaimonrest.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}