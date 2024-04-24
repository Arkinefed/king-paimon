package com.arkinefed.kingpaimonrest.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.arkinefed.kingpaimonrest.model.AppUser;

public class AppUserUtils {
    public static AppUser getAppUserPerformingRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (AppUser) authentication.getPrincipal();
    }
}
