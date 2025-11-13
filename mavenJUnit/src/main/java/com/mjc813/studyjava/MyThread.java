package com.mjc813.studyjava;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

@Getter
@Setter
public class MyThread extends Thread {
    private boolean active = true;
    @Override
    public void run() {
        try {
            int i = 0;
            while ( i <= 1000 ) {
                if ( this.active ) {
                    System.out.printf("MyThread [%s] Run : [%d]\n", this.getName(), i++);
                } else {
                    Thread.yield();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
