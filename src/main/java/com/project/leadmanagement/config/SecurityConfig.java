package com.project.leadmanagement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security
        http
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll()  // Allow access to public API endpoints
                .anyRequest().authenticated()  // Require authentication for all other endpoints
                .and()
                // Disable CSRF protection if not required (common in REST APIs)
                .csrf().disable()
                // Disable Basic Authentication if not required
                .httpBasic().disable()
                .formLogin().disable();  // Optional: Disable form login if not required

        return http.build();
    }
}

