package com.savenkoff.study.task6.controllers;

import com.savenkoff.study.task6.exceptions.EmptyObjectException;
import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ProblemDetail problemDetail = (ProblemDetail) body;
        if (body == null)
            problemDetail = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());
        problemDetail.setProperty("errorMessage",ex.getMessage());
//        problemDetail.setProperty("errorStackTrace",ex.getStackTrace()); // TODO: Включать через application.yml
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }

    // Унифицированный проброс ошибок с продуктового сервиса
    /* Пример ответа об ошибке:
    {
        "type": "about:blank",
        "title": "Not Found",
        "status": 404,
        "detail": "Ошибка взаимодейтсвия с продуктовым сервисом",
        "instance": "/payer-app/api/v1/products/user/70",
        "errorMessage": "404 : \"{\"type\":\"about:blank\",\"title\":\"Not Found\",\"status\":404,\"detail\":\"No value present\",\"instance\":\"/products-app/api/v1/products/user/70\",\"errorMessage\":\"No value present\"}\""
    }
    */
    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(),"Ошибка взаимодействия с продуктовым сервисом");
        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),e.getStatusCode(),request);
    }

    @ExceptionHandler({EmptyObjectException.class})
    public ResponseEntity<Object> handleEmptyObjectException(EmptyObjectException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"Данные не найдены");
        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
}
