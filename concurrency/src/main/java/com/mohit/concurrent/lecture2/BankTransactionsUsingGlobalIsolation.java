package com.mohit.concurrent.lecture2;

import static com.mohit.concurrent.lecture2.utils.ConcurrencyUtils.isolated;

/**
 * @author mohit@interviewbit.com on 20/08/20
 **/
public class BankTransactionsUsingGlobalIsolation extends ThreadSafeBankTransaction {
    @Override
    public void issueTransfer(int amount, Account src, Account dst) {
        isolated(() -> {
            src.performTransfer(amount, dst);
        });
    }
}
