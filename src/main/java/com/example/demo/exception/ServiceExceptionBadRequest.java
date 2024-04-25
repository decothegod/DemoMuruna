package com.example.demo.exception;

public class ServiceExceptionBadRequest extends RuntimeException {
    public ServiceExceptionBadRequest(String msg) {
        super(msg);
    }
}
