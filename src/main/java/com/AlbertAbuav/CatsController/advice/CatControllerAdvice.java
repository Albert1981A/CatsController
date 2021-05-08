package com.AlbertAbuav.CatsController.advice;

import com.AlbertAbuav.CatsController.exception.CatsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CatControllerAdvice {

    @ExceptionHandler(value = {CatsException.class})
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("LOL", e.getMessage());
    }
}
