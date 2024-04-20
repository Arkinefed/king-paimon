package com.arkinefed.kingpaimonrest.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arkinefed.kingpaimonrest.service.AppUserService;
import com.arkinefed.kingpaimonrest.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AppUserService appUserService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (auth == null || !StringUtils.startsWithIgnoreCase(auth, "Bearer ")) {
            filterChain.doFilter(request, response);

            return;
        }

        String jwt = auth.replace("Bearer ", "");

        if (jwtService.validate(jwt)) {
            String username = jwtService.getUsername(jwt);

            if ((username != null) && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    UserDetails userDetails = appUserService.userDetailsService().loadUserByUsername(username);

                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);

                    SecurityContextHolder.setContext(context);
                } catch (Exception e) {
                    filterChain.doFilter(request, response);

                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
