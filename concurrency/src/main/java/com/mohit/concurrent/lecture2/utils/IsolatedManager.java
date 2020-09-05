package com.mohit.concurrent.lecture2.utils;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mohit@interviewbit.com on 20/08/20
 **/
public class IsolatedManager {
    private final int nLocks = 64;
    private final Lock[] locks = new Lock[nLocks];

    public IsolatedManager() {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    private int lockIndexFor(final Object obj) {
        return Math.abs(obj.hashCode()) % nLocks;
    }

    public void acquireAllLocks() {
        for (int i = 0; i < locks.length; i++) {
            locks[i].lock();
        }
    }

    public void releaseAllLocks() {
        for (int i = locks.length - 1; i >= 0; i--) {
            locks[i].unlock();
        }
    }

    public void acquireLocksFor(final Object[] objects) {
        for (Object obj : objects) {
            final int lockIndex = lockIndexFor(obj);
            locks[lockIndex].lock();
        }
    }

    public void releaseLocksFor(final Object[] objects) {
        for (Object obj : objects) {
            final int lockIndex = lockIndexFor(obj);
            locks[lockIndex].unlock();
        }
    }
}
