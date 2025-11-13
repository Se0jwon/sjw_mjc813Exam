package com.mjc813.studyjava;

import java.util.LinkedList;
import java.util.List;

public class MyBox<AAA, B extends Number> {
    private List<AAA> arr = new LinkedList<>();
    private B aaa;

    public MyBox(B aaa) {
        this.aaa = aaa;
    }

    public void add(AAA a) {
        this.arr.add(a);
    }

    public AAA get(int n) {
        return this.arr.get(n);
    }
}
