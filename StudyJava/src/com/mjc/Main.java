package com.mjc;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello World !");
            byte bt = Byte.parseByte("12");
            int ni = Integer.parseInt("645678");
            long ln = Long.parseLong("64");
            float fl = Float.parseFloat("4.4");
            double dl = Float.parseFloat("4.4");
            String strbt = String.valueOf(bt);
            String strni = String.valueOf(ln);
            String strln = String.valueOf(ln);
            String strfl = String.valueOf(fl);
            String strdl = String.valueOf(dl);
            System.out.printf("%s\n", strbt);
            System.out.printf("%10s\n", strni);
            System.out.printf("%010d\n", ln);
            System.out.printf("%s\n", strfl);
            byte bv = 10;
            char ch = 'A';
            double _age = bv;
            System.out.printf("%f\n", _age);
            String str = """
                    A
                    B
                    C
                    """;
            float var = 10;
            System.out.printf("%s\n", str);
            double bt2 = 5 + dl;
            System.out.println("bt = " + bt2);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}