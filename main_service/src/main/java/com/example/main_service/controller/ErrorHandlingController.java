package com.example.main_service.controller;


import javax.jms.JMSException;
import javax.validation.ConstraintViolationException;

import com.example.data.dto.response.ErrorResponse;
import com.example.data.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ErrorHandlingController {

    @ExceptionHandler({PermissionDeniedException.class,
            TokenHasExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenException(RuntimeException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            IllegalPageParametersException.class,
            BadCredentialsException.class,
            ResourceAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(RuntimeException e) {
        return new ErrorResponse(e.getMessage());

    }

    @ExceptionHandler({UsernameNotFoundException.class,
            ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(RuntimeException e) {
        return new ErrorResponse(e.getMessage());
    }


    @ExceptionHandler({JMSException.class})
    public void handleJMSException(Exception e) {
        log.error(e.getCause().getMessage());
    }


}
