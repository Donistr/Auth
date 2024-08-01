package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInUserDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}