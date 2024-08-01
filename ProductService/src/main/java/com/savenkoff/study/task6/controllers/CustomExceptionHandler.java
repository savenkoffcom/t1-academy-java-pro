package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.configurations.properties.ApplicationProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApplicationProperties applicationProperties;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        if (body == null)
            problemDetail = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());
        problemDetail.setProperty("errorMessage",ex.getMessage());
        if (applicationProperties.isHttpDebug())
            problemDetail.setProperty("errorStackTrace",ex.getStackTrace());
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Данные не найдены: " + e.getMessage());
        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Данные не найдены: " + e.getMessage());
        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Ошибка в параметрах запроса: " + e.getMessage());
        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
    }
}
