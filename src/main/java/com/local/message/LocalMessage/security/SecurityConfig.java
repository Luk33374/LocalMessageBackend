package com.local.message.LocalMessage.security;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {
    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()//.antMatchers("/saveUser")
                .permitAll()
                //.anyRequest().hasAnyRole("USER")
        .and()
                .csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/saveUser").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
    }

    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    public UserDetailsService addUser(String userName, String password){

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(userName)
                        .password(encoder.encode(password))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
