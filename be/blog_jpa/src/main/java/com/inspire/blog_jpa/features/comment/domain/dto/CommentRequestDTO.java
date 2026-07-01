package com.inspire.blog_jpa.features.comment.domain.dto;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;
import com.inspire.blog_jpa.features.comment.domain.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
  
  private Integer commentId, blogId;
  private String comment, email;
  
  public CommentEntity toEntity(BlogEntity request) {
    return CommentEntity.builder()
      .comment(this.getComment())
      .email(this.getEmail())
      .blogs(request)
      .build();
  }
}
