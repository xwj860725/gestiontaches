package fr.xwj860725.gestiontaches.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all paths
                .allowedOrigins("http://localhost:5175")  // Browsers allow cross-domain requests for front-end address cors when the front-end calls the back-end api.
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allow all request headers
                .allowCredentials(true);  // Allow carrying credentials (e.g. cookies)
    }
}