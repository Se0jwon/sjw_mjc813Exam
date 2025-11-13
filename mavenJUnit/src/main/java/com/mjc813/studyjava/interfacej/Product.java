package com.mjc813.studyjava.interfacej;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Product {
    private int pno;
    private String name;
    private String company;
    private int price;
}
