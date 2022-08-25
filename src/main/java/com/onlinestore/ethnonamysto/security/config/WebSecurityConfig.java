package com.onlinestore.ethnonamysto.security.config;

import com.onlinestore.ethnonamysto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Artur May
 * Date 12.08.2022
 * Time 18:06
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity

/**
 * > This class is used to configure the security of the application
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //TODO upgrade the Deprecated WebSecurityConfigurerAdapter
    /**
     * If the user is not authenticated, then redirect to the login page. Otherwise, allow access to the requested
     * resource.
     *
     * @param http This is the main object that contains all the configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/", "/registration/**", "/assets/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true);
    }

    /**
     * "This function is called by the Spring Security framework to configure the authentication manager.
     * The authentication manager is responsible for authenticating users.
     * The authentication manager is configured to use the DAO authentication provider."
     *
     * @param auth This is the AuthenticationManagerBuilder object that is used to create an AuthenticationManager object.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * This function creates a DaoAuthenticationProvider object, sets the password encoder to be the bCryptPasswordEncoder
     * object, and sets the user details service to be the userService object.
     *
     * @return A DaoAuthenticationProvider object.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}