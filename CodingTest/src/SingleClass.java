public class SingleClass {
    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    private static SingleClass sc = new SingleClass();
    private SingleClass() {
    }
    public static SingleClass getInstance() {
        return sc;
    }
    private String name;
}
