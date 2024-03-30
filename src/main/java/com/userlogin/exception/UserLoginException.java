package com.userlogin.exception;

public interface UserLoginException {


    boolean getSuccess();
    String getMessage();

    int getHttpStatusCode();
}
