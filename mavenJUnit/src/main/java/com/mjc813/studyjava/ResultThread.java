package com.mjc813.studyjava;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ResultThread extends Thread {
    LinkedList<String> vs = new LinkedList<>();
    IResultExecute ire = null;
    int nmax = 0;
    public ResultThread(IResultExecute ire, int nmax) {
        this.ire = ire;
        this.nmax = nmax;
    }
    @Override
    public void run() {
        int sum = 0;
        for( int i = 0; i < this.nmax; i++ ) {
            sum += i;
            this.vs.add("");
        }
        if ( this.ire != null ) {
            this.ire.print(sum);
        }
    }
}
