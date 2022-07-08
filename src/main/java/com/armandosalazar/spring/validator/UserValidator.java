package com.armandosalazar.spring.validator;

import org.springframework.stereotype.Service;

import com.armandosalazar.spring.dto.UserRequest;
import com.armandosalazar.spring.utils.exceptions.ApiUnprocessableEntity;

// In this class, we will validate the user request.
@Service
public interface UserValidator {

    void validate(UserRequest user) throws ApiUnprocessableEntity;
}
