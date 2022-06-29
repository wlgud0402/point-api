package com.jh.triplepoint.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ToString
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.main", ignoreUnknownFields = false)
public class DataSourceProperty {
    private final String username;
    private final String password;
    private final String url;
}

