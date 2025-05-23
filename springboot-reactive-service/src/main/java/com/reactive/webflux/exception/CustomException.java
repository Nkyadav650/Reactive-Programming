package com.reactive.webflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(IdNotFoundException.class)
    public Mono<ResponseEntity<Map<String,Object>>> handleIdNotFoundException(IdNotFoundException ex){
        Map<String,Object> res=new HashMap<>();
        res.put("error",ex.getMessage());
        res.put("status",HttpStatus.BAD_REQUEST);
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res));
    }
}
