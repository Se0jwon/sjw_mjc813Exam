package com.mjc813.studyjava.interfacej;

public class Calcul {
    public Double a;
    public Double b;
    public Calcul(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

//    public Double calculaor(ICalculator ic) {
//        return ic.calcul(this.a, this.b);
//    }
    public String getSSS(ICalculator ic) {
        return ic.getS();
    }
}
