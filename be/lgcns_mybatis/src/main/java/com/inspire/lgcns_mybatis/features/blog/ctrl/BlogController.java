package com.inspire.lgcns_mybatis.features.blog.ctrl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogRequestDTO;
import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogResponseDTO;
import com.inspire.lgcns_mybatis.features.blog.service.BlogService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
  private final BlogService blogService;

  @GetMapping("/list")
  public ResponseEntity<List<BlogResponseDTO>> list() {
    System.out.println(">>>> debug blog controller list");
    List<BlogResponseDTO> list = blogService.list();
    System.out.println(list);
    if(list.size() != 0) {
      return ResponseEntity.status(HttpStatus.OK).body(list);
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
    }
  }

  @PostMapping("/write")
  public ResponseEntity<Void> write(@RequestBody BlogRequestDTO request) {
    System.out.println(">>>> debug blog controller write");
    System.out.println(">>>> debug blog request params : "+request);

    int insertFlag = blogService.write(request);

    // code : 201, 400
    if(insertFlag != 0) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/read/{id}")
  public ResponseEntity<BlogResponseDTO> read(@PathVariable("id") Integer id) {
    System.out.println(">>>> debug blog controller write");
    System.out.println(">>>> debug blog request path variable : "+id);  
    
    BlogResponseDTO response = blogService.read(id);
    System.out.println(">>>> debug blog read result");
    System.out.println(response);
    // code : 200, 404
    if(response != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
