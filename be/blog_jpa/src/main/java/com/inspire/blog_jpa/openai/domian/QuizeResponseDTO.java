package com.inspire.blog_jpa.openai.domian;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizeResponseDTO {
  private List<Quiz> quizs;

  public static class Quiz{
    private String question;
    private List<String> options;
    private String answer;
    private String desc; 
  }
}
