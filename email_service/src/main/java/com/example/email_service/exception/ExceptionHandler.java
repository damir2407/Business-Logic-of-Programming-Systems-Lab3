package com.example.email_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Slf4j
@Service
public class ExceptionHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable t) {
        log.error(t.getCause().getMessage());
    }
}
