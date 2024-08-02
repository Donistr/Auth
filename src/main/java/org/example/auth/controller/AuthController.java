package org.example.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.auth.dto.AccessTokenDTO;
import org.example.auth.dto.GetAccessTokenDTO;
import org.example.auth.dto.RefreshTokenDTO;
import org.example.auth.dto.ResponseObject;
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

/**
 * REST контроллер для аутентификации
 */
@Tag(name = "api для аутентификации")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Регистрирует пользователя
     * @param signupRequest запрос
     * @return success
     */
    @Operation(summary = "Зарегистрироваться")
    @ApiResponse(responseCode = "200",
            content = { @Content }
    )
    @PutMapping("/signup")
    public ResponseEntity<ResponseObject> signUp(@RequestBody SignUpUserDTO signupRequest) {
        userService.signUp(signupRequest);
        return ResponseEntity.ok(new ResponseObject("success"));
    }

    /**
     * Аутентифицирует пользователя
     * @param signInUserDTO запрос
     * @return refresh token
     */
    @Operation(summary = "Войти")
    @ApiResponse(responseCode = "200",
            description = "refresh token",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RefreshTokenDTO.class)) }
    )
    @PostMapping("/signin")
    public ResponseEntity<RefreshTokenDTO> signIn(@RequestBody SignInUserDTO signInUserDTO) {
        return ResponseEntity.ok(userService.signIn(signInUserDTO));
    }

    /**
     * Получает access token по refresh token-у
     * @param getAccessTokenDTO refresh token
     * @return access token
     */
    @Operation(summary = "Получить access token по refresh token-у")
    @ApiResponse(responseCode = "200",
            description = "access token",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccessTokenDTO.class)) }
    )
    @PostMapping("/get-access-token")
    public ResponseEntity<AccessTokenDTO> getAccessToken(@RequestBody GetAccessTokenDTO getAccessTokenDTO) {
        return ResponseEntity.ok(userService.getAccessToken(getAccessTokenDTO));
    }

}
