package com.mjc813.studyjava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class SmartPhone {
    private String company;
    private String os;
    private String model;
    private Integer price;
}
