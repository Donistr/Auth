package org.example.auth.controller;

import org.example.auth.dto.UserRolesDTO;
import org.example.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping("/save")
    @PreAuthorize("hasAuthority(T(org.example.auth.role.RoleEnum).ADMIN.value)")
    public ResponseEntity<UserRolesDTO> setUserRoles(@RequestBody UserRolesDTO userRolesDTO) {
        return ResponseEntity.ok(roleService.setUserRoles(userRolesDTO));
    }

}
