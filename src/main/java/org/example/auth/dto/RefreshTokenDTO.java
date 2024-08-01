package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RefreshTokenDTO {

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expiration_date")
    private Date expirationDate;

}
