package com.armandosalazar.spring.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exception for not found (404)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception {
    public ApiNotFound(String message) {
        super(message);
    }
}
