package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Этот класс представляет DTO для регистрации пользователя
 */
@Data
@Builder
public class SignUpUserDTO {

    @JsonProperty("username")
    @Schema(description = "логин", example = "username")
    private String username;

    @JsonProperty("password")
    @Schema(description = "пароль", example = "password")
    private String password;

    @JsonProperty("email")
    @Schema(description = "почта", example = "email")
    private String email;

}
