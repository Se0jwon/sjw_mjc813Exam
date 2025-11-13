public enum MyEnumData {
    명지대(4),
    명지전문대(5) ,
    고려대(6);
    private int value;
    MyEnumData(int val) {
        this.value = val;
    }
    public int getValue() {
        return this.value;
    }
}
