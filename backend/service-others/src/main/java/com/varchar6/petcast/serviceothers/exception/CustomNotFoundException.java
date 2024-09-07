package com.varchar6.petcast.serviceothers.exception;

public class CustomNotFoundException extends RuntimeException {

    // 기본 생성자
    public CustomNotFoundException() {
        super();
    }

    // 메시지를 받는 생성자
    public CustomNotFoundException(String message) {
        super(message);
    }

    // 메시지와 원인 예외를 받는 생성자
    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // 원인 예외를 받는 생성자
    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }
}
