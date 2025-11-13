package com.mjc813.studyjava.abstractj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bicycle extends Vehicle {
    private String name;

    public Bicycle(String name, int wheelCount) {
        super(wheelCount);
        this.name = name;
    }
    @Override
    public void move() {
        System.out.printf("{%d}개의 바퀴의 자전거{%s}을 이동한다."
                , this.getWheelCount(), this.getName());
    }

    @Override
    public void stop() {
        System.out.printf("자전거{%s}을 정지한다."
                , this.getName());
    }
}
