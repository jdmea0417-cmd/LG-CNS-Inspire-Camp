package com.inspire.lgcns_mybatis.features.comment.service;

import org.springframework.stereotype.Service;

import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentRequestDTO;
import com.inspire.lgcns_mybatis.features.comment.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentMapper commentMapper;

  public int write(CommentRequestDTO request) {
    System.out.println(">>>> debug comment service write");
    return commentMapper.insertRow(request);
  } 
}
