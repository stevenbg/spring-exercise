package com.example.myrest.system;

import com.example.myrest.ratelimiter.RateLimiterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new RateLimiterInterceptor(3600, 3600))
                .addPathPatterns("/v1/burgers/**");
    }


}

