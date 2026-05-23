package net.java.Springbt_restapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password("{noop}password123")
                        .roles("ADMIN")
                        .build();
        UserDetails hr =
                User.builder()
                        .username("hr")
                        .password("{noop}hr123")
                        .roles("HR")
                        .build();

        return new InMemoryUserDetailsManager(
                admin,
                hr);
    }
}
