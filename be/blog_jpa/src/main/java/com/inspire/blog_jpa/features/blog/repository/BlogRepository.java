package com.inspire.blog_jpa.features.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer>{
  // JPQL
  @Query("""
      SELECT B
      FROM BlogEntity B
      LEFT JOIN FETCH B.comments
      WHERE B.blogId = :blogId
  """)
  public Optional<BlogEntity> findByComments(@Param("blogId") Integer blogId);
}