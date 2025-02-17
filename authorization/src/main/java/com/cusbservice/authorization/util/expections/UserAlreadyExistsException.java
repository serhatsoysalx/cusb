package com.cusbservice.authorization.util.expections;

import com.cusbservice.authorization.util.constants.LoginErrorMessage;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException() {
        super(LoginErrorMessage.USER_ALREADY_EXISTS);
    }
}

