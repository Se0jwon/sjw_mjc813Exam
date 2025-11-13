public class MainNoStatic {
    public static void main (String[] args) {
        NoStaticClass nsc1 = new NoStaticClass();
        NoStaticClass nsc2 = new NoStaticClass(10, "꼬맹이");
        NoStaticClass nsc3 = new NoStaticClass(50, "할아버지");
        nsc1.printMember();
        nsc2.printMember();
        nsc3.printMember();
        StaticClass sc1 = new StaticClass();
        sc1.firstName = "최";
        StaticClass.age = 10;
        StaticClass.name = "어린이";
        System.out.print(sc1.firstName);
        StaticClass.printMember();

        StaticClass.age = 20;
        StaticClass.printMember();

        SingleClass sc3 = SingleClass.getInstance();
        sc3.setName("최원철");
        System.out.println(sc3.getName());

        SingleClass sc4 = SingleClass.getInstance();
        System.out.println(sc4.getName());
    }
}
