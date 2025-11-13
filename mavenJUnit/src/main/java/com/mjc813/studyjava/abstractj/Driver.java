package com.mjc813.studyjava.abstractj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    private String name;
    private int age;
    private Vehicle vehicle;
    public Driver(String name, int age, Vehicle vehicle) {
        this.name = name;
        this.age = age;
        this.vehicle = vehicle;
    }
    public void drive() {
        if ( this.getVehicle() instanceof Truck ) {
            System.out.printf("운전수{%s}은 ", this.getName());
            this.getVehicle().move();
            System.out.println();
        } else if ( this.getVehicle() instanceof Bicycle ) {
            System.out.printf("운전수{%s}은 ", this.getName());
            this.getVehicle().move();
            System.out.println();
        }
    }
    public void breakk() {
        if ( this.getVehicle() instanceof Truck ) {
            System.out.printf("운전수{%s}은 ", this.getName());
            this.getVehicle().stop();
            System.out.println();
        } else if ( this.getVehicle() instanceof Bicycle ) {
            System.out.printf("운전수{%s}은 ", this.getName());
            this.getVehicle().stop();
            System.out.println();
        }
    }
}
