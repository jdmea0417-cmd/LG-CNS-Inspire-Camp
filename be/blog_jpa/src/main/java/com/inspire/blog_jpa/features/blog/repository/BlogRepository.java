package com.inspire.blog_jpa.features.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Integer>{
}