package org.example.auth.service;

import org.example.auth.dto.SignInResponseDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void signUp(SignUpUserDTO signUpUserDTO);

    SignInResponseDTO signIn(SignInUserDTO signInUserDTO);

}
