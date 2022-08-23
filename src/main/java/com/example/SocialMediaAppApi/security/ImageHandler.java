package com.example.SocialMediaAppApi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;



@Configuration
public class ImageHandler implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/api/uploads/**")
                .addResourceLocations("file:///C:/Users/Eliza/Desktop/Licenta/SocialMediaAppApi/src/main/resources/Images/");
    }
}