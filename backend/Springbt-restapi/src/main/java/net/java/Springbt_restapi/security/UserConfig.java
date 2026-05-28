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

        UserDetails employee =
                User.builder()
                        .username("employee")
                        .password("{noop}employee123")
                        .roles("EMPLOYEE")
                        .build();
        UserDetails manager =
                User.builder()
                        .username("manager")
                        .password("{noop}manager123")
                        .roles("MANAGER")
                        .build();
        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password("{noop}admin123")
                        .roles("ADMIN")
                        .build();
        UserDetails hr =
                User.builder()
                        .username("hr")
                        .password("{noop}hr123")
                        .roles("HR")
                        .build();

        return new InMemoryUserDetailsManager(
                employee,
                manager,
                admin,
                hr);
    }
}
