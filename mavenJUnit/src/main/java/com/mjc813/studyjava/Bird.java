package com.mjc813.studyjava;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bird extends Animal {
    private String wings;

    public Bird(String name, int age, String wings) {
        super(name, age);
        this.wings = wings;
    }
    public void flap() {
        System.out.printf("%s(%d)은 %s 날개짓한다.\n"
                , super.getName(), super.getAge(), this.wings);
    }
    public void fly() {
        System.out.printf("%s은 %s 난다.\n", super.getName(), this.wings);
    }
    @Override
    public Animal reproduce(String newName) {
        return new Bird(newName, 0, this.getWings());
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
