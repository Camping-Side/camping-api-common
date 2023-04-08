package com.camping.common.exception;


public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super("적절하지 않은 요청입니다.");
    }

}
