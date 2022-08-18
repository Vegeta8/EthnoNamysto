package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.entity.User;
import com.onlinestore.ethnonamysto.entity.UserRole;
import com.onlinestore.ethnonamysto.entity.ConfirmationToken;
import com.onlinestore.ethnonamysto.registration.EmailValidator;
import com.onlinestore.ethnonamysto.registration.RegistrationRequest;
import com.onlinestore.ethnonamysto.util.EmailBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 18:00
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    @Autowired
    private final EmailBuilder emailBuilder;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        String token = userService.signUpUser(new User(request.getFirstname(), request.getLastname(), request.getEmail(),
                request.getPassword(), UserRole.USER));

        String link = "http://localhost:8080/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(),emailBuilder.buildEmail(request.getFirstname(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}
