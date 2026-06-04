package test.var;

public class variable {
    // 변수 + 메서드, 생성자(Constructor)

    // 생성자(반환타입이 존재하지 않고 클래스이름과 동일한 경우)
    // 기본생성자(매개변수x), 스페셜생성자(매개변수o)
    // 인스턴스의 소유가 아니기 때문에 호출될 수 없고 new 연산자 뒤에서만 호출
    // 클래스에 생성자가 명시적으로 정의되어있지 않아도 기본생성자 호출 가능
    // 매개변수의 타입과 갯수를 달리해서 여러번 생성자를 정의(overloading)
    public variable(int x) {}
    public variable() {}
    public variable(String str) {}
    public variable(int x, String str) {}
    /*
    - 변수 선언 문법
    [접근지정자] [변수타입] [변수명] = literal value ;
    public
    - 변수는 선원위치에 따라서 멤버변수, 지역변수
    변수 타입
    - 기본타입(primitive type) : 숫자, 문자, 문자열, 논리
    byte, short, int, log, long, float, double
    char, String
    boolean
    기본타입은 변수가 값을 담는 그릇

    - 참조타입(reference type) : 기본타입이 아닌 모든것
    참조타입은 주소값을 담는 그릇
    */

    public String name = "임정섭";
    public char gender = 'M';
    public int age = 20;
    public boolean isMarriage = true;

    /*
    - 메서드 선언문법
    - 반환타입 : void, 기본타입, 참조타입 - return
    [접근지정자] [반환타입] [메서드명]([매개변수]) {
        //업무로직 수행하는 코드 작성
    }
    */

    public void play() {
        System.out.println("debug >>>> play()");
    }
}
