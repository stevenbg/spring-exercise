package com.example.myrest.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.sql.DataSource;

@Configuration
// todo .env?
// There's an @EnableJpaRepositories at the app root
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }

//    Spring Boot sets up Hibernate by default

    @Bean
    public CacheManager beanCacheManager() {
        return Caching.getCachingProvider().getCacheManager();
    }
}
