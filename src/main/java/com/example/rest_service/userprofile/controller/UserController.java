package com.example.rest_service.userprofile.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.rest_service.userprofile.ProfileVisitDao;
import com.example.rest_service.userprofile.UserLikeDao;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProfileVisitDao profileVisitDao;

    @Autowired
    private UserLikeDao userLikeDao;

    @PostMapping("/visit")
    public String recordVisit(@RequestParam int visitorId, @RequestParam int visitedId) {
        if (isFraudulentActivity(visitorId)) {
            return "Fraudulent activity detected. Action not allowed.";
        }
        profileVisitDao.recordVisit(visitorId, visitedId);
        return "Visit recorded";
    }

    @PostMapping("/like")
    public String recordLike(@RequestParam int likerId, @RequestParam int likedId) {
        if (isFraudulentActivity(likerId)) {
            return "Fraudulent activity detected. Action not allowed.";
        }
        userLikeDao.recordLike(likerId, likedId);
        return "Like recorded";
    }

    private boolean isFraudulentActivity(int userId) {
        int visitCount = profileVisitDao.getVisitCountInLast10Minutes(userId);
        int likeCount = userLikeDao.getLikeCountInLast10Minutes(userId);

        // Check if either count exceeds 100
        return visitCount >= 100 || likeCount >= 100;
    }
}
