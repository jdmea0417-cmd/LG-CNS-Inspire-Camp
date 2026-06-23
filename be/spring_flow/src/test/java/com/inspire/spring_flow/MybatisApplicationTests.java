package com.inspire.spring_flow;

import com.inspire.spring_flow.features.user.domain.dto.UserRequestDTO;
import com.inspire.spring_flow.features.user.domain.dto.UserResponseDTO;
import com.inspire.spring_flow.features.user.repository.UserMapper;
import com.inspire.spring_flow.features.user.service.UserService;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisApplicationTests {

  // 단위테스트를 위한(Junit) + TDD(Test Driven Development)
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserService userService;

  @Test
  public void userMapper() {
    // given(데이터 준비)
    System.out.println(">>>> debug autowired userMapper: " + userMapper);
    UserRequestDTO request = UserRequestDTO.builder()
        .email("jslim9413@naver.com")
        .password("1234")
        .name("임정섭")
        .build();

    // when (실행)
    int insertFlag = userMapper.insertRow(request);

    // then (검증)
    // Assertions.assertNotNull();
    // Assertions.assertEquals("임정섭", response.getName());
    System.out.println("result: " + insertFlag);
    Assertions.assertEquals(1, insertFlag);

  }

  @Test
  public void serviceInsert() {
    // given(데이터 준비)
    System.out.println(">>>> debug autowired userService: " + userService);
    UserRequestDTO request = UserRequestDTO.builder()
        .email("lgcns@naver.com")
        .password("1234")
        .name("임정섭")
        .build();

    // when (실행)
    int insertFlag = userService.insertService(request);

    // then (검증)
    // Assertions.assertNotNull();
    // Assertions.assertEquals("임정섭", response.getName());
    System.out.println("result: " + insertFlag);
    Assertions.assertEquals(1, insertFlag);

  }

  @Test
  public void selectUsers() {
    // 관리자로서 전체 회원 정보를 조회
    // given

    // when
    List<UserResponseDTO> list = userService.selectService();

    // then
    list.stream().forEach(System.out::println);
  }

  @Test
  public void loginUser() {
    // 사용자 로그인에 대한 검증
    // given(데이터 준비)
    System.out.println(">>>> debug autowired userService: " + userService);
    UserRequestDTO request = UserRequestDTO.builder()
        .email("lgcns@naver.com")
        .password("1234")
        .name("임정섭")
        .build();

    // when (실행)
    Optional<UserResponseDTO> response = userService.loginService(request);
    response.orElseThrow(() -> new RuntimeException("이메일 또는 패스워드가 일치하지 않습니다."));

    // then (검증)
    Assertions.assertNotNull(response);
    Assertions.assertEquals("lgcns@naver.com", response.get().getEmail());

  }
}
