package features.game;

import java.util.Scanner;

public class GuessGame {
    // data input/output stream --> wrapper
    /*
     * GuessGame(숫자 맞추는 게임)
     * util - Scanner 이용해서 사용자의 입력값(1~100 : guess)을 전달받는다
     * 
     * 조건
     * - 1 ~ 100 사이의 난수(answer)를 발생시켜서
     * - 주어진 기회는 10번(up, down)
     * 
     * 반환값
     * - 성공시) 정답을 맞추면 "xx번째 정답을 맞췄습니다"
     * - 실패시) "10번의 기회를 모두 사용하였습니다."
     */
    Scanner scan = new Scanner(System.in);

    public String gameFor() {
        int chance = 10;
        int answer = (int)( Math.random() * 100 ) + 1;

        for (int i = 1; i <= chance; i++) {
            System.out.print("생각하신 숫자를 입력하세요 : ");
            int guess = scan.nextInt();
            if(guess == answer) {
                return i+"번째에 정답을 맞췄습니다";
            } else if (guess > answer) {
                System.err.println(i+"번째 시도 : 입력하신 숫자보다 작습니다.");
            } else {
                System.out.println(i+"번째 시도 : 입력하신 숫자보다 큽니다.");
            }
        }
        return "10번의 기회를 모두 소진하셨습니다.";
    }
    // public int gameFor1() {
    //     int chance = 10;
    //     int answer = (int)( Math.random() * 100 ) + 1;

    //     for (int i = 1; i <= chance; i++) {
    //         System.out.print("생각하신 숫자를 입력하세요 : ");
    //         int guess = scan.nextInt();
    //         if(guess == answer) {
    //             System.err.println(i+"번째에 정답을 맞췄습니다");
    //             return 0;
    //         } else if (guess > answer) {
    //             System.err.println(i+"번째 시도 : 입력하신 숫자보다 작습니다.");
    //         } else {
    //             System.out.println(i+"번째 시도 : 입력하신 숫자보다 큽니다.");
    //         }
    //     }
    //     System.out.println("10번의 기회를 모두 소진하셨습니다.");
    //     return 0;
    // }
}
