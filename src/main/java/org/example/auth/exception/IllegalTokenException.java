package org.example.auth.exception;

/**
 * Исключение сообщает о том, что токен невалиден
 */
public class IllegalTokenException extends BaseException {

    public IllegalTokenException(String message) {
        super(message);
    }

}
