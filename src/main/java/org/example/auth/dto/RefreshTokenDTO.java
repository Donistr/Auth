package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Этот класс представляет DTO для refresh token-а
 */
@Data
@Builder
public class RefreshTokenDTO {

    @JsonProperty("refresh_token")
    @Schema(description = "токен для получения access токенов", example = "token.asd.zxc")
    private String refreshToken;

    @JsonProperty("expiration_date")
    @Schema(description = "дата окончания действия токена", example = "2024-08-01T20:30:21")
    private Date expirationDate;

}
