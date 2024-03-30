package com.userlogin.config;

import com.userlogin.exception.AuthCustomException;
import com.userlogin.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class UserLoginControllerAdvice {

    @ExceptionHandler(AuthCustomException.class)
    public ResponseEntity<ResponseModel> userLoginRuntimeException(AuthCustomException authCustomException) {
        return ResponseEntity
                .status(authCustomException.getHttpStatusCode())
                .body(new ResponseModel(authCustomException.getSuccess(),
                        authCustomException.getMessage(),
                        authCustomException.getHttpStatusCode()));
    }
}
