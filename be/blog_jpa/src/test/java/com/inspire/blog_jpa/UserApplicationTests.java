package com.inspire.blog_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inspire.blog_jpa.features.user.domain.dto.UserRequestDTO;
import com.inspire.blog_jpa.features.user.domain.dto.UserResponseDTO;
import com.inspire.blog_jpa.features.user.domain.entity.UserEntity;
import com.inspire.blog_jpa.features.user.repository.UserRepository;

@SpringBootTest
public class UserApplicationTests {
    
    @Autowired
    private UserRepository userRepository ; 

    @Test
    public void signUp() {
        System.out.println("debug >>>> user signUp"); 

        UserRequestDTO request = UserRequestDTO.builder()
                                    .email("jslim9413@naver.com")
                                    .password("1234")
                                    .name("임섭순")
                                    .role("admin")
                                    .build(); 
        UserEntity entity = userRepository.save(UserRequestDTO.toEntity(request));
        System.out.println(">>>>> response");
        System.out.println(UserResponseDTO.fromEntity(entity)); 

    }

    @Test
    public void signIn() {
        System.out.println("debug >>>> user signIn"); 

        UserRequestDTO request = UserRequestDTO.builder()
                                    .email("jslim9413@naver.com")
                                    .password("1234")
                                    .build(); 

        UserEntity loginEntity = userRepository
            .findByEmailAndPassword(request.getEmail(), request.getPassword())
            .orElseThrow(() -> new RuntimeException("로그인 실패"));


        UserResponseDTO response = UserResponseDTO.fromEntity(loginEntity);
        System.out.println("debug >>>> entity   : "+loginEntity );  
        System.out.println("debug >>>> response : "+response ); 



        
    }

}
