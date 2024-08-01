package org.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.example.auth.role.RoleEnum;

import java.util.Set;

@Data
@Builder
public class UserRolesDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("roles")
    private Set<RoleEnum> roles;

}
