package com.inspire.blog_jpa.openai.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.blog_jpa.openai.service.OpenAIService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
public class OpenAIController {

  private final OpenAIService openAIService;

  @PostMapping("/weather")
  public ResponseEntity<?> postMethodName(@RequestParam("weather") String weather,
      @RequestParam("location") String location) {
    System.out.println(">>>> debug openai controller weather");
    System.out.println(">>>> debug params weather : "+weather);
    System.out.println(">>>> debug params location : "+location);

    return ResponseEntity.status(HttpStatus.OK).body(openAIService.recommand(weather, location));
  }

}
