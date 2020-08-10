package com.mohit.parallelism;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public final class SumArray {
    private SumArray() {
    }

    protected static double seqArraySum(final long[] input) {
        long sum = 0;
        final double startTime = System.currentTimeMillis();
        // Compute sum of array elements
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        final double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        System.out.println(String.format("took: %.0fms, sum: %d: seqArraySum", duration, sum));
        return sum;
    }

    private static int getChunkSize(final int nChunks, final int nElements) {
        // Integer ceil
        return (nElements + nChunks - 1) / nChunks;
    }

    private static int getChunkStartInclusive(final int chunk,
            final int nChunks, final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        return chunk * chunkSize;
    }

    private static int getChunkEndExclusive(final int chunk, final int nChunks,
            final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        final int end = (chunk + 1) * chunkSize;
        if (end > nElements) {
            return nElements;
        } else {
            return end;
        }
    }

    private static class SumArrayTask extends RecursiveAction {
        private final int startIndexInclusive;
        private final int endIndexExclusive;
        private final long[] input;
        private long value;
        private String name;

        SumArrayTask(final int setStartIndexInclusive,
                     final int setEndIndexExclusive, final long[] setInput, final String name) {
            this.startIndexInclusive = setStartIndexInclusive;
            this.endIndexExclusive = setEndIndexExclusive;
            this.input = setInput;
            this.name = name;
        }

        public long getValue() {
            return value;
        }

        @Override
        protected void compute() {
            System.out.println(String.format("Running %s compute task for SumArrayTask on Thread: %s",name, Thread.currentThread().getName()));
            for(int i = startIndexInclusive; i < endIndexExclusive; i++)
                value += input[i];
        }
    }

    private static class SumArrayFutureTask extends RecursiveTask<Long> {
        private final int startIndexInclusive;
        private final int endIndexExclusive;
        private final long[] input;
        private long value;
        private String name;

        SumArrayFutureTask(final int setStartIndexInclusive,
                           final int setEndIndexExclusive, final long[] setInput, final String name) {
            this.startIndexInclusive = setStartIndexInclusive;
            this.endIndexExclusive = setEndIndexExclusive;
            this.input = setInput;
            this.name = name;
        }

        public double getValue() {
            return value;
        }

        @Override
        protected Long compute() {
//            System.out.println("Running compute task for SumArrayFutureTask on Thread: " + Thread.currentThread().getName());
            for(int i = startIndexInclusive; i < endIndexExclusive; i++)
                value += input[i];
            return value;
        }
    }

    protected static double parArraySum(final long[] input) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
        assert input.length % 2 == 0;
        final double startTime = System.currentTimeMillis();
        int mid = input.length / 2;
        SumArrayTask sumTaskLeft = new SumArrayTask(0, mid, input, "sumTaskLeft");
        SumArrayTask sumTaskRight = new SumArrayTask(mid, input.length, input, "sumTaskRight");
        sumTaskLeft.fork();
        sumTaskRight.compute();
        sumTaskLeft.join();
        long result = sumTaskLeft.getValue() + sumTaskRight.getValue();
        final double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        System.out.println(String.format("took: %.0fms, sum: %d: parArraySum ", duration, result));
        return result;
    }

    protected static double parManyTaskArraySum(final long[] input,
            final int numTasks) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "40");
        final double startTime = System.currentTimeMillis();
        int taskCount = numTasks;
        if(numTasks > input.length)
            taskCount = input.length;
        long sum = 0;
        SumArrayTask[] sumTaskList = new SumArrayTask[taskCount];
        for(int i = 0; i<taskCount; i++) {
            SumArrayTask arraySumTask = new SumArrayTask(getChunkStartInclusive(i, taskCount, input.length), getChunkEndExclusive(i, taskCount, input.length), input, String.valueOf(i));
            sumTaskList[i] = arraySumTask;
        }
        System.out.println("ForkJoinPool size : " + ForkJoinPool.commonPool().getPoolSize());
        ForkJoinTask.invokeAll(sumTaskList);
        System.out.println("ForkJoinPool size : " + ForkJoinPool.commonPool().getPoolSize());
        for (SumArrayTask sumTask : sumTaskList) {
            sum += sumTask.getValue();
        }
        final double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        System.out.println(String.format("took: %.0fms, sum: %d, numTasks: %d: parManyTaskArraySum ", duration, sum, taskCount));
        return sum;
    }

    protected static double parManyTaskArrayFutureSum(final long[] input,
                                                final int numTasks) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        final double startTime = System.currentTimeMillis();
        int taskCount = numTasks;
        if(numTasks > input.length)
            taskCount = input.length;
        long sum = 0;
        SumArrayFutureTask[] sumTaskList = new SumArrayFutureTask[taskCount];
        for(int i = 0; i<taskCount; i++) {
            SumArrayFutureTask arraySumTask = new SumArrayFutureTask(getChunkStartInclusive(i, taskCount, input.length), getChunkEndExclusive(i, taskCount, input.length), input, String.valueOf(i));
            sumTaskList[i] = arraySumTask;
        }
        ForkJoinTask.invokeAll(sumTaskList);
        for (SumArrayFutureTask sumTask : sumTaskList) {
            sum += sumTask.join();
        }
        final double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);
        System.out.println(String.format("took: %.0fms, sum: %d, numTasks: %d: parManyTaskArrayFutureSum ", duration, sum, taskCount));
        return sum;
    }

    // RUNNER CODE
    private static long[] createArray(final int N) {
        final long[] input = new long[N];
        final Random rand = new Random(314);

        for (int i = 0; i < N; i++) {
            input[i] = rand.nextInt(100);
            if(i%2==0)
                input[i] = -input[i];
        }
        return input;
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 100000;
        final long[] input = SumArray.createArray(N);
        for (int i =0; i < 1; i++) {
//            SumArray.seqArraySum(input);
//            SumArray.parArraySum(input);
            SumArray.parManyTaskArraySum(input, 200);
//            SumArray.parManyTaskArraySum(input, N);
//            SumArray.parManyTaskArraySum(input, N/10000);
//            SumArray.parManyTaskArrayFutureSum(input, 10);
//            SumArray.parManyTaskArrayFutureSum(input, N);
//            SumArray.parManyTaskArraySum(input, N/10000);
            System.out.println();
            Thread.sleep(1000);
        }
    }
}
