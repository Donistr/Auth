package org.example.auth.exception;

/**
 * Исключение сообщает о том, что логин уже занят
 */
public class UsernameAlreadyUsedException extends BaseException {

    public UsernameAlreadyUsedException(String message) {
        super(message);
    }

}
