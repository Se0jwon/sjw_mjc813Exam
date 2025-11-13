package com.mjc813.studyjava;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void eat(Object feed) {
        System.out.printf("%s은 %s를 먹는다.\n", this.name, feed);
    }
    public void sleep() {
        System.out.printf("%s은 잔다.\n", this.name);
    }
    public void move() {
        System.out.printf("%s은 이동한다.\n", this.name);
    }
    public Animal reproduce(String newName) {
        return new Animal(newName, 0);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
