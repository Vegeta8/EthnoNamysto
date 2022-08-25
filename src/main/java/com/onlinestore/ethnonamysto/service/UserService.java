package com.onlinestore.ethnonamysto.service;

import com.onlinestore.ethnonamysto.entity.User;
import com.onlinestore.ethnonamysto.entity.ConfirmationToken;
import com.onlinestore.ethnonamysto.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 17:28
 */

/**
 * It implements the UserDetailsService interface and overrides the loadUserByUsername() method
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    /**
     * It creates a new user, saves it to the database, creates a confirmation token, saves the token to the database, and
     * returns the token
     *
     * @param user The user object that is passed in from the frontend.
     * @return A token
     */
    public String signUpUser(User user) {
        boolean userExist = userRepository.findByEmail(user.getEmail())
                .isPresent();
        if (userExist) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO:Send email

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

}
