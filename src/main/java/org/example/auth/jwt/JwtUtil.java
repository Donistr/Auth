package org.example.auth.jwt;

import org.example.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtUtil {

    String generateRefreshToken(User user);

    String generateAccessToken(String refreshToken, User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType);

    Date getExpiration(String token);

}
