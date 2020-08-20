package com.mohit.concurrent.lecture1;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public final class SyncList extends ListSet {

    public SyncList() {
        super();
    }


    public synchronized boolean contains(final Integer object) {
        Node pred = this.head;
        Node curr = pred.next;

        while (curr.object.compareTo(object) < 0) {
            pred = curr;
            curr = curr.next;
        }
        return object.equals(curr.object);
    }

    public synchronized boolean add(final Integer object) {
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
    }

    public synchronized boolean remove(final Integer object) {
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
    }
}
