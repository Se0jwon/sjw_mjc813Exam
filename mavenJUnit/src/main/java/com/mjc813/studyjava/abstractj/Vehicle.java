package com.mjc813.studyjava.abstractj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private int wheelCount;
    public Vehicle(int wheelCount) {
        this.wheelCount = wheelCount;
    }
    public abstract void move();
    public abstract void stop();
}
