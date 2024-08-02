package org.example.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.auth.dto.UserRolesDTO;
import org.example.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "api для взаимодействия с ролями пользователей")
@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Установить пользователю заданный набор родей",
            description = "Роли устанавливаются, а не добавляются")
    @ApiResponse(responseCode = "200",
            description = "Пользователь с его ролями",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRolesDTO.class)) }
    )
    @PutMapping("/save")
    @PreAuthorize("hasAuthority(T(org.example.auth.role.RoleEnum).ADMIN.value)")
    public ResponseEntity<UserRolesDTO> setUserRoles(@RequestBody UserRolesDTO userRolesDTO) {
        return ResponseEntity.ok(roleService.setUserRoles(userRolesDTO));
    }

}
