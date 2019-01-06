package com.nokinobi.repository.exceptions;

public class RepositoryException extends Exception {

    public RepositoryException() {
        super();
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message) {
        super(message);
    }
}
