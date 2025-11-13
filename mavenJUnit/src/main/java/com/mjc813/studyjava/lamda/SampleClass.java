package com.mjc813.studyjava.lamda;

public class SampleClass implements ISample{
    private Integer c;
    private Long d;
    public SampleClass(Integer c, Long d) {
        this.c = c;
        this.d = d;
    }
    public String doit(ISample inter) {
        return inter.sample(this.c, this.d);
    }

    @Override
    public String sample(Integer a, Long b) {
        return "aaaaaaaaaaaaa";
    }
}
