package org.example.auth.service.impl;

import org.example.auth.dto.UserRolesDTO;
import org.example.auth.entity.Role;
import org.example.auth.entity.User;
import org.example.auth.exception.RoleNotFoundException;
import org.example.auth.repository.RoleRepository;
import org.example.auth.repository.UserRepository;
import org.example.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Реализует интерфейс {@link RoleService}
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRolesDTO setUserRoles(UserRolesDTO userRolesDTO) {
        User user = userRepository.findByUsernameAndIsActiveTrue(userRolesDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("не найден пользователь с username=" +
                        userRolesDTO.getUsername()));

        Set<Role> newRoles = new HashSet<>();
        userRolesDTO.getRoles().forEach(role -> {
            Role currentRole = roleRepository.findByIdAndIsActiveTrue(role)
                    .orElseThrow(() -> new RoleNotFoundException("не найдена роль " + role.getValue()));
            newRoles.add(currentRole);
        });

        user.setRoles(newRoles);
        userRepository.saveAndFlush(user);

        return UserRolesDTO.builder()
                .username(user.getUsername())
                .roles(user.getRoles().stream()
                        .map(Role::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

}
