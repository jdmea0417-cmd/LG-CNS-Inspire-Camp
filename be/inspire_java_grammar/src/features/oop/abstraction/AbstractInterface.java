package features.oop.abstraction;

public interface AbstractInterface {
    /*
    - 생성자 정의 불가
    - 상수 : public static final type var = init
    - 추상메서드 public atstract type method() ;
    */
    // 묵시적으로 static final
    public double PI = 3.14;
    // 묵시적으로 abstract
    public void methodInterface() ;
}
