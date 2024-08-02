package org.example.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.auth.dto.UserRolesDTO;
import org.example.auth.exception.UserNotHavePermission;
import org.example.auth.role.RoleEnum;
import org.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "api для получения информации о ролях пользователей")
@RestController
@RequestMapping("/user-roles")
public class UserRolesController {

    private final UserService userService;

    @Autowired
    public UserRolesController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить роли пользователя",
            description = "Админ может получить доступ к ролям любого пользователя, любой другой пользователь - " +
                    "только к своим")
    @ApiResponse(responseCode = "200",
            description = "Пользователь с его ролями",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRolesDTO.class)) }
    )
    @GetMapping("/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserRolesDTO> getUserRoles(@PathVariable String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleEnum.ADMIN.getValue()))) {
            return ResponseEntity.ok(userService.getUserRoles(username));
        }

        if (!authentication.getName().equals(username)) {
            throw new UserNotHavePermission("недостаточно прав для просмотра ролей этого пользователя");
        }

        return ResponseEntity.ok(userService.getUserRoles(username));
    }

}
