package com.roman.mangaapi.config;

import com.roman.mangaapi.api.JikanClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-level beans.
 */
@Configuration
public class AppConfig {
    /**
     * Creates a JikanClient bean.
     * @return JikanClient
     */
    @Bean public JikanClient jikanClient() {
        return new JikanClient();
    }
}
