package com.inspire.lgcns_mybatis.features.comment.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentRequestDTO;
import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentResponseDTO;

@Mapper
public interface CommentMapper {
  public List<CommentResponseDTO> selectRow(Integer blogId);
  public int insertRow(CommentRequestDTO request);
  public int deleteRow(int id);
  public int updateRow(Map<String, Object> map);
}
