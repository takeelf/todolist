package com.wonho.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = DefineException.IS_COMPLETE_ERROR)
public class IsCompleteException extends RuntimeException {
    private static final long serialVersionUID = 6212340563029991181L;
    public IsCompleteException(String message) {
        super(message);
    }
}
