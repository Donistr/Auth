package org.example.auth.controller;

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

@RestController
@RequestMapping("/user-roles")
public class UserRolesController {

    private final UserService userService;

    @Autowired
    public UserRolesController(UserService userService) {
        this.userService = userService;
    }

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
