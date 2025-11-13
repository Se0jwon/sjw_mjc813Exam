package com.mjc813.studyjava;

import com.mjc813.mjc_library.MjcPoint;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread();
        mt1.setName("mjc813 Thread");
        MyThread mt2 = new MyThread();
        mt2.setName("sss541 Thread");
        mt1.setDaemon(true);
        mt1.start();
        mt2.setDaemon(true);
        mt2.start();
        Computer[] coms = new Computer[5];

        ResultExecute re = new ResultExecute();
        ResultThread rt = new ResultThread(re, 1000);
        rt.start();

        for( int i = 0; i < coms.length; i++ ) {
            coms[i] = new Computer("com" + (i+1), ECpu.Gen9_i5
                    , ERam.RAM_8GB, EStorage.SSD_500GB, EGraphicCard.Intel_A770, "LG");
        }

        try {
            mt1.setActive(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for( Computer com : coms ) {
            com.booting();
            com.print();
            com.clickMouseLeftButton(200, 400);
            com.clickMouseRightButton(600, 800);
            com.powerOff();
        }
        Computer com = new Computer("a", null, null, null, null, "Sambo");
        com.printModel();
        Machine m = com;
        ((Machine)com).printModel();
        Machine m2 = new Machine("Samsung");
        m2.printModel();
        Computer c2 = (Computer)m;  // 처음부터 자식객체로 탄생(new)한 객체인 com 을 Machine 인 m 으로 변환했다가
                        // 다시 Computer c2 로 변환하는거는 가능
        c2.printModel();

        Computer m3 = new Computer();
        Machine cc3 = m3;
        cc3.printModel();

        CWCMarket cwc = new CWCMarket();
        MjcPoint mp = new MjcPoint();
        mp.addPoint(cwc, 30);
        mp.subPoint(null, 20);


        CWCMarket cal = new CWCMarket();
        Object o = cal;
        Object o2 = new Computer();
        //CWCMarket c3 = (CWCMarket)o2;

        StringBuilder str = new StringBuilder();
        for( int i = 0; i < 10; i++ ) {
            str.append(", ");
            str.append(i);
            str.append("ss" + 1);
        }
        String result = str.toString();

        List<String> al = new Vector<>();
        al.add("String");
        String s1 = al.get(0);

        List<Boolean> al2 = new LinkedList<>();
        al2.add(true);
        Boolean b1 = al2.get(0);

        MyBox<Computer, Integer> mb = new MyBox<>(3);
        mb.add(m3);
        Computer c4 = mb.get(0);

        MyBox<String, Double> mb2 = new MyBox<>(33.3);
        mb2.add("aabbb");
        String s2 = mb2.get(0);

        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        System.out.println("끝");

        // Call by Value
        int a = 10;
        long l = 10;
        boolean b = true;
        int r1 = func1(a, l, b);
        System.out.printf("%d\t%d\t%b\t%d\n", a, l, b, r1);

        // Call by Value
        Integer a2 = 10;
        Long l2 = 10L;
        Boolean b2 = true;
        int r2 = func2(a2, l2, b2);
        System.out.printf("%d\t%d\t%b\t%d\n", a2, l2, b2, r2);

        // Call by Reference
        Animal a3 = new Animal("강아지", 5);
        System.out.printf("%s\t\t%d\n", a3.getName(), a3.getAge());
        func3(a3);  // (a3 의 주소를 복사해서 함수에 전달했지만 함수 안에서는 a3 의 주소를 변경 해도 소용없음)
        System.out.printf("%s\t\t%d\n", a3.getName(), a3.getAge());

        // Call by Reference
        int[] arr1 = new int[]{1,2,3,4};
        func4(arr1);    // (arr1 의 주소를 복사해서 함수에 전달했지만 함수 안에서는 arr1 의 주소를 변경 해도 소용없음)
        System.out.printf("%d\t%d\t%d\t%d\n", arr1[0], arr1[1], arr1[2], arr1[3]);

    }

    public static int func1(int a, long l, boolean b) {
        a += 1;
        l += 1;
        b = !b;
        return a;
    }

    public static int func2(Integer a, Long l, Boolean b) {
        a += 1;
        l += 1;
        b = !b;
        return a;
    }

    public static void func3(Animal a) {
        a.setName( a.getName() + " 10년후" );
        a.setAge( a.getAge() + 10 );
        a = new Animal("이순신", 30);
    }

    public static void func4(int[] arr) {
        for( int i = 0; i < arr.length; i++ ) {
            arr[i] *= 10;
        }
        arr = new int[]{0, 0, 0, 0, 0};
    }
}
