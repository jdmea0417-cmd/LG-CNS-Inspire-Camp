package features.oop.util;

// enum : 프로그램에서 사용할 수 있는 상수의 집합
public enum Flag {
    STU(1), TEA(2), MAN(3);

    private final int flag;
    private Flag(int flag) {
        this.flag = flag;
    }
    public int getFlag() {
        return this.flag;
    }
}
