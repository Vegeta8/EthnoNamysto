package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.entity.UserEntity;
import com.onlinestore.ethnonamysto.entity.UserRole;
import com.onlinestore.ethnonamysto.util.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 18:00
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private EmailValidatorService emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(new UserEntity(request.getFirstname(), request.getLastname(), request.getEmail(),
                request.getPassword(), UserRole.USER));
    }
}
