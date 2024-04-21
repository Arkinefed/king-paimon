package com.arkinefed.kingpaimonrest.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.arkinefed.kingpaimonrest.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${com.arkinefed.kingpaimonrest.jwt.secret}")
    private String secret;
    @Value("${com.arkinefed.kingpaimonrest.jwt.expiration}")
    private Long expiration;

    @Override
    public String generate(UserDetails userDetails) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);

        Date now = new Date(System.currentTimeMillis());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration * 1000))
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e);
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e);
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token: " + e);
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e);
        }

        return false;
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
