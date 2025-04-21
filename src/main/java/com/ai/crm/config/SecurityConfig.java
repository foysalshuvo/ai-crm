package com.ai.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF protection if not needed for Swagger UI
                .authorizeRequests()
                // Permit Swagger UI and related resources
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**")
                .permitAll()
                // Secure other APIs
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin().permitAll();  // Allow form login for other parts (or use httpBasic)
    }
}

