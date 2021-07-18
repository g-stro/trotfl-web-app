package com.trotfl.trotflwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Greg Stroud
 *
 * Configuration class for customising Spring's default web security configurations.
 * E.g. Enable HTTP authentication,
 *      Ensure the user is authenticated prior to accessing URLs,
 *      Specify permissions for accessing certain URLs,
 *      etc.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] HTTP_ANT_MATCHERS = {
            "/h2-console/**",
            "/",
            "/account/login",
            "/account/create",
            "/webjars/**"
    };

    private static final String[] WEB_ANT_MATCHERS = {
            "/resources/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.
                ignoring()
                .antMatchers(WEB_ANT_MATCHERS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers(HTTP_ANT_MATCHERS).permitAll();
                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                // Login config
                .formLogin(loginConfigurer -> {
                    loginConfigurer
                            .loginProcessingUrl("/account/login")
                            .loginPage("/account/login").permitAll()
                            .successForwardUrl("/")
                            .defaultSuccessUrl("/", true);
                })
                //Logout config
                .logout(logoutConfigurer -> {
                    logoutConfigurer
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                            .logoutSuccessUrl("/").permitAll();
                })
                .httpBasic()
                .and()
                .csrf().disable();

        // h2 console config
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
