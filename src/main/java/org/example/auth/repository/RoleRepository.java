package org.example.auth.repository;

import org.example.auth.entity.Role;
import org.example.auth.role.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, RoleEnum> {

    Optional<Role> findByIdAndIsActiveTrue(RoleEnum id);

}
