package com.kiddymap.microservicelocation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EquipmentNotFoundException extends RuntimeException{

    public EquipmentNotFoundException() {
        super();
    }

    public EquipmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipmentNotFoundException(String message) {
        super(message);
    }

    public EquipmentNotFoundException(Throwable cause) {
        super(cause);
    }
}
