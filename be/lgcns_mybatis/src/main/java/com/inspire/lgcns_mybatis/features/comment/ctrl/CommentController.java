package com.inspire.lgcns_mybatis.features.comment.ctrl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inspire.lgcns_mybatis.features.blog.domain.dto.BlogResponseDTO;
import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentRequestDTO;
import com.inspire.lgcns_mybatis.features.comment.domain.dto.CommentResponseDTO;
import com.inspire.lgcns_mybatis.features.comment.service.CommentService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blog/comment")
@RequiredArgsConstructor
@Tag(name = "Blog Comment API", description = "Blog 댓글 관련 API 명세서")
public class CommentController {

  // action : crud
  // user endPoint : http:// serverip:port / blog / comment/ xxxxx
  // 401 -> 인증실패
  // 403 -> 권한없음
  private final CommentService commentService;
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "댓글입력성공"),
      @ApiResponse(responseCode = "400", description = "댓글입력실패")
  })
  @PostMapping("/write")
  public ResponseEntity<?> write(@RequestBody CommentRequestDTO request) {
    System.out.println(">>>> debug comment controller write");
    System.out.println(">>>> debug comment request params : "+request);

    int commentId = commentService.write(request);

    System.out.println(">>>> debug comment controller return primary key = "+commentId);
    
    if(commentId != 0) {
      return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponseDTO.builder()
        .blogid(request.getBlogId())
        .email(request.getEmail())
        .comment(request.getComment())
        .id(commentId)
        .build());
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  // status code : 204 -> 수정성공이고 응답본문이 없는 코드
  // status code : 404 -> 수정할 대상이 없는/리소스가 존재하지 않는
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "댓글수정성공"),
      @ApiResponse(responseCode = "404", description = "댓글수정실패")
  })
  @PatchMapping("/update/{id}")
  public ResponseEntity<BlogResponseDTO> update(@PathVariable("id") Integer id, 
    @RequestBody Map<String, Object> map) {
    
    map.put("id", id);
    System.out.println(">>>> debug comment controller update");
    System.out.println(">>>> debug comment request path variable : "+map.get("id"));

    System.out.println(">>>> debug comment request params comment : "+map.get("comment"));
    
    int patchFlag = commentService.patch(map);
    if(patchFlag != 0) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // status code : 204 -> 삭제성공이고 응답본문이 없는 코드
  // status code : 404 -> 삭제할 대상이 없는/리소스가 존재하지 않는
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "댓글삭제성공"),
      @ApiResponse(responseCode = "404", description = "댓글삭제실패")
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {

    System.out.println(">>>> debug comment controller delete");
    System.out.println(">>>> debug comment request params id : "+id);
    
    
    int deleteFlag = commentService.delete(id);
    if(deleteFlag != 0) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
