package com.bcbs.customer.survey.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), HttpMethod.PUT.name())
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);
                        //.allowedOrigins(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:4200")
                        //.allowedMethods(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), HttpMethod.PUT.name())
                        //.allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);

            }
        };
    }
}
