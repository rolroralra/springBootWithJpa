package com.example.springbootwithjpa.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();

        // FORCE LAZY LOADING
        hibernate5Module.configure(Feature.FORCE_LAZY_LOADING, true);

        return hibernate5Module;
    }
}
