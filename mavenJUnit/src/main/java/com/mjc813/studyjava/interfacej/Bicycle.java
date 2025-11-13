package com.mjc813.studyjava.interfacej;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bicycle implements IBicycle {
    private String name;
    private int wheelCount;

    @Override
    public String drive() {
        return String.format("{%d}개의 바퀴의 자전거{%s}을 이동한다."
                , this.getWheelCount(), this.getName());
    }

    @Override
    public String breakk() {
        return String.format("자전거{%s}을 정지한다.", this.getName());
    }
}
