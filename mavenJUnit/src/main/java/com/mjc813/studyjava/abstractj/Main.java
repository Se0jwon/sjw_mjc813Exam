package com.mjc813.studyjava.abstractj;

public class Main {
    public static void main(String[] args) {
        Truck truck = new Truck("벤츠트럭", 40, 12);
        Bicycle bicycle = new Bicycle("삼천리자전거", 2);
        Driver driver01 = new Driver("운전수01", 30, truck);
        driver01.drive();
        driver01.breakk();

        Driver driver02 = new Driver("운전수02", 26, bicycle);
        driver02.drive();
        driver02.breakk();
    }
}
