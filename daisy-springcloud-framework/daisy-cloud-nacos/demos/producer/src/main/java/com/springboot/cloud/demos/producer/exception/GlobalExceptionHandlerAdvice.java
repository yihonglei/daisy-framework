package com.springboot.cloud.demos.producer.exception;

import com.springboot.cloud.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

}