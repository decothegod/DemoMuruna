package com.example.demo.exception;

public class ServiceExceptionUnauthorized extends RuntimeException {
    public ServiceExceptionUnauthorized(String msg) {
        super(msg);
    }
}
