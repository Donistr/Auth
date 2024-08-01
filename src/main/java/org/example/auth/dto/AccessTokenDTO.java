package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiration_date")
    private Date expirationDate;

}
