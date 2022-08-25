package com.onlinestore.ethnonamysto.controllers;

import com.onlinestore.ethnonamysto.dto.ItemDto;
import com.onlinestore.ethnonamysto.registration.RegistrationRequest;
import com.onlinestore.ethnonamysto.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 17:44
 */
/**
 * This class is a Spring MVC controller that handles requests to the `/registration` endpoint.
 */
@Controller
@RequestMapping(path ="/registration")
@AllArgsConstructor
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;

    @PostMapping
    @ResponseBody
    public String register(RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
