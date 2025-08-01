package com.example.paymentService.exception;


public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
