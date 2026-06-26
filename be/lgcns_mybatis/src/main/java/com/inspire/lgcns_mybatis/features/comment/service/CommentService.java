package com.inspire.lgcns_mybatis.features.comment.service;

import java.util.Map;

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
    commentMapper.insertRow(request);

    return request.getId();
  } 

  public int delete(int id) {
    System.out.println(">>>> debug comment service delete");
    return commentMapper.deleteRow(id);
  }

  public int patch(Map<String, Object> map) {
    System.out.println(">>>> debug comment service update");
    return commentMapper.updateRow(map);
  }
}
