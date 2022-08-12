package com.onlinestore.ethnonamysto.service;

/**
 * Created by Artur May
 * Date 13.08.2022
 * Time 0:43
 */
public interface EmailSender {
    void send(String to, String email);
}
