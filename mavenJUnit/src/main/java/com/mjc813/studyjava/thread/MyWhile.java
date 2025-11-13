package com.mjc813.studyjava.thread;

public class MyWhile implements Runnable {
    private MyValue mv;
    public MyWhile(MyValue mv) {
        this.mv = mv;
    }
    @Override
    public void run() {
        int i = 0;
        while ( this.mv.getNumber() <= 1000 ) {
            synchronized (this.mv) {
                System.out.println("mywhile : " + this.mv.getNumber());
                this.mv.setNumber(this.mv.getNumber() + 1);
            }
        }
    }
}
