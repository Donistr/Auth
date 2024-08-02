package org.example.auth.exception;

/**
 * Исключение сообщает о том, что почта занята
 */
public class EmailAlreadyUsedException extends BaseException {

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
