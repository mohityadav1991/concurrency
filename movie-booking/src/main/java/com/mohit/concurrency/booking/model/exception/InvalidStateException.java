package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class InvalidStateException extends BaseException{

    public InvalidStateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidStateException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
