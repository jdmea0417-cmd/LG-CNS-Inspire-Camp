package com.inspire.lgcns_mybatis.features.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogRequestDTO;
import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogResponseDTO;
import com.inspire.lgcns_mybatis.features.blog.repository.BlogMapper;
import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentResponseDTO;
import com.inspire.lgcns_mybatis.features.comment.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogMapper blogMapper;
  private final CommentMapper commentMapper;



  public List<BlogResponseDTO> list() {
    System.out.println(">>>> debug blog service list");
    return blogMapper.listRow();
  }

  public int write(BlogRequestDTO request) {
    System.out.println(">>>> debug blog service write");
    return blogMapper.insertRow(request);
  }

  @Transactional(readOnly = true)
  public BlogResponseDTO read(Integer id) {
    System.out.println(">>>> debug blog service read");
    BlogResponseDTO blog = blogMapper.readRow(id)
          .orElseThrow(() -> new RuntimeException("Not Found"));
    
    List<CommentResponseDTO> comments = commentMapper.selectRow(blog.getId());
    blog.setComments(comments);

    return blog;
  }
}
