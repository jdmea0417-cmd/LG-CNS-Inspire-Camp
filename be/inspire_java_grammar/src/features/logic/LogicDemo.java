package features.logic;

import features.user.domain.UserRequestDTO;
import features.user.domain.UserResponseDTO;

public class LogicDemo {

    public LogicDemo() {
    }

    public void operator() {
        System.out.println(">>> 반환 x, 매개변수 x");
        System.out.println(">>> 산술연산자 +, -, *, /, %, +=, -=, *=, /= etc...");
        System.out.println(">>> 증감연산자 ++, --");
        System.out.println(">>> 삼항연산자 (조건식) ? true : false");
        System.out.println(">>> 논리연산자 &, |, !, &&, ||");
        System.out.println(">>> 관계연산자 >, >=, <, <=, ==, !=");
    }

    public UserResponseDTO register(String email, String password, String name) {
        System.out.println(">>> 반환 o, 매개변수 o");
        UserRequestDTO user = UserRequestDTO.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        System.out.println("debug >>>> log : " + user);

        // 조건처리 후 정상적으로 사용자 가입이 이루어졌다고 가정하고

        UserResponseDTO result = new UserResponseDTO(201, "정상가입되었습니다.");
        return result;
    }

    /*
     * script if구문
     * if(논리형 값) {
     * true인 경우
     * } else {
     * false인 경우
     * }
     * Quiz)
     * 매개변수의 값의 범위는 : 1 ~ 3
     * - 1 : 금도끼, 2 : 은도끼, 3 : 쇠도끼
     * - 나무꾼이 자기의 도끼가 1번이라고 하면 -> "거짓말하는구나"
     * - 나무꾼이 자기의 도끼가 2번이라고 하면 -> "또 거짓말하는구나"
     * - 나무꾼이 자기의 도끼가 3번이라고 하면 -> "정직하구나 너에게 모든 도끼를 주겠다"
     */
    public String ifwoodman(int number) {
        String result = null;
        // 구현부
        // case 1
        // if(number == 1)
        // result = "거짓말하는구나";
        // else if(number == 2)
        // result = "또 거짓말하는구나";
        // else if(number == 3)
        // result = "정직하구나 너에게 모든 도끼를 주겠다";
        // else
        // result = "뭔 헛소리야";

        // case 2
        // params type : byte, short, int, char, String, enum
        // switch (number) {
        // case 1 :
        // result = "거짓말하는구나.";
        // break;
        // case 2 :
        // result = "또 거짓말하는구나.";
        // break;
        // case 3 :
        // result = "정직하구나.";
        // break;
        // default :
        // result = "헛소리하는구나.";
        // break
        // }

        // lambda : ->
        // switch (number) {
        // case 1 -> result = "거짓말하는구나.";
        // case 2 -> result = "또 거짓말하는구나.";
        // case 3 -> result = "정직하구나.";
        // default -> result = "헛소리하는구나.";
        // }
        // return result;

        // case 3
        // if (number >= 1 && number <= 3) {
        // if (number == 1) {
        // result = "거짓말하는구나";
        // } else if (number == 2) {
        // result = "또 거짓말하는구나";
        // } else if (number == 3) {
        // result = "정직하구나 너에게 모든 도끼를 주겠다";
        // }
        // } else {
        // result = "뭔 헛소리야";
        // }

        // case 4
        result = (number == 1) ? "거짓말하는구나."
                : (number == 2) ? "또 거짓말하는구나."
                        : (number == 3) ? "정직하구나 너에게 모든 도끼를 주겠다"
                                : "헛소리하지마라";

        return result;
    }

    /*
     * 반복구문?( for ~, while, do ~ while)
     * - 배열([]) 또는 Collection API(List, Set, Map)
     * - 요소의 타입으로 기본타입보다는 참조타입을 사용하는 경우가 대부분
     * - 반복 도중 특정 조건에 만족했을 때 반복을 종료(break)
     * - 해당 조건만 스킵(continue)
     * 
     * java ver : 8 ~ Stream API
     * - lambda expression
     * - 함수형 인터페이스(Supplier, Consumer, Function, Predicate)
     */

    public int sumNumber(int start, int end) {
        int result = 0;
        int temp = 0;
        if (start > end) {
            temp = start;
            start = end;
            end = temp;
        }
        for (int i = start; i <= end; i++) {
            result += i;
        }
        return result;
    }

    /*
     * Quiz)
     * - 1 ~ 100 사이의 난수를 발생시켜서 1~해당난수까지의 누적합을 계산
     * - argument X
     * - return type : int
     * - method name : sumRandom()
     * hint) Math.random() 참고
     */

    public int sumRandom() {
        int result = 0;
        int nan = (int) (Math.random() * 100) + 1;
        System.out.println("debug >>> nan = " + nan);

        // for
        // for (int i = 1; i <= nan; i++) {
        // result += i;
        // }
        // int i = 1;

        // while
        // while(i <= nan) {
        // result += i;
        // i++;
        // }

        // do ~ while() -> 무조건 한번은 실행
        int i = 1;
        do {
            result += i;
            i++;
        } while (i <= nan);
        return result;
    }

    public void printGugudan() {
        outer: for (int i = 2; i <= 9; i++) {
            // if (i == 5) {
            // break;
            // }
            System.out.printf("dan = %d\n", i);
            inner: for (int j = 1; j <= 9; j++) {
                // if(i == 5){
                // break outer;
                // }
                System.out.printf("%d X %d = %d\t", i, j, i * j);
            }
            System.out.printf("\n");
        }
    }
    // Q) 혹시 문자열도 반복이 가능할까요?
    // 문자열은 문자의 집합['', '', '', '', ''] = "XXXXX"
    // ==(literal value, 평소 사용 x), .equals()

    public void popStr(String str) {
        System.out.println("debug >>>> params : " + str);
        System.out.println("debug >>>> str length : " + str.length());
        for(int i = str.length()-1; i >= 0 ; i--){
            System.out.print(str.charAt(i));
        }
    }

    public void Fiv() {
        int[] fivo = new int[101];
        fivo[0] = 1;
        fivo[1] = 1;
        int nan = (int) (Math.random() * 100) + 1;
        for (int i = 2; i < nan; i++) {
            fivo[i] = fivo[i - 1] + fivo[i - 2];
            System.out.printf("Debug >>>> fivo[%d] = %d\n", i + 1, fivo[i]);
        }
    }
}
