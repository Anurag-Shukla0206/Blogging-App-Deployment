package com.anurag.BloggingPlatformAPI.repository;

import com.anurag.BloggingPlatformAPI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
}
