package com.inspire.lgcns_mybatis.features.common.aop;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// BindingResult를 대신해서 사용
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(exception = MethodArgumentNotValidException.class)
  public ResponseEntity<?> validationHandler(MethodArgumentNotValidException e) {
    System.out.println(">>>> debug GlobalExceptionHandler MethodArgumentNotValidException");

    // 연산에 대한 로그출력 : peek()
    Map<String, String> errMap =e.getBindingResult().getFieldErrors()
      .stream()
      .peek( fieldError -> {
        System.out.println(fieldError.getField()+"\t"+fieldError.getDefaultMessage());
      })
      .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errMap);
  } 

  /*
  결과 
  >>>> debug GlobalExceptionHandler MethodArgumentNotValidException
  password        크기가 8에서 20 사이여야 합니다
  name    이름은 필수입력 항목입니다.
  password        비밀번호는 필수입력 항목입니다.
  */
}
