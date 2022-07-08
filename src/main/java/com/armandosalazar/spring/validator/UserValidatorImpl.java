package com.armandosalazar.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.armandosalazar.spring.dto.UserRequest;
import com.armandosalazar.spring.serivices.interfaces.UserService;
import com.armandosalazar.spring.utils.exceptions.ApiUnprocessableEntity;

@Component
public class UserValidatorImpl implements UserValidator {

    @Autowired
    UserService userService;

    @Override
    public void validate(UserRequest user) throws ApiUnprocessableEntity {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new ApiUnprocessableEntity("Name is required");
        }
        if (user.getName().length() < 3) {
            throw new ApiUnprocessableEntity("Name must be at least 3 characters");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new ApiUnprocessableEntity("Last name is required");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ApiUnprocessableEntity("Username is required");
        }
        if (userService.findByUsername(user.getUsername().trim()) != null) {
            throw new ApiUnprocessableEntity("Username already exists");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            message("Password is required");
        }
    }

    private void message(String message) throws ApiUnprocessableEntity {
        throw new ApiUnprocessableEntity(message);
    }

}
