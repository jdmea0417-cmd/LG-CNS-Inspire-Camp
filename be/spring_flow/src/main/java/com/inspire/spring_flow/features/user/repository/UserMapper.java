package com.inspire.spring_flow.features.user.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.inspire.spring_flow.features.user.domain.dto.UserRequestDTO;
import com.inspire.spring_flow.features.user.domain.dto.UserResponseDTO;

// Mybatis 를 구현하는 Data Access Object
/*
JDBC
try{
- 특정 벤더사의 드라이버를 로딩
- 접속(root, 5242)
- query 작성
- query 실행
- 실행된 결과 집합을 반환
- 핸들링
} catch() {
  try{
  - 외부 리소스 연결을 종료
  }catch() {
  
  }
}
-> mybatis
*/
@Mapper
public interface UserMapper {
  public int insertRow(UserRequestDTO request);
  public List<UserResponseDTO> selectRow();
  public Optional<UserResponseDTO> loginRow(UserRequestDTO request);
}
