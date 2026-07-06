package com.inspire.blog_jpa.features.blog.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.blog_jpa.features.blog.domain.dto.BlogRequestDTO;
import com.inspire.blog_jpa.features.blog.domain.dto.BlogResponseDTO;
import com.inspire.blog_jpa.features.blog.domain.entity.BlogEntity;
import com.inspire.blog_jpa.features.blog.repository.BlogRepository;
import com.inspire.blog_jpa.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository    userRepository;
    private final BlogRepository    blogRepository;
    private final ChatClient        chatClient    ; 



    // private final CommentRepository    commentRepository;

    public BlogResponseDTO write(BlogRequestDTO request) {
        System.out.println(">>>> debug blog service write "); 

        // spring security add 
        // SecurityContextHolder 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println(">>>> debug blog service request email                        : "+request.getEmail()); 
        System.out.println(">>>> debug blog service SecurityContextHolder authentication : "+email); 
        // return userRepository.findById(request.getEmail())

        return userRepository.findById(email) 
            .map(user -> {
                BlogEntity blog = blogRepository.save(
                    request.toEntity(user)
                );

                return BlogResponseDTO.fromEntity(blog);
            })
            .orElseThrow(() -> new RuntimeException("사용자 인증 오류")) ;


    }

    @Transactional(readOnly = true) 
    public List<BlogResponseDTO> blogs() {
        System.out.println(">>>> debug blog service blogs "); 
        return blogRepository.findAll()
                .stream()
                .map(BlogResponseDTO::fromEntity)
                .toList() ;
    }

    /*
    bad case)
    blogs 1 : comments 100 
    BlogEntity blog = blogRepository.findById(blogId).get()
    List<CommentEntity> comments = blog.getComments()
    blog.setComments(comments)

    best case)
    Blog  + Comments 한번에 조회하는 것 
    
    */
    @Transactional(readOnly = true)
    public BlogResponseDTO read(Integer blogId) {
        System.out.println(">>>> debug blog service read"); 

        // bad case 
        // blog = blogRepository.findBy()
        // commentRepository.findBlogBlogId(blog) ;
        
        // best case 
        return blogRepository.findByComments(blogId)
                .map(BlogResponseDTO::fromEntityWithComments)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

    }

    public String generate(String keyword) {
        System.out.println(">>>> debug blog service generate"); 
        String genContent = chatClient.prompt()
                                .system("""
                                    너는 전문 블로그 작가야.
                                    키워드에 해당하는 내용으로 본문을 만들어줘.
                                    markdown  형식으로 작성해줘.        
                                """)
                                .user("""
                                    키워드 %s 분야에 해당하는 블로그 작성해줘.
                                    500자 이내로 작성해줘.        
                                """.formatted(keyword)) 
                                .call()
                                .content();
        System.out.println(">>>> debug generate content");
        System.out.println(genContent);
        return genContent ;

    }

    
}
