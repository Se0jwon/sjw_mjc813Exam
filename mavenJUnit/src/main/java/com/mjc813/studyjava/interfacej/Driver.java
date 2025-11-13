package com.mjc813.studyjava.interfacej;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Driver {
    private String name;
    private int age;
    private IVehicle ivehicle;

    public void drive() {
        System.out.printf("운전수{%s}은 %s\n"
                , this.getName()
                , this.getIvehicle().drive()
        );
    }

    public void breakk() {
        String str = String.format("운전수{%s}은 ", this.getName());
        str += this.getIvehicle().breakk();
        System.out.println(str);
    }
}
