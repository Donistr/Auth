package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.example.auth.role.RoleEnum;

import java.util.Set;

@Data
@Builder
public class UserRolesDTO {

    @JsonProperty("username")
    @Schema(description = "логин", example = "username")
    private String username;

    @JsonProperty("roles")
    @Schema(description = "роли пользователя")
    private Set<RoleEnum> roles;

}
