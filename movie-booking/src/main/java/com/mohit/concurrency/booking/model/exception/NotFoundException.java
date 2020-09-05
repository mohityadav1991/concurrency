package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class NotFoundException extends BaseException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
