package com.inspire.blog_jpa.features.blog.domain.entity;

import java.util.List;

import com.inspire.blog_jpa.features.user.domain.entity.UserEntity;

import com.inspire.blog_jpa.features.comment.domain.entity.CommentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "JPA_BLOG_TBL")

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

// 참조관계의 엔티티는 엔티티 간의 연관관계를 관리해야함
// cascade, fetch, optional, orphanRemoval
public class BlogEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
  private Integer blogId;

  @Column(nullable = false, length = 200)
  private String title;

  @Column(nullable = false, length = 200)
  private String content;

  // 외래키
  // optional : NULL 허용 여부
  // select * from user where id = x ; 시, blog는 조회하지 않음(FetchType.LAZY)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "email")
  private UserEntity author;

  @OneToMany(mappedBy = "blogs", orphanRemoval = true)
  private List<CommentEntity> comments;

}
