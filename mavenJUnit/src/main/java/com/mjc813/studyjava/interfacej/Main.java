package com.mjc813.studyjava.interfacej;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Truck truck01 = new Truck("현대트럭1.4톤", 6);
        Truck truck02 = new Truck("벤츠트럭32톤", 32);

        Bicycle bicycle01 = new Bicycle("삼천리L320", 2);
        Bicycle bicycle02 = new Bicycle("중국산어린이자전거", 3);

        Driver lee = new Driver("이순신", 35, truck01);
        lee.drive();
        lee.breakk();
        lee.setIvehicle(truck02);
        lee.drive();
        lee.breakk();

        Driver choi = new Driver("최원철", 55, bicycle01);
        choi.drive();
        choi.breakk();
        choi.setIvehicle(bicycle02);
        choi.drive();
        choi.breakk();

        Calcul cc = new Calcul(60.0, 30.3);
//        Double d = cc.calculaor((a, b) -> 10.0);
        String s = cc.getSSS(new Ccc());
        String s1 = cc.getSSS(() -> "aabde");
        System.out.println("calculator : " + s);
        System.out.println("calculator : " + s1);
        System.out.println("calculator : " + cc.a);
        System.out.println("calculator : " + cc.b);

        NumProc np = new NumProc();
        np.printHuman((a, b) -> {
            System.out.printf("{%s} 님의 나이는 {%d} 입니다.\n", a, b);
        }, "홍길동", 30);
        np.printCar("그랜저", 7, (m, n) -> {
            System.out.printf("자동자 {%s} 의 년식은 {%d} 입니다.\n", m, n);
        });

        Long res = loopStatic((list) -> {
            long l = 0;
            for(Integer i : list) {
                l += i;
            }
            return l;
        });
        System.out.printf("합은 {%d} 입니다.\n", res);

        Long res2 = loopStatic((list) -> {
            long l = 0;
            for(Integer i : list) {
                l += i;
            }
            return l / list.size();
        });
        System.out.printf("평균은 {%d} 입니다.\n", res2);
    }

    public static Long loopStatic(IArrayProc iap) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(70);
        Long result = iap.process(list);

        Stream<Integer> ar = list.stream();
        IntStream is = ar.mapToInt((aaa) -> aaa + 2);
        int[] arr2 = is.toArray();

        int[] arr = list.stream()
                .mapToInt((aaa) -> aaa + 2)
                .toArray();

        int[] arr3 = list.parallelStream()
                .mapToInt((aaa) -> aaa + 2)
                .toArray();

        return result;
    }

    public static void streamExample() {
        List<Product> list = new ArrayList<>();
        for( int i = 0; i < 10; i++ ) {
            Product p = new Product(i+1, "상품"+i, "회사", (int)(100000 * Math.random()));
            list.add(p);
        }
        list.stream().forEach(
            p -> System.out.println(p.toString())
        );

        // 상품 가격의 총합을 구해보세요 stream().map(Product) -> price 를 합계
        // 상품 가격의 평균을 구해보세요 stream().map(Product) -> price 를 평균
    }
}
