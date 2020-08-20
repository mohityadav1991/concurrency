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

    private TreeSet<Object> createSortedObjects(final Object[] objects) {
        TreeSet<Object> sorted = new TreeSet<Object>(new Comparator<Object>() {
            @Override
            public int compare(final Object o1, final Object o2) {
                return lockIndexFor(o1) - lockIndexFor(o2);
            }
        });

        for (Object obj : objects) {
            sorted.add(obj);
        }

        return sorted;
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
        final TreeSet<Object> sorted = createSortedObjects(objects);

        for (Object obj : sorted) {
            final int lockIndex = lockIndexFor(obj);
            locks[lockIndex].lock();
        }
    }

    public void releaseLocksFor(final Object[] objects) {
        final TreeSet<Object> sorted = createSortedObjects(objects);
        for (Object obj : sorted) {
            final int lockIndex = lockIndexFor(obj);
            locks[lockIndex].unlock();
        }
    }
}
