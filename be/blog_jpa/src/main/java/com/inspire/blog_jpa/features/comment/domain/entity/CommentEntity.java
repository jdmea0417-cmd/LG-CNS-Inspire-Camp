package com.inspire.blog_jpa.features.comment.domain.entity;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "JPA_COMMENT_TBL")

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id", nullable = false)
  private Integer commentId;

  @Column(nullable = false, length = 200)
  private String comment;

  @Column(nullable = false, length = 50)
  private String email;

  // 부모가 삭제되도 삭제하지 않음(orphanRemoval = false)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "blog")
  private BlogEntity blogs;

  public void updateComment(String comment) {
    this.comment = comment;
  }
}
