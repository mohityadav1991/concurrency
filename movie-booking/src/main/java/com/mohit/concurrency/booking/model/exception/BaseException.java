package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class BaseException extends Exception {
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
