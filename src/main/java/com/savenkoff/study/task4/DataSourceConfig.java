package com.savenkoff.study.task4;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=task4");
        config.setUsername("postgres");
        config.setPassword("admin");
        config.setConnectionTimeout(10000); // 10 сек
        return new HikariDataSource(config);
    }

    @Bean
    public Connection connection(DataSource dataSource) throws SQLException {
        return dataSource().getConnection();
    }
}
