package com.kiddymap.microservicelocation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException{

    public LocationNotFoundException() {
        super();
    }

    public LocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFoundException(String message) {
        super(message);
    }

    public LocationNotFoundException(Throwable cause) {
        super(cause);
    }
}
