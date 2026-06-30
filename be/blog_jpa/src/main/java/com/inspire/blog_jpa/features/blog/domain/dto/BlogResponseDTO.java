package com.inspire.blog_jpa.features.blog.domain.dto;

import java.util.List;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;

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
// user - blog(1:N) - comment(1:N)
public class BlogResponseDTO {
  private Integer blogId;
  private String title, content, email;

  // 단순하게 blog 반환하는 구조
  public static BlogResponseDTO fromEntity(BlogEntity entity) {
    return BlogResponseDTO.builder()
        .blogId(entity.getBlogid())
        .title(entity.getTitle())
        .content(entity.getContent())
        .email(entity.getAuthor().getEmail())
        .build();
  }

  // blog + comment(N+1) 반환하는 구조
  // public static BlogResponseDTO fromEntityWithComments(BlogEntity entity) {
  //   return BlogResponseDTO.builder()
  //       .blogId(entity.getBlogid())
  //       .title(entity.getTitle())
  //       .content(entity.getContent())
  //       .email(entity.getAuthor().getEmail())
  //       .comments(
  //           entity.getComments()
  //               .stream()
  //               .map(CommentResponseDTO::fromEntity)
  //               .toList())
  //       .build();
  // }
}
