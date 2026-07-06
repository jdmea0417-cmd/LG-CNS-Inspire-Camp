package com.inspire.blog_jpa.features.openai.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.blog_jpa.features.openai.service.OpenAiService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
public class OpenAiController {
    
    private final OpenAiService openAiService ;


    @PostMapping("/weather")
    public ResponseEntity<?> weather(   @RequestParam("weather")    String weather,
                                        @RequestParam("location")   String location) {
        System.out.println(">>>> debug openai controller  weather ");  
        System.out.println(">>>> debug params weather  : "+weather);
        System.out.println(">>>> debug params location : "+location); 

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(openAiService.recommand(weather, location));
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> quiz(@RequestParam("subject")   String subject) {
        System.out.println(">>>> debug openai controller  quiz ");  
        System.out.println(">>>> debug params subject : "+subject); 

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(openAiService.quiz(subject));
    }
    

}
