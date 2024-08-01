package org.example.auth.service;

import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;

public interface UserService {

    void signUp(SignUpUserDTO signUpUserDTO);

    RefreshTokenDTO signIn(SignInUserDTO signInUserDTO);

    AccessTokenDTO getAccessToken(GetAccessTokenDTO getAccessTokenDTO);

}
