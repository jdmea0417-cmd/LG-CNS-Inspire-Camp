package com.inspire.lgcns_mybatis.features.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.lgcns_mybatis.features.user.domain.dto.UserRequestDTO;
import com.inspire.lgcns_mybatis.features.user.domain.dto.UserResponseDTO;
import com.inspire.lgcns_mybatis.features.user.repository.UserMapper;
import com.inspire.lgcns_mybatis.features.common.token.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  
  private final UserMapper userMapper;
  private final JwtProvider jwtProvider;

  public int signUp(UserRequestDTO request) {
    System.out.println(">>>> debug user service signup");
    System.out.println(">>>> debug params :" + request);
    return userMapper.insertRow(request);
  }

  public UserResponseDTO signIn(UserRequestDTO request) {
    System.out.println(">>>> debug user service signin");
    System.out.println(">>>> debug params :" + request);
    return userMapper.loginRow(request).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public Map<String, Object> signInWithTokens(UserRequestDTO request) {
    System.out.println(">>>> debug user service signin with tokens");
    
    Map<String, Object> map = new HashMap<>();
    UserResponseDTO response = userMapper
      .loginRow(request)
      .orElseThrow(() -> new RuntimeException("User not found"));

    String at = jwtProvider.createAT(response.getEmail());
    String rt = jwtProvider.createRT(response.getEmail());

    // inMemory DB - Redis, H2
    // at, rt 담아서 관리, 인증코드
    map.put("data", response);
    map.put("at", at);
    map.put("rt", rt);
    return map;
  }
}
