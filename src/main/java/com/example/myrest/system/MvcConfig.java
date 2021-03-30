package com.example.myrest.system;

import com.example.myrest.ratelimiter.RateLimiterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.cache.CacheManager;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimiterInterceptor(
                    Integer.parseInt(env.getProperty("myapi.limit.num", "20")),
                    Integer.parseInt(env.getProperty("myapi.limit.interval", "60")),
                    cacheManager))
                .addPathPatterns(env.getProperty("myapi.path.root") + env.getProperty("myapi.path.burgers") + "/**");
    }
}

