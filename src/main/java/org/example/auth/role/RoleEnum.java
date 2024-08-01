package org.example.auth.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    USER("USER"),
    CREDIT_USER("CREDIT_USER"),
    OVERDRAFT_USER("OVERDRAFT_USER"),
    DEAL_SUPERUSER("DEAL_SUPERUSER"),
    CONTRACTOR_RUS("CONTRACTOR_RUS"),
    CONTRACTOR_SUPERUSER("CONTRACTOR_SUPERUSER"),
    SUPERUSER("SUPERUSER"),
    ADMIN("ADMIN");

    private final String value;

}
