package com.gmail.serhiisemiv.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
