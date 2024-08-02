package org.example.auth.service;

import org.example.auth.dto.UserRolesDTO;

/**
 * Класс предоставляет методы для работы с ролями
 */
public interface RoleService {

    /**
     * Устанавливает пользователю заданный набор ролей
     * @param userRolesDTO запрос
     * @return пользователь с его ролями
     */
    UserRolesDTO setUserRoles(UserRolesDTO userRolesDTO);

}
