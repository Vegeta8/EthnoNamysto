package com.onlinestore.ethnonamysto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Artur May
 * Date 19.08.2022
 * Time 19:10
 */
@Controller
@RequestMapping("/login")
/**
 * > This class is responsible for handling the login process
 */
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping()
    public String loginPage() {
        logger.debug("Logging");

        return "login_register";
    }

}
