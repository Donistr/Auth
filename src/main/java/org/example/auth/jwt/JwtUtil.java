package org.example.auth.jwt;

import org.example.auth.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * Этот класс предназначен для работы с токенами
 */
public interface JwtUtil {

    /**
     * Генерирует refresh token
     * @param user пользователь
     * @return refresh token
     */
    String generateRefreshToken(User user);

    /**
     * Генерирует access token
     * @param refreshToken refresh token
     * @param user пользователь
     * @return access token
     */
    String generateAccessToken(String refreshToken, User user);

    /**
     * Получает логин из токена
     * @param token токен
     * @return логин
     */
    String extractUsername(String token);

    /**
     * Проверяет токен на валидность
     * @param token токен
     * @param userDetails пользователь
     * @param tokenType тип токена
     * @return валиден токен или нет
     */
    boolean isTokenValid(String token, UserDetails userDetails, TokenType tokenType);

    /**
     * Получает время истечения токена
     * @param token токен
     * @return время истечения токена
     */
    Date getExpiration(String token);

}
