package com.onlinestore.ethnonamysto.controllers;

import com.onlinestore.ethnonamysto.registration.RegistrationRequest;
import com.onlinestore.ethnonamysto.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
