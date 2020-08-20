package com.mohit.concurrent.lecture1.live;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i< 50; i++) {
            final int index = i;
            Thread t1 = new Thread(() -> {
                System.out.println("Run : " + index + " Inside Thread0: " + Thread.currentThread().getName());
            });
            t1.start();
            t1.join();
            System.out.println("Run : " + index + " Inside Thread1: " + Thread.currentThread().getName());
            System.out.println("Run : " + index + " Inside Thread2: " + Thread.currentThread().getName());
            System.out.println("Run : " + index + " ----------------------------------------------------");
        }
    }
}
