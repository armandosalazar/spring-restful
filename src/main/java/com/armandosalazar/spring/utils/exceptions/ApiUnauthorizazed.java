package com.armandosalazar.spring.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Exception for unauthorizazed (401)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorizazed extends Exception {
    public ApiUnauthorizazed(String message) {
        super(message);
    }
}
