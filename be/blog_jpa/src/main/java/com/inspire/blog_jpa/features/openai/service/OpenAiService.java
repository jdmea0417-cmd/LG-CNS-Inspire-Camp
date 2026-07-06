package com.inspire.blog_jpa.features.openai.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire.blog_jpa.features.openai.domain.ChatResponseDTO;
import com.inspire.blog_jpa.features.openai.domain.QuizResponseDTO;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
인공지능 모델과 소통을 위한 메시지 형태 
endpoint : open ai 
messages{
    Map - {
        role : system, user, assistant
        content : xxxxxx
    }
}

builder pattern 
- ChatClient 

build.gradle - okhttp , webflux 
- OkHttpClient 

Json -> Object(DTO) 
- jackson lib 

*/
@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final ChatClient    chatClient   ;
    private final ObjectMapper  objectMapper ; 
    private final OkHttpClient  okHttpClient ;


    @Value("${spring.ai.openai.api-key}")
    private String key ; 

    @Value("${spring.ai.openai.chat.options.model}")
    private String model ; 

    // endpoint : 3 model, 4 model 
    // https://api.openai.com/v1 , https://api.openai.com/v1/chat/completions 

    private String endPoint = "https://api.openai.com/v1/chat/completions" ;

    // public ChatResponseDTO recommand(String weather, String location) {
    //     System.out.println(">>>> debug openai service  recommand ");  
    //     System.out.println(">>>> debug openai service  model    : "+model); 
    //     System.out.println(">>>> debug openai service  endPoint : "+endPoint); 
        
    //     String prompt="""
    //         너는 날씨에 따른 맛집을 추천하는 인공지능 전문가야
    //         조건과 아래 규칙에 맞게 응답해줘
    //         조건 
    //         - 날씨 : "%s"
    //         - 우치 : "%s"
    //         출력예시
    //         {
    //             "weather"  : "날시",
    //             "location" : "위치",
    //             "restaurants" : [
    //                 {"name" : "음식점명", "category" : "분류" , "reason" : "추천이유"}
    //             ]
    //         }
    //     """.formatted(weather, location);

    //     Map<String, Object> userRole = new HashMap<>();
    //     userRole.put("role", "user");
    //     userRole.put("content", prompt); 


    //     Map<String, Object> messages = new HashMap<>();
    //     messages.put("model", model);
    //     messages.put("messages", List.of(userRole));

    //     // object(Map) -> json 
    //     String requestJson = null ; 
    //     try {
    //         requestJson = objectMapper.writeValueAsString(messages);
    //     } catch (JsonProcessingException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println(">>>> debug requestJson");
    //     System.out.println(requestJson); 
        
    //     Request requestInfo = new Request.Builder()
    //                             .url(endPoint)
    //                             .header("Authorization", "Bearer "+key)
    //                             .header("Content-Type", "application/json")
    //                             .post(RequestBody.create(requestJson, MediaType.parse("application/json")))
    //                             .build();

    //     Response response = null ;
    //     try {
    //         response = okHttpClient.newCall(requestInfo).execute();
    //         System.out.println(">>>> debug response "); 
    //         System.out.println(response); 
    //         //////////////////////////////////////////////////
    //         String responseJson = response.body().string() ; 
    //         System.out.println(responseJson); 
    //         //////////////////////////////////////////////////
    //         JsonNode node = objectMapper.readTree(responseJson);
    //         System.out.println(">>>> debug JsonNode "); 
    //         System.out.println(node); 
            
    //         String exr = node.at("/choices/0/message/content").asText();
    //         System.out.println(">>>> debug exr "); 
    //         System.out.println(exr); 

    //         ChatResponseDTO resonse = objectMapper.readValue(exr, ChatResponseDTO.class) ;
    //         System.out.println(">>>> debug resonse result (String(json) -> dto )"); 
    //         System.out.println(resonse); 
            
            
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
        
    //     return null ;

    //     // chat client version 
    //     // return chatClient.prompt()
    //     //     .system("""
                    
    //     //     """)
    //     //     .user("""
                    
    //     //     """)
    //     //     .call()
    //     //     .content()        
    //     //     or
    //     //     .entity(XXXXXXDTO.class) ;
         
    // } 


    /* 
    ChatClinet - OpenAI api 직접호출하지 않고 
    gpt-key  : yaml - OpenAI Configuration(gpt-key, model) 
    endPoint : ChatClient에 이미 endPoint 들어 있음...
    */
    public ChatResponseDTO recommand(String weather, String location) {
        System.out.println(">>>> debug openai service  recommand ");  
        System.out.println(">>>> debug openai service  model    : "+model); 
        System.out.println(">>>> debug openai service  endPoint : "+endPoint); 
                
        // chat client version 
        ChatResponseDTO response = chatClient.prompt()
            .system("""
                 반드시 json 형태롤 응답해줘   
            """)
            .user("""
                너는 날씨에 따른 맛집을 추천하는 인공지능 전문가야
                조건과 아래 규칙에 맞게 응답해줘
                조건 
                - 날씨 : "%s"
                - 우치 : "%s"
                출력예시
                {
                    "weather"  : "날시",
                    "location" : "위치",
                    "restaurants" : [
                        {"name" : "음식점명", "category" : "분류" , "reason" : "추천이유"}
                    ]
                }   
            """.formatted(weather, location))
            .call()
            .entity(ChatResponseDTO.class) ;
        
        System.out.println(">>>> debug resonse(json -> dot)"); 
        System.out.println(response); 

        return response ;
    } 
    
    /*
    yml - OpenAI Configuration - OpenAI API - OpenAI Chat Model - ChatClient - prompt();
    */
    public QuizResponseDTO quiz(String subject) {
        System.out.println(">>>> debug openai service  quiz ");  
        System.out.println(">>>> debug openai service subject : "+subject); 

        QuizResponseDTO response = chatClient.prompt()
            .system("""
                너는 멋진 인공지능이고 국가공인 문제출제 전문가야.
                반드시 json만 반환해줘.
            """)
            .user("""
                너의 문제출제 전공분야는 %s 전공이야.
                규칙 
                - 무조건 json 형태로 만들어줘
                - 10문제를 만들거야.
                - ` 쓰지마.
                출력예시
                {
                    "quizs" : [
                        {
                            "question" : 문제내용,
                            "options"  : [보기1, 보기2, 보기3, 보기4],
                            "answer    : 정답,
                            "desc"     : 해설
                        }
                    ]    
                }   
            """.formatted(subject))
            .call()
            .entity(QuizResponseDTO.class) ;
        
        System.out.println(">>>> debug resonse(json -> dot)"); 
        System.out.println(response); 

        return response ;
    }


}
