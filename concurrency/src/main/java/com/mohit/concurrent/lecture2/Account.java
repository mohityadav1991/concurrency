package com.mohit.concurrent.lecture2;

/**
 * @author mohit@interviewbit.com on 20/08/20
 **/
public class Account {
    private int id;
    private int balance;

    public Account(final int setId, final int setStartingBalance) {
        this.id = setId;
        this.balance = setStartingBalance;
    }

    public int balance() {
        return balance;
    }

    public boolean withdraw(final int amount) {
        if (amount > 0 && amount < balance) {
            balance -= amount;
            return true;
        }
        return false;
    }


    public boolean deposit(final int amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    private static void busyWork(final int srcID, final int destID) {
        for (int i = 0; i < srcID * 100; i++) ;
        for (int i = 0; i < destID * 100; i++) ;
    }

    public boolean performTransfer(final int amount, final Account target) {
        final boolean withdrawSuccess = withdraw(amount);
        busyWork(this.id, target.id);
        if (withdrawSuccess) {
            target.deposit(amount);
        }
        return withdrawSuccess;
    }
}
