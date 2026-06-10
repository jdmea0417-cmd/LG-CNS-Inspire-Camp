import java.io.BufferedReader;
import java.io.InputStreamReader;

import features.exception.ExceptionDemo;

public class ExceptionApp {
    public static void main(String[] args) {
        System.out.println(">>>> main thread start");
        String [] strAry = {"jslim", "lgcns", "inspire"};

        // runtime exception
        // try {
        //     for(int idx = 0; idx <= strAry.length; idx++) {
        //         System.out.println(strAry[idx]);
        //     }
        // } catch(ArrayIndexOutOfBoundsException e) {
        //     e.printStackTrace();
        // } catch() {

        // } catch(Exception e) { // 다중catch. 부모 Exception의 경우, 무조건 후순위에 배치

        // } finally {
        //     System.out.println(">>>> finally 예외 발생여부와 상관없이 무조건 실행");
        // }

        // compile exception
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String txt = null;
        // try {
        //     txt = br.readLine();
        // } catch(Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(txt);

        // user exception
        ExceptionDemo demo = new ExceptionDemo();
        try{
            demo.first(-10);
        } catch(Exception e) {
            System.out.println(">>>> 사용자 예외처리 블럭");
        } finally {
            try {

            } catch(Exception e) {
                e.printStackTrace();;
            }
        }

        System.out.println(">>>> main thread end");
    }
}
