package features.stat;

public class StaticDemo {
    /*
     * static(변수, 메서드, 클래스)
     * - static 변수의 소유는 인스턴스가 아니라 클래스의 소유가 됨.
     * final : 자바의 상수
     * - static final
     */
    public String message = "인스턴스 소유 변수";
    public static String staticMessage = "클래스 소유의 변수";
    public static final double PI = 3.14;

    public void nonStaticMethod() {
        System.out.println("debug >>> nonStaticMethod : " + message);
        System.out.println("debug >>> nonStaticMethod : " + staticMessage);
        System.out.println("debug >>> nonStaticMethod : " + PI);

    }

    public static void StaticMethod() {
        // static method에서는 nonstatic에 접근할 수 없다.
        // System.out.println("debug >>> StaticMethod : " + message);
        System.out.println("debug >>> StaticMethod : " + staticMessage);
        System.out.println("debug >>> StaticMethod : " + PI);
    }
}
