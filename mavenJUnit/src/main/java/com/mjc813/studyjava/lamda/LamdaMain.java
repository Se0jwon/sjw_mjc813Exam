package com.mjc813.studyjava.lamda;

public class LamdaMain {
    public static void main(String[] args) {
        SampleClass sc = new SampleClass( 5, 567800L );
        System.out.println(sc.doit(sc::sample));
        System.out.println(sc.doit((x, y) -> ((Long)(x * y / 10)).toString()));
    }
}
