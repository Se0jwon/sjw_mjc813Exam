package com.mjc813.studyjava;

public class TestMain extends Thread implements ITestMain {
    @Override
    public void run() {
        this.myRun1();
        this.myRun2(this);
    }

    private void myRun1() {
        for ( int i = 1; i <= 100000; i++ ) {
            System.out.printf("myRun1 : %d\n", i);
        }
    }

    private void myRun2(ITestMain im) {
        for ( int i = 1; i <= 100000; i++ ) {
            im.print(String.format("myRun2 : %d", i));
        }
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        TestMain tm = new TestMain();
        tm.start();
    }
}
