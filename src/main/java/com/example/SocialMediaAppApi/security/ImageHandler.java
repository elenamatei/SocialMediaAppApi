package com.example.SocialMediaAppApi.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;

import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//@Configuration
//@EnableWebMvc
//@ComponentScan
//public class ImageHandler implements WebMvcConfigurer {
//
//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//            "classpath:/META-INF/resources/", "classpath:/resources/Images/",
//            "classpath:/static/", "classpath:/public/", "classpath:/resources/" };
//
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/api/resources/**")
//                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//    }
//}
@Configuration
public class ImageHandler implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/api/uploads/**")
                .addResourceLocations("file:///C:/Users/Eliza/Desktop/Licenta/SocialMediaAppApi/src/main/resources/Images/");
    }
}

