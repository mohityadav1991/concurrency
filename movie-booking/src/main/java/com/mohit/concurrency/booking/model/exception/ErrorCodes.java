package com.mohit.concurrency.booking.model.exception;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class ErrorCodes {
    public static final ErrorCode E100 = new ErrorCode("Need to specify value of at least 1 filter criterion: Title, Genre, Language", 100);
    public static final ErrorCode E101 = new ErrorCode("No Movie found for given input", 101);
    public static final ErrorCode E102 = new ErrorCode("Lock the seats before proceeding with the booking", 102);
    public static final ErrorCode E103 = new ErrorCode("Corresponding lock seats entry not found", 102);
}
