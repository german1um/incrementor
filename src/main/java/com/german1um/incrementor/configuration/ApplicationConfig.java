package com.german1um.incrementor.configuration;

import com.german1um.incrementor.service.Incrementor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ApplicationConfig {

    @Bean
    public Incrementor incrementor() {
        return new Incrementor(new AtomicInteger(0), Integer.MAX_VALUE);
    }
}
