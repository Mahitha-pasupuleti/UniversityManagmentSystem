package com.userprofile.Service;

import com.userprofile.CustomExceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private static final String SECRET_KEY = "6e2ff9c92f41a22153133085b79a10f284ddaa364078ee1872b6d98babe9b7f3";

    public Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract data from a given JWT Token

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            throw new UnauthorizedException("Invalid token");
        }
    }

    public <T> T extractClaim(String token, String claimName, Class<T> requiredType) {
        return extractAllClaims(token).get(claimName, requiredType);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims.SUBJECT, String.class);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims.EXPIRATION, Date.class);
    }

    public String extractRole(String token) {
        return extractClaim(token, "role", String.class);
    }

    // Token Validation
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
