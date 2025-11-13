public class NoStaticClass {
    private int age;
    private String name;

    public NoStaticClass() {
        this.age = 1;
        this.name = "";
    }
    public NoStaticClass(int ag, String nm) {
        this.age = ag;
        this.name = nm;
    }
    public void printMember() {
        System.out.println("age : " + this.age);
        System.out.println("name : " + this.name);
    }
}
