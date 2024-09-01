package com.example.rest_service.userprofile;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Repository
public class UserLikeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void recordLike(int likerId, int likedId) {
        String sql = "INSERT INTO userlikes (liker_id, liked_id, timestamp) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, likerId, likedId, LocalDateTime.now());
    }

    public int getLikeCountInLast10Minutes(int likerId) {
        String sql = "SELECT COUNT(*) FROM userlikes WHERE liker_id = ? AND timestamp >= NOW() - INTERVAL '10 minutes'";
        return jdbcTemplate.queryForObject(sql, new Object[]{likerId}, Integer.class);
    }
}
