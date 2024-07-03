package com.pandadescuentos.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500","http://localhost:5173","http://localhost:8080") // Permitir solicitudes desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir estos métodos HTTP
                .allowCredentials(true); // Permitir credenciales (por ejemplo, cookies)
    }
}
