package com.custacc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig is used for user authorization control
 *
 * @author Duygu Muslu
 * @version 1.0
 * @since 2020-05-06
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/customers.html", "/accounts.html", "/transactions.html", "/h2-console/**", "/webjars/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}