package com.anurag.BloggingPlatformAPI.repository;

import com.anurag.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String email);

    User findFirstByUserName(String userName);
}
