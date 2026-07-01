package com.inspire.blog_jpa.features.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inspire.blog_jpa.features.comment.domain.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
  
  @Query("""
      SELECT C
      FROM CommentEntity C
      WHERE C.blogs.blogId = :blogId
      """)
  public List<CommentEntity> findByBlogBlogId(@Param("blogId") Integer blogId);
}
