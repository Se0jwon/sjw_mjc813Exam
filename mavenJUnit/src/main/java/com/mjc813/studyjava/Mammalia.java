package com.mjc813.studyjava;

public class Mammalia extends Animal {
    public Mammalia(String name, int age) {
        super(name, age);
    }
    void feedingMilk() {
        System.out.printf("%s 젖을 먹인다.\n", this.getName());
    }
    @Override
    public void eat(Object feed) {
        if (feed instanceof String) {
            super.eat(feed);
        }
    }
    @Override
    public Animal reproduce(String newName) {
        return new Mammalia(newName, 0);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
