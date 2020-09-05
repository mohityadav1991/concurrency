package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class ErrorCode {
    private String message;
    private int appCode;

    public ErrorCode(String message, int appCode) {
        this.message = message;
        this.appCode = appCode;
    }
}
