package net.java.Springbt_restapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    //Role specific access
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/uploads/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/employees", "/api/employees/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/departments", "/api/departments/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.POST, "/api/departments").hasAnyRole("MANAGER", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.PATCH, "/api/departments/**").hasAnyRole("MANAGER", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}

