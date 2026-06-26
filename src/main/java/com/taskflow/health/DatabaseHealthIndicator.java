package com.taskflow.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseHealthIndicator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Health health() {
        try {
            jdbcTemplate.execute("SELECT 1");

            return Health.up()
                    .withDetail("database", "PostgreSQL is reachable")
                    .build();

        } catch (Exception e) {

            return Health.down()
                    .withDetail("database", "Database connection failed")
                    .withException(e)
                    .build();
        }
    }
}