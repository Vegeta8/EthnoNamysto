package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.entity.ConfirmationToken;
import com.onlinestore.ethnonamysto.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 23:53
 */
/**
 * It's a service class that
 * provides methods to save, get, and update a confirmation token
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
