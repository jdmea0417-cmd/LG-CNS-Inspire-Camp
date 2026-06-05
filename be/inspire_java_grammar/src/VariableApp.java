import test.var.variable;

public class VariableApp {
    // 멤버변수
    public String str = null;
    public static void main(String[] args) {
        // 지역변수
        int year = 2026;
        double height = 178.6;

        // new 연산자 뒤에 호출되는 메서드가 생성자이다.
        // 생성자란? like a method로 클래스의 이름과 동일한 메서드
        variable var = new variable();
        var.play();
        //System.out.println( var.name );
        //System.out.println( var.gender );

        // script : parsFloat()
        // 타입변환(casting : 묵시적(작은 -> 큰), 명시적(큰 -> 작은))
        byte x = 10, y = 20, sum = 0;
        sum = (byte)(x + y);

        /*
        숫자형
        - 정수 : byte, short, int, long
        - 실수 : float, double
        - 문자 : char
        casting : byte -> short -> char -> int -> long -> float -> double
        */
        char letterA ='A', letterB='B';
        System.out.println((char)(letterA+letterB));
        System.out.println((char)65);
        float floatValue = (float)3.14;

        String str01 = "jslim";
        String str02 = "jslim"; // 이 경우, str01, str02의 key 값이 동일
        // String str01 = new String("jslim");
        // String str02 = new String("jslim");
        if( str01 == str02 ){// key 값이 동일할 때 출력됨
            System.out.println("str01 == str02");
        }
        // 일반적으로 자바에서는 문자열 값을 비교할 때 == 사용하지 않고 .equals() 사용
        if( str01.equals(str02) ){
            System.out.println("str01.equals(str02)");
        }
    }
}
