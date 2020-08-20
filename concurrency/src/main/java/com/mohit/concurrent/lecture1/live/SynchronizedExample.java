package com.mohit.concurrent.lecture1.live;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public class SynchronizedExample {

    static class Counter{

        long count = 0;

        public synchronized void add(long value){
            this.count += value;
        }
    }

    public static class CounterThread extends Thread{

        protected Counter counter = null;

        public CounterThread(Counter counter){
            this.counter = counter;
        }

        public void run() {
            for(int i=0; i<1000; i++){
                counter.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int sum = 0;
        for(int i = 0; i < 100; i++) {
            Counter counter = new Counter();
            Thread threadA = new CounterThread(counter);
            Thread threadB = new CounterThread(counter);

            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            sum += counter.count;
        }
        System.out.println(sum / 100);
    }
}

// 997735, 991552,
// 999000 = 999 * 1000