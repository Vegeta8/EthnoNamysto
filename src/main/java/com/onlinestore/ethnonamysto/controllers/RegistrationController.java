package com.onlinestore.ethnonamysto.controllers;

import com.onlinestore.ethnonamysto.util.RegistrationRequest;
import com.onlinestore.ethnonamysto.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 17:44
 */
@RestController
@RequestMapping(path ="/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

}
