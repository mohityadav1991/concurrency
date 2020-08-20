package com.mohit.concurrent.lecture2;

/**
 * @author mohit@interviewbit.com on 20/08/20
 **/
public abstract class ThreadSafeBankTransaction {
    public abstract void issueTransfer(final int amount, final Account src,
                                       final Account dst);
}
