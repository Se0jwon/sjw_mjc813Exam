package com.mjc813.studyjava.abstractj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Truck extends Vehicle {
    private String name;
    private int ton;
    public Truck(String name, int ton, int wheelCount) {
        super(wheelCount);
        this.name = name;
        this.ton = ton;
    }

    @Override
    public void move() {
        System.out.printf("{%d}개의 바퀴의 트럭{%s}을 운전한다."
                , this.getWheelCount(), this.getName());
    }

    @Override
    public void stop() {
        System.out.printf("트럭{%s}을 정지한다."
                , this.getName());
    }
}
