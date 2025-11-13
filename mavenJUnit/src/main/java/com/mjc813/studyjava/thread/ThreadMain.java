package com.mjc813.studyjava.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ThreadMain {
    public static void main(String[] args) {
        Long ms = System.currentTimeMillis();
//        for ( int i = 1; i <= 100000; i++ ) {
//            final int idx = i;
//            Thread sl = new Thread(new SumLoop(idx, num -> System.out.printf("result = %d\n", num)));
//            sl.start();
//        }
        ms = System.currentTimeMillis() - ms;
        System.out.printf("totalMills = %d\n", ms);
    }
}
