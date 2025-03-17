package fr.xwj860725.gestiontaches.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Enables CORS support and specifies a customized CORS configuration source
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
            // Allow login/registration api and OAuth2 authentication-free access
            .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
            .requestMatchers("/oauth2/**").permitAll()

            // All URLs on the backend starting with "/tasks/" can be accessed without logging in
            // 8080/tasks, :8080/tasks/search, 8080/tasks/{id}, etc. will not be blocked by Spring Security, so, add, search, delete can be done successfully
            .requestMatchers("/tasks/**").permitAll()
            
                // All other requests must be authenticated
                .anyRequest().authenticated()
            )
            // Configure OAuth2 login (if using it)
            .oauth2Login(oauth2 -> oauth2
            // After a successful login, the backend redirects the user to the 5175/tasks management page on the frontend
            .defaultSuccessUrl("http://localhost:5175/tasks", true)
            )
            // Configure the logout function to redirect to the login page after successful logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login").permitAll()
            )
            // Turn off CSRF (for development only)
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow cross-domain requests from frontend addresses
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5175"));
        // Allows all http methods that may be used by the frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow all request headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Allow carrying credentials (e.g. cookies)）
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Effective on all paths
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
