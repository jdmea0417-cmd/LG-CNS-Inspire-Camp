package com.inspire.blog_jpa.features.blog.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.blog_jpa.features.blog.domain.dto.BlogRequestDTO;
import com.inspire.blog_jpa.features.blog.domain.dto.BlogResponseDTO;
import com.inspire.blog_jpa.features.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/*
api 명세서 만들 때 사용하는 네이밍규칙 
- Noun 작성
GET    api/v1/blogs
GET    api/v1/blogs/{blogId}  
POST   api/v1/blogs
PUT    api/v1/blogs/{blogId}
DELETE api/v1/blogs/{blogId} 
*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService ;

    @PostMapping("/blogs")
    public ResponseEntity<?> write(@RequestBody BlogRequestDTO request) {
        System.out.println(">>>> debug blog controller write "); 
        System.out.println(">>>> debug params : "+request); 

        BlogResponseDTO response = blogService.write(request);
        System.out.println(">>>> debug blog controller write response :  "+response); 
        
        if(response != null ) {
            return ResponseEntity.status(HttpStatus.CREATED).build() ;     
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ; 
        }
    }


    @GetMapping("/blogs")
    public ResponseEntity<?> blogs() {
        System.out.println(">>>> debug blog controller blogs "); 
        
        List<BlogResponseDTO> response = blogService.blogs();
        System.out.println(">>>> debug blog controller blogs response :  "+response.size()); 
        
        if(response.size() != 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body(response) ;     
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ; 
        }
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogResponseDTO> read(@PathVariable("id") Integer id) {
        System.out.println(">>>> debug blog controller blogs/{id} = "+id); 
        BlogResponseDTO response = blogService.read(id);
        System.out.println(">>>> debug blog read result");
        System.out.println(response);
        // code : 200(ok), 404(not found)
        if(response != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response); 
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
