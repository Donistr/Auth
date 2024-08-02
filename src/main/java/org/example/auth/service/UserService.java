package org.example.auth.service;

import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;
import org.example.auth.dto.UserRolesDTO;

/**
 * Класс предоставляющий методы для работы с пользователями
 */
public interface UserService {

    /**
     * Регистрирует пользователя
     * @param signUpUserDTO запрос
     */
    void signUp(SignUpUserDTO signUpUserDTO);

    /**
     * Аутентифицирует пользователя
     * @param signInUserDTO запрос
     * @return refresh token
     */
    RefreshTokenDTO signIn(SignInUserDTO signInUserDTO);

    /**
     * Получает access token по refresh token-у
     * @param getAccessTokenDTO запрос
     * @return access token
     */
    AccessTokenDTO getAccessToken(GetAccessTokenDTO getAccessTokenDTO);

    /**
     * Получает роли пользователя
     * @param username логин
     * @return пользователь с ролями
     */
    UserRolesDTO getUserRoles(String username);

}
