package com.inspire.lgcns_mybatis.features.blog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogRequestDTO;
import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogResponseDTO;

@Mapper
public interface BlogMapper {
    public List<BlogResponseDTO> listRow();
    public int insertRow(BlogRequestDTO request);
    public Optional<BlogResponseDTO> readRow(Integer id);
}
