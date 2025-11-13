package com.mjc813.studyjava.thread;

public class SumRunnable implements Runnable {
    private int max;
    private ISumLoop isl;

    public SumRunnable(int max, ISumLoop isl) {
        this.max = max;
        this.isl = isl;
    }

    @Override
    public void run() {
        Long result = this.sum();
        this.isl.print(result);
    }

    private Long sum() {
        Long sum = 0L;
        while( this.max >= 1 ) {
            sum += this.max--;
        }
        return sum;
    }
}
