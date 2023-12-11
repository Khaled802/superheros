package com.example.superheros.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.example.superheros.dto.MessageDto;
import com.example.superheros.dto.MessageValidationDto;
import com.example.superheros.dto.ValidationItemDto;
import com.example.superheros.exception.CustomeWebException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomeWebException.class)
    public ResponseEntity<MessageDto> handleCustomeWebException(CustomeWebException customeWebException) {
        return ResponseEntity.status(customeWebException.getStatus())
                .body(new MessageDto(customeWebException.getMessage(), customeWebException.getMessageList()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageValidationDto("validation error",
                        methodArgumentNotValidException.getFieldErrors().stream()
                                .map((fieldError) -> new ValidationItemDto(
                                        fieldError.getField(),
                                        ObjectUtils.nullSafeToString(fieldError.getRejectedValue()),
                                        fieldError.getDefaultMessage()))
                                .toList()));
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        if (ex instanceof CustomeWebException) {
            CustomeWebException customeWebException = (CustomeWebException) ex;
            return ResponseEntity.status(customeWebException.getStatus())
                    .body(new MessageDto(customeWebException.getMessage(), customeWebException.getMessageList()));
        }
        log.error(ex.toString());
        return new ResponseEntity<Object>(
                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
