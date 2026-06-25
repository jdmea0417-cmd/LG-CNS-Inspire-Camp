package com.inspire.lgcns_mybatis.features.user.ctrl;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.lgcns_mybatis.features.user.domain.dto.UserRequestDTO;
import com.inspire.lgcns_mybatis.features.user.domain.dto.UserResponseDTO;
import com.inspire.lgcns_mybatis.features.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;


/*
Controller
- 파라미터를 DTO로 바인딩해서 데이터의 유효성을 체크하는 것
- 서비스 레이어와 의존성을 가지는 것
- ResponseEntity를 통해서 데이터 및 상태코드를 전달
*/
@Tag(name = "User API" , description = "사용자 생성 및 로그인 관련 API 명세서")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // swagger 명세
  @Operation(summary = "회원가입", description = "신규가입(email, password, name)")
  @ApiResponse(responseCode = "201", description = "회원가입 성공")
  @ApiResponse(responseCode = "500", description = "회원가입 실패")
  @PostMapping("/signUp")
  public ResponseEntity<Void> signUp(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "사용자 가입정보를 담는 DTO",
        required = true,
        content = @Content(
        schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRequestDTO.class)
      )) @RequestBody UserRequestDTO request) {
    System.out.println(">>>> debug user controller signup");
    System.out.println(">>>> debug params :" + request);
    int insertFlag = userService.signUp(request);
    if (insertFlag != 0) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
 
  @Operation(summary = "로그인", description = "사용자 로그인(email, password)")
  @ApiResponse(responseCode = "200", description = "로그인 성공")
  @ApiResponse(responseCode = "400", description = "로그인 실패")
  @PostMapping("/signIn")
  /*
  - Json Web Token
  - 인증(Autherntication) : 사용자가 누구인지 확인하는 것
  - 인가(Authorization) : 사용자가 어떤 권한을 가지고 있는지 확인하는 것
  */
  public ResponseEntity<UserResponseDTO> signIn(
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "사용자 로그인정보를 담는 DTO",
        required = true,
        content = @Content(
        schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRequestDTO.class)
      )) @RequestBody UserRequestDTO request) {
    System.out.println(">>>> debug user controller signin");
    System.out.println(">>>> debug params :" + request);

    Map<String, Object> map = userService.signInWithTokens(request);
    
    // token : access token, refresh token
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", (String) map.get("at"));
    headers.add("Refresh-Token", (String) map.get("rt"));
    headers.add("Access-Control-Expose-Headers", "Authorization, Refresh-Token");

    // service - signIn
    return ResponseEntity
          .status(HttpStatus.OK)
          .headers(headers)
          .body((UserResponseDTO) map.get("data"));
  }
}
