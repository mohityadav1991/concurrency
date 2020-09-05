package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class InvalidSearchRequestException extends BaseException {
    public InvalidSearchRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidSearchRequestException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
