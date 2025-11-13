package com.mjc813.studyjava.interfacej;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Truck implements ITruck {
    private String name;
    private int wheelCount;

    public Truck(String name) {
        this(name, 6);
    }
    public Truck(String name, int wheelCount) {
        this.name = name;
        this.wheelCount = wheelCount;
    }

    @Override
    public String drive() {
        return "{" + this.getWheelCount() + "}개의 바퀴의 트럭{"
                + this.getName() + "}을 운전한다.";
    }

    @Override
    public String breakk() {
        return String.format("트럭{%s}을 정지한다.", this.getName());
    }
}
