package org.example.auth.exception;

/**
 * Исключение сообщает о том, что роль не найдена
 */
public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException(String message) {
        super(message);
    }

}
