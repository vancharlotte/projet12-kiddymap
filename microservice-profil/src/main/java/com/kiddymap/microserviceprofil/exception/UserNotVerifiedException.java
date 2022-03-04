package com.kiddymap.microserviceprofil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserNotVerifiedException extends RuntimeException{

    public UserNotVerifiedException() {
        super();
    }

    public UserNotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotVerifiedException(String message) {
        super(message);
    }

    public UserNotVerifiedException(Throwable cause) {
        super(cause);
    }
}
