package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAccessTokenDTO {

    @JsonProperty("refresh_token")
    private String refreshToken;

}
