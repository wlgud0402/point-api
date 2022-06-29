package com.jh.triplepoint.config;

import com.jh.triplepoint.property.DataSourceProperty;
import com.jh.triplepoint.property.HikariProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {
    private final HikariProperty hikariProperty;
    private final DataSourceProperty dataSourceProperty;

    @Bean
    DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dataSourceProperty.getUrl());
        config.setUsername(dataSourceProperty.getUsername());
        config.setPassword(dataSourceProperty.getPassword());
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(hikariProperty.getMaximumPoolSize());
        config.setMinimumIdle(hikariProperty.getMinimumIdle());
        return new HikariDataSource(config);
    }

}