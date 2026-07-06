package com.inspire.blog_jpa.features.common.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

@Configuration
public class OpenAiConfig {
    
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        System.out.println(">>>> debug OpenAi config ChatClient ");
        return builder.build() ; 
    }

    @Bean
    public ObjectMapper objectMapper() {
        System.out.println(">>>> debug OpenAi config ObjectMapper ");
        return new ObjectMapper() ; 
    }

    @Bean
    public OkHttpClient okHttpClient() {
        System.out.println(">>>> debug OpenAi config ObjectMapper ");
        return new OkHttpClient() ; 
    }

}
