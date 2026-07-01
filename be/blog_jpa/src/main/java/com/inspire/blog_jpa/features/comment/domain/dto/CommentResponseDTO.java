package com.inspire.blog_jpa.features.comment.domain.dto;

import com.inspire.blog_jpa.features.comment.domain.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
  private Integer id;
  private Integer commentId, blogId; 
  private String comment, email;

  public static CommentResponseDTO fromEntity(CommentEntity entity) {
    return CommentResponseDTO.builder()
      .id(entity.getCommentId())
      .email(entity.getEmail())
      .commentId(entity.getCommentId())
      .comment(entity.getComment())
      .blogId(entity.getBlogs().getBlogId())
      .build();
  }
}
