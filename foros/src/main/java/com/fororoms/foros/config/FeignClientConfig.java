package com.fororoms.foros.config;

import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class FeignClientConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public FeignClientBuilder feignClientBuilder() {
        return new FeignClientBuilder(context);
    }

}