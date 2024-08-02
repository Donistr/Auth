package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Этот класс представляет DTO для получения access token-а
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAccessTokenDTO {

    @JsonProperty("refresh_token")
    @Schema(description = "токен для доступа к сервису", example = "token.asd.zxc")
    private String refreshToken;

}
