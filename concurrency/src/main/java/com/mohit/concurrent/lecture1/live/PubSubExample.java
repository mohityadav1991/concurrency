package com.mohit.concurrent.lecture1.live;

import java.util.Random;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public class PubSubExample {
    static class Message {
        private String message;
        private boolean empty = true; // false

        public synchronized String take() {
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            empty = true;
            notifyAll();
            return message;
        }

        public  void put(String message) {
            synchronized (this) {
                while (!empty) {
                    try {
                        wait();
                        System.out.println("hello");
                    } catch (InterruptedException e) {
                    }
                }
                empty = false;
                this.message = message;
            notifyAll();
            }
        }
    }

    static class Producer implements Runnable {
        private Message message;

        public Producer(Message message) {
            this.message = message;
        }

        public void run() {
            String importantInfo[] = {
                    "Scaler",
                    "Plus",
                    "June-2020",
                    "Batch"
            };
            Random random = new Random();

            for (int i = 0; i < importantInfo.length; i++) {
                message.put(importantInfo[i]);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                }
            }
            message.put("DONE");
        }
    }

    static class Consumer implements Runnable {
        private Message message;

        public Consumer(Message message) {
            this.message = message;
        }

        public void run() {
            Random random = new Random();
            for (String msg = message.take(); !msg.equals("DONE"); msg = message.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", msg);
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Message drop = new Message();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
