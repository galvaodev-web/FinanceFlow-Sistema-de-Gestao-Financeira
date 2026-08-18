package com.financeflow.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(RecursoNaoEncontradoException.class) ResponseEntity<ApiError> notFound(RuntimeException e){return response(HttpStatus.NOT_FOUND,e.getMessage(),Map.of());}
  @ExceptionHandler(RegraNegocioException.class) ResponseEntity<ApiError> business(RuntimeException e){return response(HttpStatus.BAD_REQUEST,e.getMessage(),Map.of());}
  @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<ApiError> conflict(){return response(HttpStatus.CONFLICT,"O registro já existe ou está em uso.",Map.of());}
  @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ApiError> validation(MethodArgumentNotValidException e){
    Map<String,String> fields=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->fields.putIfAbsent(x.getField(),x.getDefaultMessage()));
    return response(HttpStatus.BAD_REQUEST,"Revise os campos informados.",fields);
  }
  private ResponseEntity<ApiError> response(HttpStatus status,String message,Map<String,String> fields){return ResponseEntity.status(status).body(new ApiError(LocalDateTime.now(),status.value(),status.getReasonPhrase(),message,fields));}
}

