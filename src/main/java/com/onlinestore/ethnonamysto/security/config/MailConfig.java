package com.onlinestore.ethnonamysto.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by Artur May
 * Date 14.08.2022
 * Time 14:32
 */
/**
 * It's a Spring configuration class that configures a JavaMailSender bean.
 */
@Configuration
public class MailConfig {

    @Bean
    // A bean that is used to send emails.
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setProtocol("smtp");
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("etno.namysto@gmail.com");
        javaMailSender.setPassword("juoehfqkclvrpajj");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }

}
