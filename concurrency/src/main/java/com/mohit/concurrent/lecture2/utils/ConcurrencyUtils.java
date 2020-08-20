package com.mohit.concurrent.lecture2.utils;

/**
 * @author mohit@interviewbit.com on 20/08/20
 **/
public class ConcurrencyUtils {

    private static final IsolatedManager isolatedManager =
            new IsolatedManager();

    public static void isolated(final Runnable runnable) {
        isolatedManager.acquireAllLocks();
        try {
            runnable.run();
        } finally {
            isolatedManager.releaseAllLocks();
        }
    }

    public static void isolated(final Object obj, final Runnable runnable) {
        Object[] objArr = new Object[1];
        objArr[0] = obj;

        isolatedManager.acquireLocksFor(objArr);
        try {
            runnable.run();
        } finally {
            isolatedManager.releaseLocksFor(objArr);
        }
    }

    public static void isolated(final Object obj1, final Object obj2,
                                final Runnable runnable) {
        Object[] objArr = new Object[2];
        objArr[0] = obj1;
        objArr[1] = obj2;

        isolatedManager.acquireLocksFor(objArr);
        try {
            runnable.run();
        } finally {
            isolatedManager.releaseLocksFor(objArr);
        }
    }
}
