package net.java.Springbt_restapi.security;

import net.java.Springbt_restapi.service.impl.DatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
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
    public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception{
        http.authenticationProvider(authenticationProvider);
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/uploads/**").hasAnyRole("SUPER_ADMIN","ADMIN","MANAGER","HR")

                                .requestMatchers(HttpMethod.POST, "/api/users").hasAnyRole("SUPER_ADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/users").hasAnyRole("SUPER_ADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("SUPER_ADMIN","ADMIN","HR","MANAGER")
                                .requestMatchers(HttpMethod.PATCH,"/api/users/**").hasAnyRole("SUPER_ADMIN","ADMIN","HR","MANAGER","EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasAnyRole("SUPER_ADMIN","ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole( "MANAGER", "ADMIN", "HR", "SUPER_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("SUPER_ADMIN","ADMIN","HR","MANAGER","EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("ADMIN", "HR", "SUPER_ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasAnyRole("SUPER_ADMIN","ADMIN","HR","MANAGER","EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyRole("ADMIN", "SUPER_ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/departments", "/api/departments/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "MANAGER", "HR", "EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/departments").hasAnyRole("SUPER_ADMIN", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.PATCH, "/api/departments/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "HR")
                                .requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                                .anyRequest().authenticated())
                                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(DatabaseUserDetailsService userDetailsService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}

