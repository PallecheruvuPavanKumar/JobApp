package com.example.JobApp.configuration;

import com.example.JobApp.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
public class SecurityConfig {
    
    @Autowired
    private MyUserDetailsService userRegistrationService;
    
    @Autowired
    JwtFilter jwtFilter;
    
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        if(userRegistrationService != null)
            daoAuthenticationProvider.setUserDetailsService(userRegistrationService);
        else
            System.out.println("User Registration is null");
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return daoAuthenticationProvider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf(customizer -> customizer.disable())
                    .authorizeHttpRequests(request -> request
                            .requestMatchers("/register", "/login", "/error")
                            .permitAll()
                            .anyRequest().authenticated())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling(ex -> ex
                            .authenticationEntryPoint((req, res, ex2) -> {
                                System.out.println("AUTHENTICATION FAILED: " + ex2.getMessage());
                                System.out.println("Request URI: " + req.getRequestURI());
                                System.out.println("Auth Header: " + req.getHeader("Authorization"));
                                res.setContentType("application/json");
                                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                res.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + ex2.getMessage() + "\"}");
                            })
                            .accessDeniedHandler((req, res, ex2) -> {
                                System.out.println("ACCESS DENIED: " + ex2.getMessage());
                                res.setContentType("application/json");
                                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                res.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"" + ex2.getMessage() + "\"}");
                            })
                    );

            return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
    }
    
}
