package org.example.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Этот енам представляет типы токенов
 */
@AllArgsConstructor
@Getter
public enum TokenType {

    ACCESS("ACCESS"),
    REFRESH("REFRESH");

    private final String value;

}
