package org.example.auth.repository;

import org.example.auth.entity.Role;
import org.example.auth.role.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, RoleEnum> {
}
