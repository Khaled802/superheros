package com.example.superheros.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.superheros.dto.MessageDto;
import com.example.superheros.dto.MessageValidationDto;
import com.example.superheros.dto.ValidationItemDto;
import com.example.superheros.exception.CustomeWebException;

@ControllerAdvice
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
                .body(new MessageValidationDto("validation error", methodArgumentNotValidException.getFieldErrors().stream()
                        .map((fieldError) -> new ValidationItemDto(
                                fieldError.getField(),
                                ObjectUtils.nullSafeToString(fieldError.getRejectedValue()) ,
                                fieldError.getDefaultMessage())).toList()
                        ));
    }
}
