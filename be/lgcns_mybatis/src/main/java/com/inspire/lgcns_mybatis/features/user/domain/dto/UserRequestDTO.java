package com.inspire.lgcns_mybatis.features.user.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
/*
Validation Annotation
@NotNull , @MotEmpty, @NotBlank @Pattern(정규표현식), @Email
*/
public class UserRequestDTO {

  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수입력 항목입니다.")
  @Size(min = 8, max =20)
  private String password;

  @NotBlank(message = "이름은 필수입력 항목입니다.")
  private String name;
}
