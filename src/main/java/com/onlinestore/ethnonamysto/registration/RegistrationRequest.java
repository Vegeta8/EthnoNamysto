package com.onlinestore.ethnonamysto.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 18:01
 */

/**
 * It's a data transfer object (DTO) that represents a request to register a new user
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
}
