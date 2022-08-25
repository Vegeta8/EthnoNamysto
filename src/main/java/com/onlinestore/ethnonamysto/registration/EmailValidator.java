package com.onlinestore.ethnonamysto.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 21:14
 */
/**
 * This class is a service that validates emails.
 */
@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO:Regex to validate email
        return true;
    }
}
