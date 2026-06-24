package com.inspire.lgcns_mybatis.features.user.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.inspire.lgcns_mybatis.features.user.domain.dto.UserRequestDTO;
import com.inspire.lgcns_mybatis.features.user.domain.dto.UserResponseDTO;

@Mapper
public interface UserMapper {
  public int insertRow(UserRequestDTO request);
  public Optional<UserResponseDTO> loginRow(UserRequestDTO request);
}
