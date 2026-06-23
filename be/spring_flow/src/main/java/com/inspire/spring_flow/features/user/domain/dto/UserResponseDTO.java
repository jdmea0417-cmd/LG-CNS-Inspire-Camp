package com.inspire.spring_flow.features.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UserResponseDTO {
  private String email, password, name;
}
