package org.example.auth.controller;

import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.dto.SignInUserDTO;
import org.example.auth.dto.SignUpUserDTO;
import org.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpUserDTO signupRequest) {
        userService.signUp(signupRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<RefreshTokenDTO> signIn(@RequestBody SignInUserDTO signInUserDTO) {
        return ResponseEntity.ok(userService.signIn(signInUserDTO));
    }

    @PostMapping("/get-access-token")
    public ResponseEntity<AccessTokenDTO> getAccessToken(@RequestBody GetAccessTokenDTO getAccessTokenDTO) {
        return ResponseEntity.ok(userService.getAccessToken(getAccessTokenDTO));
    }

}
