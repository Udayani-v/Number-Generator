package com.numbergenerator.demo.serviceimpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@Component
@Service
public class ExecutorServiceConfig {
	
	@Bean("fixedThreadPool")
    public ExecutorService cachedThreadPool() {
        return Executors.newFixedThreadPool(5);
	}

}
