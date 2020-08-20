package com.mohit.concurrent.lecture1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public final class CoarseLists {

    public static final class CoarseList extends ListSet {

        ReentrantLock lock = new ReentrantLock();

        public CoarseList() {
            super();
        }

        @Override
        boolean add(final Integer object) {
            try {
                lock.lock();
                Node pred = this.head;
                Node curr = pred.next;

                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }

                if (object.equals(curr.object)) {
                    return false;
                } else {
                    final Node node = new Node(object);
                    node.next = curr;
                    pred.next = node;
                    return true;
                }
            } finally {
                lock.unlock();
            }

        }

        @Override
        boolean remove(final Integer object) {
            try {
                lock.lock();
                Node pred = this.head;
                Node curr = pred.next;

                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }

                if (object.equals(curr.object)) {
                    pred.next = curr.next;
                    return true;
                } else {
                    return false;
                }
            } finally {
                lock.unlock();
            }
        }

        @Override
        boolean contains(final Integer object) {
            try {
                lock.lock();
                Node pred = this.head;
                Node curr = pred.next;

                while (curr.object.compareTo(object) < 0) {
                    pred = curr;
                    curr = curr.next;
                }
                return object.equals(curr.object);

            } finally {
                lock.unlock();
            }
        }

        public static final class RWCoarseList extends ListSet {

            ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
            Lock r = rwlock.readLock();
            Lock w = rwlock.writeLock();

            public RWCoarseList() {
                super();
            }

            @Override
            boolean add(final Integer object) {
                try {
                    w.lock();
                    Node pred = this.head;
                    Node curr = pred.next;
                    while (curr.object.compareTo(object) < 0) {
                        pred = curr;
                        curr = curr.next;
                    }
                    if (object.equals(curr.object)) {
                        return false;
                    } else {
                        final Node node = new Node(object);
                        node.next = curr;
                        pred.next = node;
                        return true;
                    }
                } finally {
                    w.unlock();
                }
            }

            @Override
            boolean remove(final Integer object) {
                try {
                    w.lock();
                    Node pred = this.head;
                    Node curr = pred.next;

                    while (curr.object.compareTo(object) < 0) {
                        pred = curr;
                        curr = curr.next;
                    }
                    if (object.equals(curr.object)) {
                        pred.next = curr.next;
                        return true;
                    } else {
                        return false;
                    }
                } finally {
                    w.unlock();
                }
            }

            @Override
            boolean contains(final Integer object) {
                try {
                    r.lock();
                    Node pred = this.head;
                    Node curr = pred.next;

                    while (curr.object.compareTo(object) < 0) {
                        pred = curr;
                        curr = curr.next;
                    }
                    return object.equals(curr.object);
                } finally {
                    w.unlock();
                }
            }
        }
    }
}
