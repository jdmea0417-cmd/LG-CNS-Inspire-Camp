package com.inspire.blog_jpa.features.blog.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer>{
    // JPQL
    @Query("""
        SELECT      b
        FROM        BlogEntity b        
        LEFT JOIN   FETCH b.comments
        WHERE       b.blogId = :blogId 
    """)
    public Optional<BlogEntity> findByComments(@Param("blogId") Integer blogId) ;

    // 메서드 이름 기반 쿼리 - 
    // 대소문자 무시하고 keyword 컬럼에 부분 일치(LIKE '%keyword%') 검색
    // @Query("SELECT b FROM Blog b WHERE LOWER(b.keyword) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    // public List<Blog> findByKeywordContainingIgnoreCase(@Param("keyword") String keyword);
    public List<BlogEntity> findByKeywordContainingIgnoreCase(String keyword);

}



