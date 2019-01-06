package com.nokinobi.repository.exceptions;

public class UserExistException extends RepositoryException {
    public UserExistException() {
        super();
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    public UserExistException(String message) {
        super(message);
    }
}
