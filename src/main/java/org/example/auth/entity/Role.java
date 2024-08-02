package org.example.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.auth.role.RoleEnum;

/**
 * Entity представляющее роль пользователя
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleEnum id;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

}
