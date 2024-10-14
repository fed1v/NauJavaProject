package ru.fed1v.NauJava.controllers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.fed1v.NauJava.util.Exception;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exception(java.lang.Exception exception) {
        return Exception.create(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Exception exception(ResourceNotFoundException exception) {
        return Exception.create(exception);
    }
}
