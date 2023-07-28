package com.anurag.BloggingPlatformAPI.repository;

import com.anurag.BloggingPlatformAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface IPostRepo extends JpaRepository<Post,Long> {
}
