package org.example.auth.jwt.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.auth.entity.User;
import org.example.auth.exception.IllegalTokenException;
import org.example.auth.jwt.JwtUtil;
import org.example.auth.jwt.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtilImpl implements JwtUtil {

    private static final String TOKEN_TYPE_KEY = "token_type";

    private static final String ROLES_KEY = "roles";

    @Value("${auth.secret-key}")
    private String secretKey;

    @Value("${auth.access-token-expiration-time-millis}")
    private long accessTokenExpirationTimeMillis;

    @Value("${auth.refresh-token-expiration-time-millis}")
    private long refreshTokenExpirationTimeMillis;

    @Override
    public String generateRefreshToken(User user) {
        try {
            return Jwts.builder()
                    .claim(TOKEN_TYPE_KEY, TokenType.REFRESH.getValue())
                    .claim(ROLES_KEY, user.getRoles())
                    .subject(user.getUsername())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTimeMillis))
                    .signWith(generateKey())
                    .compact();
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public String generateAccessToken(String refreshToken, User user) {
        try {
            if (!isTokenValid(refreshToken, user, TokenType.REFRESH)) {
                throw new IllegalTokenException("токен не валиден");
            }

            Claims claims = extractAllClaims(refreshToken);
            return Jwts.builder()
                    .claim(TOKEN_TYPE_KEY, TokenType.ACCESS.getValue())
                    .claim(ROLES_KEY, claims.get(ROLES_KEY))
                    .subject(claims.getSubject())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationTimeMillis))
                    .signWith(generateKey())
                    .compact();
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType) {
        try {
            return (extractUsername(token).equals(userDetails.getUsername())) && !isTokenExpired(token)
                    && getTokenType(token) == tokenType;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private TokenType getTokenType(String token) {
        try {
            return TokenType.valueOf(extractAllClaims(token).get(TOKEN_TYPE_KEY, String.class));
        } catch (Throwable e) {
            return null;
        }
    }

    private SecretKey generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) throws JwtException,
            IllegalArgumentException {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) throws JwtException, IllegalArgumentException {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) throws JwtException, IllegalArgumentException {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
