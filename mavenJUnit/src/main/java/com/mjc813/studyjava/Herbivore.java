package com.mjc813.studyjava;

public class Herbivore extends Mammalia {
    public Herbivore(String name, int age) {
        super(name, age);
    }
    @Override
    public Animal reproduce(String newName) {
        return new Herbivore(newName, 0);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
