package com.mjc813.studyjava.thread;

public class MyLoop extends Thread {
    private MyValue mv;
    public MyLoop(MyValue mv) {
        this.mv = mv;
    }
    @Override
    public void run() {
        for ( ; /*this.mv.getNumber() < 1000*/ true ; ) {
            synchronized (this.mv) {
                System.out.println("myloop : " + this.mv.getNumber());
                this.mv.setNumber(this.mv.getNumber() + 1);
            }
        }
    }
}
