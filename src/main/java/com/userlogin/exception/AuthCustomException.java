package com.userlogin.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthCustomException extends RuntimeException implements UserLoginException {

    private final boolean success;

    private final int httpStatusCode;

    public AuthCustomException(boolean success,String message, int httpStatusCode) {
        super(message);
        this.success = success;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public boolean getSuccess() {
        return this.success;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
