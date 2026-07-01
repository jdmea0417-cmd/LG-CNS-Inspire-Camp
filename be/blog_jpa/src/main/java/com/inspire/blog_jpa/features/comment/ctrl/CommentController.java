package com.inspire.blog_jpa.features.comment.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inspire.blog_jpa.features.comment.domain.dto.CommentRequestDTO;
import com.inspire.blog_jpa.features.comment.domain.dto.CommentResponseDTO;
import com.inspire.blog_jpa.features.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<?> write(@RequestBody CommentRequestDTO request) {
    System.out.println(">>>> debug comment controller write");
    System.out.println(">>>> debug params : " + request);

    List<CommentResponseDTO> response = commentService.write(request);
    System.out.println(">>>> debug comment controller write response : " + response);
    if (response != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    System.out.println(">>>> debug comment controller delete");
    System.out.println(">>>> debug params content id : " + id);
    commentService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CommentRequestDTO request) {
    System.out.println(">>>> debug comment controller update");
    System.out.println(">>>> debug comment request path variable : "+id);
    System.out.println(">>>> debug comment request params comment : "+request);

    commentService.update(id, request);

    // if (patchFlag == 1) {
    //   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // } else {
    //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    return null;
  }
}
