package com.mjc813.studyjava.stream;

import com.mjc813.studyjava.Animal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

public class StreamSample {
    List<String> list = new LinkedList<>();
    List<Animal> animalList = new ArrayList<>();

    public static void main(String[] args) {
        StreamSample sts = new StreamSample();
        sts.initRandom(100);
        sts.print();
        sts.streamPrint();

        sts.animalPrint();
    }

    private void initRandom(int max) {
        for ( int i = 0; i < max; i++ ) {
            this.list.add( ((Double)(Math.random() * 1000)).toString() );
            this.animalList.add(
                    new Animal(((Double)(Math.random() * 1000)).toString()
                            , Integer.parseInt(String.valueOf(((int)(Math.random() * 30) + 1))))
            );
        }
    }

    private void print() {
        for( String s : this.list ) {
            System.out.printf("print [%s] : %s\n"
                    ,Thread.currentThread().getName()
                    , s);
        }
    }

    private void streamPrint() {
        this.list.stream()
                .forEach( (m) -> {
                    System.out.printf("streamPrint [%s] : %s\n"
                        ,Thread.currentThread().getName()
                        , m);
                } );
    }

    private void animalPrint() {
        OptionalInt oNum = this.animalList.stream()
                //.mapToInt((item) -> item.getAge())
                .mapToInt(Animal::getAge)
                .filter( x -> x > 10 )
                .findFirst();
        System.out.println(oNum.orElse(0));
    }
}
