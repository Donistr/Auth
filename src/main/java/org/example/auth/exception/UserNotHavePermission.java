package org.example.auth.exception;

/**
 * Исключение сообщает о том, что у пользователя не хватает привилегий
 */
public class UserNotHavePermission extends BaseException {

    public UserNotHavePermission(String message) {
        super(message);
    }

}
