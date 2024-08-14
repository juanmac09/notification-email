package com.example.notificationsemails.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type `MethodArgumentNotValidException` that occur during
     * the validation of request parameters in controller methods.
     *
     * This method is annotated with `@ExceptionHandler`, meaning it will intercept
     * exceptions of type `MethodArgumentNotValidException` thrown within the controller
     * and process them accordingly. When such an exception is caught, the method extracts
     * the error message associated with the invalid argument and returns a `ProblemDetail`
     * object containing an HTTP status code of 400 (Bad Request) and the validation error message.
     *
     * @param e the `MethodArgumentNotValidException` thrown when validation fails.
     * @return a `ProblemDetail` object containing the HTTP status code and the error message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleNotificationException(MethodArgumentNotValidException e){
        ProblemDetail errorDetail  = null;

        return errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
