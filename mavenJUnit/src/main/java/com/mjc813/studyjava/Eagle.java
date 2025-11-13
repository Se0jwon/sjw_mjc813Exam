package com.mjc813.studyjava;

public class Eagle extends Bird {
    public Eagle(String name, int age, String wings) {
        super(name, age, wings);
    }
    @Override
    public void fly() {
        System.out.printf("%s은 %s 높이 난다.\n"
                , super.getName(), super.getWings());
    }
    @Override
    public void eat(Object feed) {
        if ( feed instanceof Animal ) {
            super.eat(feed);
        }
    }
    @Override
    public Animal reproduce(String newName) {
        return new Eagle(newName, 0, "앵무새날개");
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
