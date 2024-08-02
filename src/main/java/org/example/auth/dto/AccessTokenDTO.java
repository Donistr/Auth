package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Этот класс представляет DTO для access token-а
 */
@Data
@Builder
public class AccessTokenDTO {

    @JsonProperty("access_token")
    @Schema(description = "токен для доступа к сервису", example = "token.asd.zxc")
    private String accessToken;

    @JsonProperty("expiration_date")
    @Schema(description = "дата окончания действия токена", example = "2024-08-01T20:30:21")
    private Date expirationDate;

}
