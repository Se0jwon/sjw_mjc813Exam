package com.mjc813.studyjava;

public class Carnivore extends Mammalia {
    public Carnivore(String name, int age) {
        super(name, age);
    }
    @Override
    public void eat(Object feed) {
        if (feed instanceof Herbivore) {
            System.out.printf("%s은 %s를 먹는다.\n", this.getName(), feed);
        }
    }
    @Override
    public Animal reproduce(String newName) {
        return new Carnivore(newName, 0);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
