package com.mjc813.studyjava.thread;

import com.mjc813.studyjava.MyThread;

public class Main {
    public static int count = 0;
    public static void main(String[] args) {
        System.out.println("Main");
        MyValue mv = new MyValue();
        MyLoop ml = new MyLoop(mv);
        Thread mw = new Thread(new MyWhile(mv));
        ml.setDaemon(true);
        ml.start();
        mw.start();
        for ( int i = 0; i < 100; i++ ) {
            System.out.println("main : " + i);
        }
    }
}
