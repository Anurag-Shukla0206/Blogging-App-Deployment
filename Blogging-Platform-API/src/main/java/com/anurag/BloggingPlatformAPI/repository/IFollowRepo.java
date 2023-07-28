package com.anurag.BloggingPlatformAPI.repository;

import com.anurag.BloggingPlatformAPI.model.Follow;
import com.anurag.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollowRepo extends JpaRepository<Follow,Long> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User targetUser, User follower);
}
