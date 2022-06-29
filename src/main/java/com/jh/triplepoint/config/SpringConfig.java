package com.jh.triplepoint.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ConfigurationPropertiesScan("com.jh.triplepoint")
@EnableConfigurationProperties
@EnableScheduling
@Configuration
public class SpringConfig {
}
