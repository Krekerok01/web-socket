package com.krekerok.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DemoConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

}

