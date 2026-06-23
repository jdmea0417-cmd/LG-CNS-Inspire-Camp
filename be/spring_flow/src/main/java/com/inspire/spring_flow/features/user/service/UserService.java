package com.inspire.spring_flow.features.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.spring_flow.features.user.domain.dto.UserRequestDTO;
import com.inspire.spring_flow.features.user.domain.dto.UserResponseDTO;
import com.inspire.spring_flow.features.user.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public int insertService(UserRequestDTO request) {
    System.out.println(">>>> debug user service insertService");
    System.out.println(">>>> debug params: " + request);
    return userMapper.insertRow(request);
  }

  public List<UserResponseDTO> selectService() {
    System.out.println(">>>> debug user service selectService");
    return userMapper.selectRow();
  }

  public Optional<UserResponseDTO> loginService(UserRequestDTO request) {
    System.out.println(">>>> debug user service loginService");
    return userMapper.loginRow(request);
  }
}
