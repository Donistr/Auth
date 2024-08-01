package org.example.auth.service.impl;

import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.entity.User;
import org.example.auth.exception.EmailAlreadyUsedException;
import org.example.auth.exception.IllegalTokenException;
import org.example.auth.exception.RoleNotFoundException;
import org.example.auth.exception.UsernameAlreadyUsedException;
import org.example.auth.jwt.JwtUtil;
import org.example.auth.jwt.TokenType;
import org.example.auth.repository.RoleRepository;
import org.example.auth.repository.UserRepository;
import org.example.auth.role.RoleEnum;
import org.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final RoleEnum DEFAULT_ROLE = RoleEnum.USER;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void signUp(SignUpUserDTO signUpUserDTO) {
        if (userRepository.existsByUsername(signUpUserDTO.getUsername())) {
            throw new UsernameAlreadyUsedException("username " + signUpUserDTO.getUsername() + " уже занят");
        }
        if (userRepository.existsByEmail(signUpUserDTO.getEmail())) {
            throw new EmailAlreadyUsedException("email " + signUpUserDTO.getEmail() + " уже занят");
        }

        User user = User.builder()
                .username(signUpUserDTO.getUsername())
                .email(signUpUserDTO.getEmail())
                .password(passwordEncoder.encode(signUpUserDTO.getPassword()))
                .roles(Set.of(roleRepository.findById(DEFAULT_ROLE)
                        .orElseThrow(() -> new RoleNotFoundException("не найдена роль " + DEFAULT_ROLE.getValue()))))
                .build();

        userRepository.saveAndFlush(user);
    }

    @Override
    public RefreshTokenDTO signIn(SignInUserDTO signInUserDTO) {
        String username = signInUserDTO.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, signInUserDTO.getPassword()));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("не найден пользователь с username=" + username));

        String refreshToken = jwtUtil.generateRefreshToken(user);
        return RefreshTokenDTO.builder()
                .refreshToken(refreshToken)
                .expirationDate(jwtUtil.getExpiration(refreshToken))
                .build();
    }

    @Override
    public AccessTokenDTO getAccessToken(GetAccessTokenDTO getAccessTokenDTO) {
        String refreshToken = getAccessTokenDTO.getRefreshToken();

        String username = jwtUtil.extractUsername(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("не найден пользователь с username=" + username));
        if (!jwtUtil.isTokenValid(refreshToken, user, TokenType.REFRESH)) {
            throw new IllegalTokenException("токен не валиден");
        }

        String accessToken = jwtUtil.generateAccessToken(refreshToken, user);
        return AccessTokenDTO.builder()
                .accessToken(accessToken)
                .expirationDate(jwtUtil.getExpiration(accessToken))
                .build();
    }

}
