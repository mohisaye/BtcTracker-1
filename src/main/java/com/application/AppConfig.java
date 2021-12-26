package com.application;

import com.application.entities.BTCInfo;
import com.application.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public User user() {
        return new User();
    }
    public BTCInfo btcInfo() {
        return new BTCInfo();
    }



}
