package com.mohit.concurrent.lecture1;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public abstract class ListSet {

    protected final Node head;

    public ListSet() {
        this.head = new Node(Integer.MIN_VALUE);
        this.head.next = new Node(Integer.MAX_VALUE);
    }

    public Node getHead() {
        return head;
    }

    abstract boolean add(Integer o);

    abstract boolean remove(Integer o);

    abstract boolean contains(Integer o);
}
