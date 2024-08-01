package org.example.auth.service;

import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;
import org.example.auth.dto.UserRolesDTO;

public interface UserService {

    void signUp(SignUpUserDTO signUpUserDTO);

    RefreshTokenDTO signIn(SignInUserDTO signInUserDTO);

    AccessTokenDTO getAccessToken(GetAccessTokenDTO getAccessTokenDTO);

    UserRolesDTO getUserRoles(String username);

}
