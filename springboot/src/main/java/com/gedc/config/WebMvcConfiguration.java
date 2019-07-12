package com.gedc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gedc.interceptor.ApiRequestInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public ApiRequestInterceptor getApiRequestInterceptor() {
        return new ApiRequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getApiRequestInterceptor());
    }

}
