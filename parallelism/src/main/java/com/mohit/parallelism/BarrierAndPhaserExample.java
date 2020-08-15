package com.mohit.parallelism;

import java.util.concurrent.Phaser;

/**
 * @author mohit@interviewbit.com on 15/08/20
 **/
public class BarrierAndPhaserExample {

    private final static int niterations = 400;

    public static void runSequential(final int iterations, final double[] myNew,
                                     final double[] myVal, final int n) {
        double[] next = myNew;
        double[] curr = myVal;

        for (int iter = 0; iter < iterations; iter++) {
            for (int j = 1; j <= n; j++) {
                next[j] = (curr[j - 1] + curr[j + 1]) / 2.0;
            }
            // Swap
            double[] tmp = curr;
            curr = next;
            next = tmp;
        }
    }

    public static void runParallelBarrierInChunks(final int steps, double[] newArr,
                                          double[] oldArr, final int n,
                                          final int tasks) {
        Phaser ph = new Phaser(0);
        ph.bulkRegister(tasks);

        Thread[] threads = new Thread[tasks];

        for (int ii = 0; ii < tasks; ii++) {
            final int i = ii;

            threads[ii] = new Thread(() -> {
                double[] threadPrivateOldArr = oldArr;
                double[] threadPrivateNewArr = newArr;

                final int chunkSize = (n + tasks - 1) / tasks;
                final int left = (i * chunkSize) + 1;
                int right = (left + chunkSize) - 1;
                if (right > n) right = n;

                for (int iter = 0; iter < steps; iter++) {
                    for (int j = left; j <= right; j++) {
                        threadPrivateNewArr[j] = (threadPrivateOldArr[j - 1]
                                + threadPrivateOldArr[j + 1]) / 2.0;
                    }
                    ph.arriveAndAwaitAdvance();

                    double[] temp = threadPrivateNewArr;
                    threadPrivateNewArr = threadPrivateOldArr;
                    threadPrivateOldArr = temp;
                }
            });
            threads[ii].start();
        }

        for (int ii = 0; ii < tasks; ii++) {
            try {
                threads[ii].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void runParallelBarrier(final int steps,  double[] newArr,
                                            double[] oldArr, final int n) {
        Phaser ph = new Phaser(0);
        ph.bulkRegister(n);

        Thread[] threads = new Thread[n];

        for (int ii = 1; ii <= n; ii++) { // forParallelWithThreshold
            int j = ii;
            threads[ii-1] = new Thread(() -> {
                double[] threadPrivateOldArr = oldArr;
                double[] threadPrivateNewArr = newArr;
                for (int iter = 0; iter < steps; iter++) {
                    threadPrivateNewArr[j] = (threadPrivateOldArr[j - 1]
                            + threadPrivateOldArr[j + 1]) / 2.0;
//                    System.out.println("Before: j : " + j + ", step:" + iter + ", threadPrivateNewArr: "
//                            + threadPrivateNewArr + ", threadPrivateOldArr: " + threadPrivateOldArr
//                            + ", oldArr: " + oldArr + ", newArr: " + newArr);
                    ph.arriveAndAwaitAdvance();
//                    System.out.println("After: j : " + j + ", step:" + iter +", threadPrivateNewArr: "
//                            + threadPrivateNewArr + ", threadPrivateOldArr: " + threadPrivateOldArr
//                    + ", oldArr: " + oldArr + ", newArr: " + newArr);
                    double[] temp = threadPrivateNewArr;
                    threadPrivateNewArr = threadPrivateOldArr;
                    threadPrivateOldArr = temp;
                }
            });
            threads[ii-1].start();
        }

        for (int ii = 0; ii < n; ii++) {
            try {
                threads[ii].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void runParallelPhaser(final int iterations, final double[] newArr,
                                         final double[] oldArr, final int n,
                                         final int tasks) {

        Phaser[] phs = new Phaser[tasks];
        for (int i = 0; i < phs.length; i++) {
            phs[i] = new Phaser(1);
        }

        Thread[] threads = new Thread[tasks];

        for (int ii = 0; ii < tasks; ii++) {
            final int i = ii;

            threads[ii] = new Thread(() -> {
                double[] threadPrivateOldArr = oldArr;
                double[] threadPrivateNewArr = newArr;

                for (int iter = 0; iter < iterations; iter++) {
                    final int left = i * (n / tasks) + 1;
                    final int right = (i + 1) * (n / tasks);

                    for (int j = left; j <= right; j++) {
                        threadPrivateNewArr[j] = (threadPrivateOldArr[j - 1]
                                + threadPrivateOldArr[j + 1]) / 2.0;
                    }
//                    System.out.println("Arriving task: " + i);
                    phs[i].arrive();
                    if (i - 1 >= 0) {
//                        System.out.println("Arrived task " + i + " Waiting for " + (i - 1));
                        phs[i - 1].awaitAdvance(iter);
                    }
                    if (i + 1 < tasks) {
//                        System.out.println("Arrived task " + i + " Waiting for " + (i + 1));
                        phs[i + 1].awaitAdvance(iter);
                    }
                    double[] temp = threadPrivateNewArr;
                    threadPrivateNewArr = threadPrivateOldArr;
                    threadPrivateOldArr = temp;
                }
            });
            threads[ii].start();
        }

        for (int ii = 0; ii < tasks; ii++) {
            try {
                threads[ii].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static double[] createArray(final int N, final int iterations) {
        final double[] input = new double[N + 2];
        int index = N + 1;
        while (index > 0) {
            input[index] = 1.0;
            index -= (iterations / 4) != 0 ? (iterations / 4) : 1;
        }
        return input;
    }

    private static void checkAndPrintResult(final double[] parArr, final double[] seqArr) {
        for (int i = 0; i < parArr.length; i++) {
//            System.out.print(parArr[i] + ", ");
            if (parArr[i] != seqArr[i])
                throw new RuntimeException("Mismatch in expect and given output");
        }
    }

    public static void main(String[] args) {
        final int N = 2 * 1024 * 1024;
        final int ntasks = 4;

        double[] myValBar;
//        double[] myNewBar;
        double[] myValChu;
        double[] myNewChu;
        double[] myNewSeq;
        double[] myValSeq;
        double[] myNewPha;
        double[] myValPha;

        long barrierTotalTime = 0;
        long barrierChunkTotalTime = 0;
        long phaserTotalTime = 0;
        long seqTotalTime = 0;

        for (int r = 0; r < 3; r++) {

//            myNewBar = createArray(N, niterations);
            myValBar = createArray(N, niterations);
            myNewChu = createArray(N, niterations);
            myValChu = createArray(N, niterations);
            myNewSeq = createArray(N, niterations);
            myValSeq = createArray(N, niterations);
            myNewPha = createArray(N, niterations);
            myValPha = createArray(N, niterations);

            final long seqStartTime = System.currentTimeMillis();
            runSequential(niterations, myNewSeq, myValSeq, N);
            final long seqEndTime = System.currentTimeMillis();

            final long barrierStartTime = System.currentTimeMillis();
//            runParallelBarrier(niterations, myNewBar, myValBar, N);
            final long barrierEndTime = System.currentTimeMillis();

            final long barrierChunkStartTime = System.currentTimeMillis();
            runParallelBarrierInChunks(niterations, myNewChu, myValChu, N, ntasks);
            final long barrierChunkEndTime = System.currentTimeMillis();

            final long phaserStartTime = System.currentTimeMillis();
            runParallelPhaser(niterations, myNewPha, myValPha, N, ntasks);
            final long phaserEndTime = System.currentTimeMillis();

            if (niterations % 2 != 0) {
//                checkAndPrintResult(myNewBar, myNewSeq);
                checkAndPrintResult(myNewChu, myNewSeq);
                checkAndPrintResult(myNewPha, myNewSeq);
            } else {
//                checkAndPrintResult(myNewBar, myNewSeq);
                checkAndPrintResult(myNewChu, myNewSeq);
                checkAndPrintResult(myNewPha, myNewSeq);
            }

            barrierTotalTime += (barrierEndTime - barrierStartTime);
            seqTotalTime += (seqEndTime - seqStartTime);
            phaserTotalTime += (phaserEndTime - phaserStartTime);
            barrierChunkTotalTime += (barrierChunkEndTime - barrierChunkStartTime);
        }
        System.out.println("seqTime: " + seqTotalTime / 3);
//        System.out.println("barrierTime: " + barrierTotalTime / 3);
        System.out.println("barrierChunkTime: " + barrierChunkTotalTime / 3 + ", q: " + (double) barrierChunkTotalTime / (double) seqTotalTime);
        System.out.println("phaserTime: " + phaserTotalTime / 3 + ", q: " + (double) phaserTotalTime / (double) seqTotalTime);
    }
}
