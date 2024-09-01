package com.example.rest_service.userprofile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Repository
public class ProfileVisitDao {
    private final JdbcTemplate jdbcTemplate;

    public ProfileVisitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
    }

    public void recordVisit(int visitorId, int visitedId) {
        String sql = "INSERT INTO profilevisits (visitor_id, visited_id, timestamp) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, visitorId, visitedId, LocalDateTime.now());
    }

    public int getVisitCountInLast10Minutes(int visitorId) {
        String sql = "SELECT COUNT(*) FROM ProfileVisits WHERE visitor_id = ? AND timestamp >= NOW() - INTERVAL '10 minutes'";
        return jdbcTemplate.queryForObject(sql, new Object[]{visitorId}, Integer.class);
    }
}
