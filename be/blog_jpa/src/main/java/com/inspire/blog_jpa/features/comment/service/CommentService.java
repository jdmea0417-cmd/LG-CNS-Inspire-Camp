package com.inspire.blog_jpa.features.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;
import com.inspire.blog_jpa.features.blog.repository.BlogRepository;
import com.inspire.blog_jpa.features.comment.domain.dto.CommentRequestDTO;
import com.inspire.blog_jpa.features.comment.domain.dto.CommentResponseDTO;
import com.inspire.blog_jpa.features.comment.domain.entity.CommentEntity;
import com.inspire.blog_jpa.features.comment.repository.CommentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
  
  private final CommentRepository commentRepository;
  private final BlogRepository blogRepository;

  public List<CommentResponseDTO> write(CommentRequestDTO request) {
    System.out.println(">>>> debug comment service write : "+request);

    BlogEntity blog = blogRepository.findByComments(request.getBlogId())
      .orElseThrow(() -> new RuntimeException("블로그 찾기 오류"));

    commentRepository.save(request.toEntity(blog));
    return commentRepository.findByBlogBlogId(request.getBlogId())
      .stream()
      .map(CommentResponseDTO::fromEntity)
      .toList();
  }

  public void delete(Integer commentId) {
    System.out.println(">>>> debug comment service delete : "+commentId);
    CommentEntity comment = commentRepository.findById(commentId)
      .orElseThrow(() -> new RuntimeException("댓글 찾기 오류"));
    commentRepository.delete(comment);
  }

  /*
  jpa update 주의사항
  - DML : transactional 필요
  - update 시 entity를 findById로 조회 후 setter로 수정 후 save 호출

  (Dirty Checking)
  Entity entity = repository.findById(id)
  entity.xxxxx(); 수정 // commit
  repository.save(entity); // 생략 가능
  */

  public void update(Integer commentId, CommentRequestDTO request) {
    System.out.println(">>>> debug comment service update");
    System.out.println(">>>> debug comment service update commentId : "+commentId);
    System.out.println(">>>> debug comment service update content : "+request.getComment());

    commentRepository.findById(commentId)
      .map(comment -> {
        comment.updateComment(request.getComment());
        return comment;
      })
      .orElseThrow(() -> new RuntimeException("댓글 찾기 오류"));
  }
}
