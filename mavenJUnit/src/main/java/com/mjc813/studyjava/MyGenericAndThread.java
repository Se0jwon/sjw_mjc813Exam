package com.mjc813.studyjava;

import java.math.BigDecimal;

public class MyGenericAndThread<P extends Number> extends Thread {
    private Integer valThread = null;
    public MyGenericAndThread(Integer val) {
        this.valThread = val;
    }
    private BigDecimal add(P a, P b) {
        BigDecimal bda = new BigDecimal(a.toString());
        BigDecimal bdb = new BigDecimal(b.toString());
        BigDecimal bd = bda.add(bdb);
        return bd;
    }
    private BigDecimal sub(P a, P b) {
        BigDecimal bda = new BigDecimal(a.toString());
        BigDecimal bdb = new BigDecimal(b.toString());
        BigDecimal bd = bda.subtract(bdb);
        return bd;
    }
    private Long sumOfLoop(Integer a) {
        Integer bda = a;
        Long sum = 0L;
        while( bda > 0 ) {
            sum += bda;
            bda--;
        }
        return sum;
    }

    @Override
    public void run() {
        Long res = this.sumOfLoop(this.valThread);
        System.out.printf("sum : %d\n", res);
    }

    public static void main(String[] args) {
        MyGenericAndThread<Double> cal2 = new MyGenericAndThread<>(999999999);
        BigDecimal bd1 = cal2.add(53.2, 392.1);
        BigDecimal bd2 = cal2.sub(933.1, 33.1);
        System.out.printf("%s, %s\n", bd1.toString(), bd2.toString());
        cal2.start();
//        Long res = cal2.sumOfLoop(cal2.valThread);
//        System.out.printf("sum : %d\n", res);

        MyGenericAndThread<Integer> cal = new MyGenericAndThread<>(878787878);
        BigDecimal i = cal.add(10, 20);
        BigDecimal j = cal.sub(30, 5);
        System.out.printf("%s, %s\n", i.toString(), j.toString());
        cal.start();
    }
}
